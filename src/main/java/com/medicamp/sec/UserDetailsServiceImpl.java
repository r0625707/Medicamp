package com.medicamp.sec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medicamp.db.UserRepository;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository users;

	public UserDetailsServiceImpl(UserRepository userRepo) {
		this.users = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		com.medicamp.model.User user = users.findOne(login);
		if (user == null) {
			throw new UsernameNotFoundException(login);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Integer.toString(user.getRole())));
		
		return new User(user.getLogin(), user.getPassword(), authorities);
	}

	
}