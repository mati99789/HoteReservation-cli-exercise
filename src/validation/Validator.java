package validation;

import validation.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T value) throws ValidationException;
}
