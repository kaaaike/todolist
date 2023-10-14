package com.kaike.study.todolist.filter;

import com.kaike.study.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

@Autowired
private IUserRepository userRepository;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

            var servletPath = request.getServletPath();
            if(servletPath.startsWith("/tasks/")) {
                // Busca informações de Auth do User
                var authorization = request.getHeader("Authorization");
                var authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);
                System.out.println("Authorization: " + authString);

                // Validando usuário
                var user = this.userRepository.findByUsername(username);
                if (user == null) {
                    response.sendError(401);
                } else {

                    // Validando a senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if (passwordVerify.verified) {
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }
    }
}