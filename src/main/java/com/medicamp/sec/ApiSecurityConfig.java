package com.medicamp.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.medicamp.sec.SecConstants.SIGN_UP_URL;
import static com.medicamp.sec.SecConstants.LOGIN_URL;
import static com.medicamp.sec.SecConstants.WELCOME_URL;

import java.util.Arrays;

@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	private PasswordEncoder passwordEncoder;

	public ApiSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl(LOGIN_URL);
		http
				.cors()
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, WELCOME_URL).permitAll()
				.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
		        .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
		        .antMatchers(HttpMethod.OPTIONS, SIGN_UP_URL).permitAll()
		        .antMatchers(HttpMethod.OPTIONS, LOGIN_URL).permitAll()
		        .anyRequest().authenticated()
		        .and()
		        .addFilter(filter)	
				.addFilter(new JWTAuthorisationFilter(authenticationManager()));				
				// this disables session creation on Spring Security
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization"));
		configuration.setExposedHeaders(Arrays.asList("Authorization"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
}
