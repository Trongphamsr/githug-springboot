package vn.com.fpt.clt.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.fpt.clt.dto.UserDto;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.security.AuthenticationFacade;
import vn.com.fpt.clt.services.UserService;

/**
 * 
 * @author ChienNV7
 *
 */

@RepositoryRestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) {

		try {
			return ResponseEntity.ok(userService.create(userDto));
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			/*if (e instanceof MethodArgumentNotValidException) {
				MessageDto errorValidate = MessageUtil.notValid(((MethodArgumentNotValidException) e).getBindingResult()
						.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst()
						.orElse(e.getMessage()));

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorValidate);
			}*/

			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<?> findAll(Boolean active, Pageable pageable) {

		try {
			Page<UserDto> jsonBody = userService.findAll(active, pageable);

			return ResponseEntity.ok(jsonBody);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/findUser")
	public ResponseEntity<?> findUser() {

		try {
			UserDto user = userService.findOne(authenticationFacade.getUserCredentials().getId());

			return ResponseEntity.ok(user);
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
