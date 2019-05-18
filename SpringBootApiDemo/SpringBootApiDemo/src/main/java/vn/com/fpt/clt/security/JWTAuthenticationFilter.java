package vn.com.fpt.clt.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.com.fpt.clt.dto.UserDto;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public JWTAuthenticationFilter(AuthenticationManager authManager) {
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					userDto.getUsername(), userDto.getPassword(), Collections.emptyList()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDto user = (UserDto) authResult.getPrincipal();
		String userJson = new ObjectMapper().writeValueAsString(user);

		UserCredentials credentials = new UserCredentials();
		credentials.setId(user.getId());
		credentials.setUsername(user.getUsername());
		credentials.setLstRoleName(user.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList()));
		String credentialsJson = new ObjectMapper().writeValueAsString(credentials);

		// Write Authorization to Headers of Response.
		String token = JWT.create().withSubject(credentialsJson)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(HMAC512(SecurityConstants.SECRET.getBytes()));

		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.getWriter().write(userJson);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		super.unsuccessfulAuthentication(request, response, failed);

	}

}
