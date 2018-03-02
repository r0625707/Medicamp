package com.medicamp.sec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	

	@GetMapping("login")
	public String login(@RequestBody Credentials credentials) {

		return "Welcome " + credentials.getLogin();
	}

	@GetMapping()
	public String welcome() {

		return "Welcome to the medicamp API!";
	}
	
	class Credentials{
		
		String login , password;
		
		

		public Credentials() {
			super();
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
	}

}
