package io.matrix.toolkit.validation;

import com.google.common.base.Strings;
import io.matrix.toolkit.constant.RegexConstant;
import io.matrix.toolkit.validation.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
import java.util.regex.Pattern;

/**
 * @author Noa Swartz
 * @date 2020/09/04
 */
public class EmailValidator  implements ConstraintValidator<Email, String> {

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.EMAIL_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Strings.isNullOrEmpty(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}
