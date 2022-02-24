package danieladamzoltan.userservice.validation;

import danieladamzoltan.userservice.persistence.models.request.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegisterRequest user = (RegisterRequest) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}