package pl.coderslab.projektklinika.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.services.UserService;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","NonEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name","NonEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name","NonEmpty");
        final Pattern emailRegexPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$");
                // regex z neta (sprawdzajacy poprawność maili i gmaili //

        if(!emailRegexPattern.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email","Email.invalid");
        }
        if(userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email","Email.exists");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm","PasswordConfirm.invalid");
        }
        if(user.getPassword().length() < 8) {
            errors.rejectValue("password","Password.tooShort");
            errors.rejectValue("passwordConfirm","Password.tooShort");
        }
        if(user.getPesel().length() != 11) {
            errors.rejectValue("pesel","Pesel.invalid_length");
        } else {
            int[] w = {1,3,7,9,1,3,7,9,1,3};
            int s = 0;
            for(int i=0;i<w.length;++i) {
                s += w[i] * (user.getPesel().charAt(i) - 48);
            }
            if((s%10)+48 != user.getPesel().charAt(10))
            {
                errors.rejectValue("pesel", "Pesel.invalid");
            }
        }
    }
}
