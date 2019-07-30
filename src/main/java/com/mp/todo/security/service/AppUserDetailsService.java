package com.mp.todo.security.service;

import com.mp.todo.infrastructure.jpa.repository.UserJpaRepository;
import com.mp.todo.domain.Role;
import com.mp.todo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailsService  implements UserDetailsService {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userJpaRepository.findByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found");
        }



        List<GrantedAuthority> grantList = new ArrayList<>();

        try {
            List<Role> roles = user.getRoles();



            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        }catch (Exception e)
        {
            System.out.println("loadUserByUsername exception " + e.getMessage());
        }


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantList);

        return userDetails;
    }

}