package com.br.itapemirim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.br.itapemirim.entity.UserEntity;
import com.br.itapemirim.error.BusinessException;
import com.br.itapemirim.repository.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	
    	UserEntity user = userRepository.findUserByLogin(name);

        if(user == null) {
            throw new BusinessException("Login não encontrado");
        }

        return (UserDetails) user;
    }
}