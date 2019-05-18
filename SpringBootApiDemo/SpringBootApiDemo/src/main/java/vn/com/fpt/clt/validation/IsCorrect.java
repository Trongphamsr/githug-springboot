package vn.com.fpt.clt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IsCorrectProjectValidator.class, IsCorrectUserValidator.class })
public @interface IsCorrect {

	String message() default "Data is incorrect";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
