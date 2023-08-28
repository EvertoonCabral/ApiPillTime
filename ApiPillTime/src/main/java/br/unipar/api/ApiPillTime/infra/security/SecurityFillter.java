package br.unipar.api.ApiPillTime.infra.security;

import br.unipar.api.ApiPillTime.user.TokenService;
import br.unipar.api.ApiPillTime.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFillter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token  = this.recoverToken(request);
        if(token != null){

            var login = tokenService.validaToken(token);
            UserDetails user = userRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);


    }

    private String recoverToken(HttpServletRequest request){

    var authHeader = request.getHeader("Authorization");
    if(authHeader ==null) return  null;
    return authHeader.replace("Bearer","");


    }

}
