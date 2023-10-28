package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.CuidadorRepository;
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


    private final IdosoRepository idosoRepository;
    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoService enderecoService;
    private final CuidadorRepository cuidadorRepository;

    @Autowired
    public AuthService(IdosoRepository idosoRepository, UserRepository usuarioRepository,
                       PasswordEncoder passwordEncoder, EnderecoService enderecoService,
                       CuidadorRepository cuidadorRepository) {
        this.idosoRepository = idosoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.enderecoService = enderecoService;
        this.cuidadorRepository = cuidadorRepository;
    }

    @Transactional
    public void registerIdoso(IdosoDTO idosoDTO) {
        if (idosoDTO == null) {
            throw new IllegalArgumentException("Idoso não pode ser nulo");
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

        // Criação de uma nova entidade Usuario para representar as credenciais do idoso.
        Usuario newUsuario = new Usuario();
        newUsuario.setLogin(idosoDTO.getLogin());
        newUsuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
        newUsuario.setRole(UserRole.USER);
        newUsuario.setPessoa(newIdoso);

        Cuidador cuidador = cuidadorRepository.findByCpf(idosoDTO.getCpfCuidador())
                .orElseThrow(() -> new IllegalArgumentException("Cuidador com CPF fornecido não encontrado"));

        newIdoso.setCuidador(cuidador);

        vincularIdosoACuidador(newIdoso, cuidador);


        try {
            idosoRepository.save(newIdoso);
            usuarioRepository.save(newUsuario);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao salvar informações do idoso e usuário", e);
        }
    }
    @Transactional
    public void vincularIdosoACuidador(Idoso idoso, Cuidador cuidador) {
        if (idoso == null || cuidador == null) {
            throw new IllegalArgumentException("Idoso e Cuidador não podem ser nulos");
        }

        idoso.setCuidador(cuidador);

        if (cuidador.getListaIdoso() == null) {
            cuidador.setListaIdoso(new ArrayList<>());
        }
        cuidador.getListaIdoso().add(idoso);


        cuidadorRepository.save(cuidador);

        idosoRepository.save(idoso);
    }



    }


    



