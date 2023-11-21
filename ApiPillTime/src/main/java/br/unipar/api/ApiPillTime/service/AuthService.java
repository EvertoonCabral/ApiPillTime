package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.CuidadorRepository;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.UserRole;
import br.unipar.api.ApiPillTime.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

        // Verifica se já existe um Idoso com o mesmo CPF
        if (idosoRepository.findByCpf(idosoDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um idoso cadastrado com este CPF");
        }

        // Verifica se já existe um Idoso com o mesmo e-mail
        if (idosoRepository.findByEmail(idosoDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um idoso cadastrado com este e-mail");
        }

        Idoso idoso = new Idoso();
        idoso.setNome(idosoDTO.getNome());
        idoso.setCpf(idosoDTO.getCpf());
        idoso.setEmail(idosoDTO.getEmail());
        idoso.setDataNascimento(idosoDTO.getDataNascimento());
        idoso.setObservacao(idosoDTO.getObservacao());
        idoso.setTelefone(idosoDTO.getTelefone());
        idoso.setStAtivo(true);
        idoso.setTipoUsuario(TipoUsuario.I);

        Endereco endereco = enderecoService.convertToEntity(idosoDTO.getEndereco());
        idoso.setEndereco(endereco);

        Usuario usuario = new Usuario();
        usuario.setLogin(idosoDTO.getLogin());
        usuario.setPassword(passwordEncoder.encode(idosoDTO.getSenha()));
        usuario.setRole(UserRole.USER);
        usuario.setPessoa(idoso);

        Cuidador cuidador = cuidadorRepository.findByCpf(idosoDTO.getCpfCuidador())
                .orElseThrow(() -> new IllegalArgumentException("Cuidador com CPF fornecido não encontrado"));

        idoso.setCuidador(cuidador);

        try {
            vincularIdosoACuidador(idoso, cuidador);
            usuarioRepository.save(usuario);
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
        // idosoRepository.save(idoso);
    }




    }


    



