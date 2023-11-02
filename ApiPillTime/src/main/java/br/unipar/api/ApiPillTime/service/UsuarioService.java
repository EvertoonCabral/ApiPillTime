package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.TipoUsuario;
import br.unipar.api.ApiPillTime.user.RegisterDTO;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.UserRole;
import br.unipar.api.ApiPillTime.user.Usuario;
import br.unipar.api.ApiPillTime.repository.CuidadorRepository; // Se você tem um repositório específico para Cuidador
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private CuidadorRepository cuidadorRepository; // Se aplicável
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    public UsuarioService(UserRepository userRepository,
                          CuidadorRepository cuidadorRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cuidadorRepository = cuidadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }


    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    
    @Transactional
    public Usuario registerCuidador(RegisterDTO registerDTO) {

        // Criação e preenchimento do Cuidador a partir do DTO.
        Cuidador cuidador = new Cuidador();
        cuidador.setNome(registerDTO.getPessoa().getNome());
        cuidador.setCpf(registerDTO.getPessoa().getCpf());
        cuidador.setEmail(registerDTO.getPessoa().getEmail());
        cuidador.setTelefone(registerDTO.getPessoa().getTelefone());
        cuidador.setDataNascimento(registerDTO.getPessoa().getDataNascimento());
        cuidador.setEndereco(registerDTO.getPessoa().getEndereco());
        cuidador.setObservacao(registerDTO.getPessoa().getObservacao());
        cuidador.setTipoUsuario(TipoUsuario.C);
        cuidador.setStAtivo(true);
        cuidadorRepository.save(cuidador);

        Usuario usuario = new Usuario();
        usuario.setLogin(registerDTO.getLogin());
        usuario.setPassword(passwordEncoder.encode(registerDTO.getSenha()));
        usuario.setRole(UserRole.ADMIN);
        usuario.setPessoa(cuidador);

        return userRepository.save(usuario);
    }
    public Usuario findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


}




