package com.medicamp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.medicamp.sec.PasswordEncoderImpl;

@SpringBootApplication
@ComponentScan({ "com.medicamp.model", "com.medicamp.api","com.medicamp.mobiel","com.medicamp.sec"})
@EnableJpaRepositories({"com.medicamp.db","com.medicamp.sec"})
@EntityScan({"com.medicamp.model","com.medicamp.sec"})
public class ApiApplication {

	@Bean
    public PasswordEncoderImpl passwordEncoder(){
		PasswordEncoderImpl bCryptPasswordEncoder = new PasswordEncoderImpl();
        return bCryptPasswordEncoder;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	
}
