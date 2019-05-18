package vn.com.fpt.clt.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	
	Authentication getAuthentication();
	
	UserCredentials getUserCredentials();
}
