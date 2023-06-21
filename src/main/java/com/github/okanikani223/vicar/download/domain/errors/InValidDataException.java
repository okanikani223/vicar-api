package com.github.okanikani223.vicar.download.domain.errors;

import am.ik.yavi.core.ConstraintViolation;

import java.util.List;
import java.util.stream.Collectors;

public class InValidDataException extends RuntimeException{
    public InValidDataException(String message) {
        super(message);
    }

    public InValidDataException(List<ConstraintViolation> violations) {
        super(violations.stream().map(ConstraintViolation::message).collect(Collectors.joining()));
    }
}
