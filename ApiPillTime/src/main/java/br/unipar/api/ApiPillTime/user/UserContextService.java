package br.unipar.api.ApiPillTime.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    private final UserRepository userRepository;

    @Autowired
    public UserContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario getUsuarioAtual() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = userRepository.findByLogin(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return usuario;
    }

}

