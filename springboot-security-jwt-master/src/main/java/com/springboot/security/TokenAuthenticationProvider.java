package com.springboot.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.model.AuthenticationToken;
import com.springboot.model.LoginUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final String SECURITY_KEY = "!@asdsadJAS780";

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		AuthenticationToken authenticationToken = (AuthenticationToken) usernamePasswordAuthenticationToken;
		String token = authenticationToken.getToken();
		Claims claim = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
		return new LoginUserDetails(claim.getSubject(), Long.parseLong((String) claim.get("Id")), token,
				AuthorityUtils.commaSeparatedStringToAuthorityList((String) claim.get("role")));
	}
}
