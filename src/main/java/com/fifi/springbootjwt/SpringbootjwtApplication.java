package com.fifi.springbootjwt;

import com.fifi.springbootjwt.entity.User;
import com.fifi.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringbootjwtApplication {

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
 public void iitUser(){
     List<User> users= Stream.of(new User(101,"milkias","password","milkias@gmail.com"),

             new User(102,"aron","pd","aron@gmail.com"),
             new User(103,"Tomas","prd","tomas@gmail.com"),
             new User(104,"kidane","paa","kidane@gmail.com")


             ).collect(Collectors.toList());

     userRepository.saveAll(users);


 }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjwtApplication.class, args);
    }

}
