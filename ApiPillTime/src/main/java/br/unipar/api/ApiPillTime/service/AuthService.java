package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Cuidador;
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
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void registerIdoso(IdosoDTO idosoDTO) {


        if (idosoDTO == null) {
            throw new IllegalArgumentException("IdosoDTO não pode ser nulo");
        }

        Idoso newIdoso = new Idoso();
        newIdoso.setNome(idosoDTO.getNome());
        newIdoso.setCpf(idosoDTO.getCpf());
        newIdoso.setEmail(idosoDTO.getEmail());
        newIdoso.setDataNascimento(idosoDTO.getDataNascimento());
        newIdoso.setObservacao(idosoDTO.getObservacao());
        newIdoso.setTelefone(idosoDTO.getTelefone());
        newIdoso.setStAtivo(true);
        newIdoso.setTipoUsuario(TipoUsuario.I);

        Endereco endereco = enderecoService.convertToEntity(idosoDTO.getEndereco());
        newIdoso.setEndereco(endereco);

        // Obtenção do usuário atual (usuarioAtual) e validação de seu papel.
        Usuario usuarioAtual = userContextService.getUsuarioAtual();
        if (usuarioAtual == null || !usuarioAtual.getRole().equals(UserRole.ADMIN)) {
            throw new SecurityException("Ação não permitida. Somente cuidadores podem registrar idosos.");
        }

        // Verificando se 'Pessoa' do usuário é um 'Cuidador'
        if (!(usuarioAtual.getPessoa() instanceof Cuidador)) {
            throw new SecurityException("O usuário atual não está associado a um cuidador.");
        }

        Cuidador cuidador = (Cuidador) usuarioAtual.getPessoa();

        newIdoso.setCuidador(cuidador);
        List<Idoso> idosos = cuidador.getListaIdoso();
        if (idosos == null) {
            idosos = new ArrayList<>();
            cuidador.setListaIdoso(idosos); // Ajuste o nome do método se for diferente em sua classe.
        }


        Usuario newUsuario = new Usuario();
        newUsuario.setLogin(idosoDTO.getLogin());
        newUsuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
        newUsuario.setRole(UserRole.USER);
        newUsuario.setPessoa(newIdoso);




        try {
            idosoRepository.save(newIdoso);
            usuarioRepository.save(newUsuario);
        } catch (DataAccessException e) {

            throw new RuntimeException("Erro ao salvar informações do idoso e usuário", e);
        }

    }
}

    



