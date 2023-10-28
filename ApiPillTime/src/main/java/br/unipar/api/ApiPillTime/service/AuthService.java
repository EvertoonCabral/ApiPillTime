package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import br.unipar.api.ApiPillTime.user.UserContextService;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.UserRole;
import br.unipar.api.ApiPillTime.user.Usuario;
import org.hibernate.Hibernate;
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
            throw new IllegalArgumentException("Idoso não pode ser nulo");
        }

        // Criação de uma nova entidade Idoso a partir do DTO recebido.
        Idoso newIdoso = new Idoso();
        newIdoso.setNome(idosoDTO.getNome());
        newIdoso.setCpf(idosoDTO.getCpf());
        newIdoso.setEmail(idosoDTO.getEmail());
        newIdoso.setDataNascimento(idosoDTO.getDataNascimento());
        newIdoso.setObservacao(idosoDTO.getObservacao());
        newIdoso.setTelefone(idosoDTO.getTelefone());
        newIdoso.setStAtivo(true);
        newIdoso.setTipoUsuario(TipoUsuario.I);

        // Conversão e definição do endereço.
        Endereco endereco = enderecoService.convertToEntity(idosoDTO.getEndereco());
        newIdoso.setEndereco(endereco);

        // Criação de uma nova entidade Usuario para representar as credenciais do idoso.
        Usuario newUsuario = new Usuario();
        newUsuario.setLogin(idosoDTO.getLogin());
        newUsuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
        newUsuario.setRole(UserRole.USER);
        newUsuario.setPessoa(newIdoso);

        // Tentativa de salvar as entidades no banco de dados.
        try {
            // Salvando a entidade Idoso
            idosoRepository.save(newIdoso);
            // Salvando a entidade Usuario relacionada
            usuarioRepository.save(newUsuario);
        } catch (DataAccessException e) {
            // Em caso de erro durante o acesso aos dados, uma exceção é lançada.
            throw new RuntimeException("Erro ao salvar informações do idoso e usuário", e);
        }
    }


    }


    



