package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.validation.annotation.ValidPage;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null 이면 false 처리하거나 true 처리 (필수 여부에 따라 결정)
        if (value == null) {
            return false;
        }
        return value >= 1;
    }
}
