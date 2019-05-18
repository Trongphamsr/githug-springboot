package vn.com.fpt.clt.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import vn.com.fpt.clt.security.AuthenticationFacade;

/**
 * 
 * @author ChienNV7
 * 
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Bean
	public AuditorAware<String> auditorProvider() {

		return () -> Optional
				.ofNullable(authenticationFacade.getUserCredentials().getUsername());
	}
}
