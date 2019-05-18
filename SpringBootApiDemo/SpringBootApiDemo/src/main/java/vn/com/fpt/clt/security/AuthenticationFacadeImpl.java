package vn.com.fpt.clt.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {

		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public UserCredentials getUserCredentials() {

		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return object instanceof UserCredentials ? (UserCredentials) object : new UserCredentials();
	}

}
