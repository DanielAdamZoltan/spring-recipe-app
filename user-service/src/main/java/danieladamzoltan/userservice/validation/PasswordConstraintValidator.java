package danieladamzoltan.userservice.validation;

import com.google.common.base.Joiner;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(final ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext constraintValidatorContext) {

        LengthRule lengthRule = new LengthRule(8, 30);
        CharacterCharacteristicsRule ruleOne = new CharacterCharacteristicsRule();
        ruleOne.setNumberOfCharacteristics(3);
        ruleOne.getRules().add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
        ruleOne.getRules().add(new CharacterRule(EnglishCharacterData.Digit, 1));
        ruleOne.getRules().add(new CharacterRule(EnglishCharacterData.Special, 1));
        ruleOne.getRules().add(new CharacterRule(EnglishCharacterData.Alphabetical, 3));
        WhitespaceRule whitespaceRule = new WhitespaceRule();

        final PasswordValidator validator = new PasswordValidator(lengthRule, ruleOne, whitespaceRule);
        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }
}
