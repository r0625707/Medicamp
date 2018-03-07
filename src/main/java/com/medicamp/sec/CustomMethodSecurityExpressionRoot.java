package com.medicamp.sec;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
		implements MethodSecurityExpressionOperations {

	private Object filterObject;
	private Object returnObject;
	private RolesService roles;

	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
		this.roles = new RolesService();
	}

	public boolean isAuthorisedMethod(String methodName) {
		
		if (this.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_0"))) {return true;}

		for (GrantedAuthority a : this.getAuthentication().getAuthorities()) {

			for (String m : roles.getMethodsForRole(a.toString())) {

				if (m.equals(methodName)) {
					return true;
				}
			}

		}

		return false;
	}

	public boolean isAuthorisedMethodAndUser(String methodName, String login) {
		if (this.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_0"))) {return true;}
		
		if (getPrincipal().equals(login)) {
			for (GrantedAuthority a : this.getAuthentication().getAuthorities()) {

				for (String m : roles.getMethodsForRole(a.toString())) {

					if (m.equals(methodName)) {
						return true;
					}
				}

			}
		}

		return false;
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return this;
	}

	@Override
	public void setFilterObject(Object obj) {
		this.filterObject = obj;
	}

	@Override
	public void setReturnObject(Object obj) {
		this.returnObject = obj;
	}

}