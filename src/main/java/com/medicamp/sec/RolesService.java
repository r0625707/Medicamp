package com.medicamp.sec;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class RolesService {

	private YAMLRoles yaml;

	public RolesService() {

		 ObjectMapper mapper = new ObjectMapper(new YAMLFactory());       
	     try {
	    	
	    
	    	File file = new File( this.getClass().getResource( "/roles.yml" ).toURI() );
			yaml = mapper.readValue(file, YAMLRoles.class);
			
		} catch (IOException | URISyntaxException e) {
		
			e.printStackTrace();
		}
	}

	public YAMLRoles getYaml() {
		return yaml;
	}

	public void setYaml(YAMLRoles yaml) {
		this.yaml = yaml;
	}

	public static class YAMLRoles {

		private Map<String, List<String>> roles;

		public YAMLRoles() {

		}

		public Map<String, List<String>> getRoles() {
			return roles;
		}

		public void setRoles(Map<String, List<String>> roles) {
			this.roles = roles;
		}

		public static class Roles {

			private List<String> methods;

			public Roles() {

			}

			public List<String> getMethods() {
				return methods;
			}

			public void setMethods(List<String> methods) {
				this.methods = methods;
			}

		}

		public List<String> getMethodsForRole(String string) {
			List<String> result = roles.get(string);
			if (result != null) {return result;}
			else return new ArrayList<String>();
		}

	}

	public List<String> getMethodsForRole(String string) {
		return yaml.getMethodsForRole(string);
		
	}

}
