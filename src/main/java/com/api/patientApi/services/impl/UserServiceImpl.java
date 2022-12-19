package com.api.patientApi.services.impl;

import com.api.patientApi.exception.SenhaInvalidaException;
import com.api.patientApi.models.User;
import com.api.patientApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public User salvar( User usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar(User usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasSaoIguais = encoder.matches(usuario.getPassword(), user.getPassword());
        if (senhasSaoIguais){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User usuario = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário na encontrado"));

       String[] roles = usuario.isAdmin() ?
               new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return org.springframework.security.core.userdetails
                .User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
    }
}
