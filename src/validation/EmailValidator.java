package validation;

import validation.exceptions.ValidationException;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");


    @Override
    public void validate(String value) throws ValidationException {
        if(value == null || value.trim().length() == 0)
            throw new ValidationException("Email address cannot be empty");

        if(!EMAIL_PATTERN.matcher(value).matches())
            throw new ValidationException("Invalid email address " + value);
    }

}
