package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Endereco;
import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.TipoUsuario;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import br.unipar.api.ApiPillTime.user.UserContextService;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.UserRole;
import br.unipar.api.ApiPillTime.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class AuthService {


        @Autowired
        private IdosoRepository idosoRepository;
        @Autowired
        private UserRepository usuarioRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private EnderecoService enderecoService;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private UserContextService userContextService;

        @Autowired
        public AuthService(IdosoRepository idosoRepository, UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
            this.idosoRepository = idosoRepository;
            this.usuarioRepository = usuarioRepository;
            this.passwordEncoder = passwordEncoder;
        }


        public void registerIdoso(IdosoDTO idosoDTO) {
            // É bom prática garantir que o objeto injetado não seja nulo.
            // Outras validações de campo devem ser feitas no controlador ou via validação de bean de spring.
            if (idosoDTO == null) {
                throw new IllegalArgumentException("IdosoDTO não pode ser nulo");
            }

            // A criação do objeto Idoso a partir do DTO.
            Idoso newIdoso = new Idoso();
            newIdoso.setNome(idosoDTO.getNome());
            newIdoso.setCpf(idosoDTO.getCpf());
            newIdoso.setEmail(idosoDTO.getEmail());
            newIdoso.setDataNascimento(idosoDTO.getDataNascimento());
            newIdoso.setObservacao(idosoDTO.getObservacao());
            newIdoso.setTelefone(idosoDTO.getTelefone());
            newIdoso.setStAtivo(true);
            newIdoso.setTipoUsuario(TipoUsuario.I);
            newIdoso.setCuidador(userContextService.getUsuarioAtual());

            // Conversão e associação do endereço.
            Endereco endereco = enderecoService.convertToEntity(idosoDTO.getEndereco());
            newIdoso.setEndereco(endereco);

            // Criação do usuário associado ao idoso.
            Usuario newUsuario = new Usuario();
            newUsuario.setLogin(idosoDTO.getLogin());
            newUsuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
            newUsuario.setRole(UserRole.USER); // ou qualquer regra de negócio aplicável
            newUsuario.setPessoa(newIdoso);

            // Obtenção do usuário atual (cuidador) e validação de seu papel.
            Usuario cuidador = userContextService.getUsuarioAtual();
            if (cuidador == null || !cuidador.getRole().equals(UserRole.ADMIN)) {
                throw new SecurityException("Ação não permitida. Somente cuidadores podem registrar idosos.");
            }

            // Adicionando o novo idoso à lista do cuidador, garantindo que a lista não é nula.
            List<Idoso> idosos = cuidador.getListaIdosos();
            if (idosos == null) {
                idosos = new ArrayList<>();
                cuidador.setListaIdosos(idosos);
            }
            idosos.add(newIdoso);

            // Salvando o idoso e o usuário. Se houver algum problema com essas operações,
            // o Spring irá gerenciar e lançar uma exceção, garantindo a atomicidade da transação.
            idosoRepository.save(newIdoso); // isso atualiza 'newIdoso' com o ID gerado.
            usuarioRepository.save(newUsuario); // isso salva as informações do usuário.

            // Não é necessário salvar 'cuidador' aqui se estamos em uma transação e
            // 'cuidador' é gerenciado pelo contexto de persistência, pois qualquer
            // mudança nele será automaticamente persistida no final da transação.
        }
    }

    



