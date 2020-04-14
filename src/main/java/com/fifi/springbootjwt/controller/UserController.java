package com.fifi.springbootjwt.controller;


import com.fifi.springbootjwt.entity.AuthcationRequest;
import com.fifi.springbootjwt.util.JwtUtil;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
public String welcome(){
    return "WElcome to JWT";
}



 @PostMapping("/authen")
public  String  generateToken(@RequestBody AuthcationRequest authcationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authcationRequest.getUserName(), authcationRequest.getPassword()));
        }
        catch (Exception e){
            throw new Exception("invalid  userNme and Password");
        }
        return jwtUtil.generateToken(authcationRequest.getUserName());

}

}
