package com.amitgiri.financetracker.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amitgiri.financetracker.auth.config.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	
            String token = authHeader.substring(7);
            System.out.println("Token: " + token);
            System.out.println("Valid: " + jwtUtil.validateToken(token));
            
            
            if (jwtUtil.validateToken(token)) {

                String email = jwtUtil.extractEmail(token);

                // 🔥 THIS IS THE MISSING PART
                UsernamePasswordAuthenticationToken auth =
                	    new UsernamePasswordAuthenticationToken(
                	        email,
                	        null,
                	        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                	    );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;               
            }
        }

        filterChain.doFilter(request, response);
    }
}