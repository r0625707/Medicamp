package com.medicamp.sec;

import static com.medicamp.sec.SecConstants.EXPIRATION_TIME;
import static com.medicamp.sec.SecConstants.HEADER_STRING;
import static com.medicamp.sec.SecConstants.SECRET;
import static com.medicamp.sec.SecConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	

	
	private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
    		
    	
    	try {
            com.medicamp.model.User userModel = new ObjectMapper().readValue(req.getInputStream(), com.medicamp.model.User.class);
            ArrayList<Object> list = new ArrayList<>();
            list.add(userModel);
            return authenticationManager.authenticate(
            		
            		
                    new UsernamePasswordAuthenticationToken(
                    		userModel.getLogin(),
                    		userModel.getPassword(),                  		
                            new ArrayList<>() ))
            ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User userUD = ((User) auth.getPrincipal());
    	String token = JWT.create()
                .withSubject(userUD.getUsername())
                .withClaim("role",userUD.getAuthorities().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign( Algorithm.HMAC256(SECRET));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    
}
