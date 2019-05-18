package com.trungtamjava.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.trungtamjava.model.UserDTO;
// trong vadidator dung de tao bean trong vadidator
@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clszz) {
		// kt xem phai medel user k
		return UserDTO.class.isAssignableFrom(clszz);
	}

	public void validate(Object target, Errors errors) {
		
		UserDTO user= (UserDTO) target;
		
		if(user.getName()==null || user.getName().length()==0){
			errors.rejectValue("name", "field.required");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required");
//		if(user.getPhone()<6 || user.getPhone() >12){
//			errors.rejectValue("phone", "phone.invalid");
//		}
	}
}
