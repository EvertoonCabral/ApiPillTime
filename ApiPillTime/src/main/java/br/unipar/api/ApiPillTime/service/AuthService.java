package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Endereco;
import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.UserRole;
import br.unipar.api.ApiPillTime.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        public AuthService(IdosoRepository idosoRepository, UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
            this.idosoRepository = idosoRepository;
            this.usuarioRepository = usuarioRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public void registerIdoso(IdosoDTO idosoDTO) {
            // Validação para evitar NullPointerException
            if (idosoDTO == null) {
                throw new IllegalArgumentException("IdosoDTO não pode ser nulo");
            }

            // Validação para verificar se o EnderecoService foi injetado
            if (enderecoService == null) {
                throw new IllegalStateException("EnderecoService não foi injetado no AuthService");
            }

            // Criação e preenchimento da entidade Idoso a partir do DTO
            Idoso newIdoso = new Idoso();
            newIdoso.setNome(idosoDTO.getNome());
            newIdoso.setCpf(idosoDTO.getCpf());
            newIdoso.setEmail(idosoDTO.getEmail());
            newIdoso.setDataNascimento(idosoDTO.getDataNascimento());

            // Conversão e associação do endereço
            Endereco endereco = enderecoService.convertToEntity(idosoDTO.getEndereco());
            newIdoso.setEndereco(endereco);

            newIdoso.setObservacao(idosoDTO.getObservacao());
            newIdoso.setTelefone(idosoDTO.getTelefone());

            // Criação do usuário associado ao idoso
            Usuario newUsuario = new Usuario();
            newUsuario.setLogin(idosoDTO.getLogin());
            newUsuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
            newUsuario.setRole(UserRole.USER);
            newUsuario.setPessoa(newIdoso);

            // Salvar entidades no banco de dados
            idosoRepository.save(newIdoso);
            usuarioRepository.save(newUsuario);
        }

    }



