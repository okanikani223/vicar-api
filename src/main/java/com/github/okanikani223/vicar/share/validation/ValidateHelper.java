package com.github.okanikani223.vicar.share.validation;

import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.download.domain.errors.InValidDataException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ValidateHelper {
    public static <T> void validate(T object, Validator<T> validator) {
        var violations = validator.validate(object);
        if (violations.isValid()) return;

        throw new InValidDataException(violations);
    }
}
