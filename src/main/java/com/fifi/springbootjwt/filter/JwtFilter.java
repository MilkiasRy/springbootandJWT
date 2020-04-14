package com.fifi.springbootjwt.filter;

import com.fifi.springbootjwt.service.CustomUserDetailService;
import com.fifi.springbootjwt.util.JwtUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailService  customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization=httpServletRequest.getHeader("Authorization");
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWxraWFzIiwiZXhwIjoxNTg2ODgyODQ0LCJpYXQiOjE1ODY4NDY4NDR9.8sNLTFfYCRihizmIpG8b5ts2gd9fXuQHf9sSD6XrWd0
String token=null;
String userName = null;
        if(authorization!=null&&authorization.startsWith("Bearer")  ){
            token =authorization.substring(7);
            userName=jwtUtil.extractUsername(token);
        }
        if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){


            UserDetails userDetails=customUserDetailService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities()

                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
