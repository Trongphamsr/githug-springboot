package vn.com.fpt.clt.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import vn.com.fpt.clt.entities.Project;

@Component
public class IsCorrectProjectValidator implements ConstraintValidator<IsCorrect, Project> {

	@Override
	public void initialize(IsCorrect constraintAnnotation) {

	}

	@Override
	public boolean isValid(Project project, ConstraintValidatorContext context) {
		if (null == project)
			return true;

		if (StringUtils.isBlank(project.getProjectName())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Project Name is required").addPropertyNode("projectName")
					.addConstraintViolation();
			return false;
		} else if (project.getProjectName().length() < 4 || project.getProjectName().length() > 50) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Project Name must be between 2 and 50 characters").addPropertyNode("projectName")
					.addConstraintViolation();
			return false;
		}

		return true;
	}

}
