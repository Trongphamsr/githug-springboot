package vn.com.fpt.clt.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import vn.com.fpt.clt.entities.User;

@Component
public class IsCorrectUserValidator implements ConstraintValidator<IsCorrect, User> {

	@Override
	public void initialize(IsCorrect constraintAnnotation) {

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		
		if (null == user)
			return true;

		if (StringUtils.isBlank(user.getUsername())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username is required").addPropertyNode("username")
					.addConstraintViolation();
			return false;
		} else if (user.getUsername().length() < 4 || user.getUsername().length() > 20) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username must be between 2 and 20 characters")
					.addPropertyNode("username").addConstraintViolation();
			return false;
		}

		return true;
	}

}
