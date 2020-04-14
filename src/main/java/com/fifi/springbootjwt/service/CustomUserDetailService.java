package com.fifi.springbootjwt.service;


import com.fifi.springbootjwt.entity.User;
import com.fifi.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;






    @Override
    public UserDetails loadUserByUsername(String usernames) throws UsernameNotFoundException {
        User user =repository.findByUserName(usernames);
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList
                <>());
    }
}
