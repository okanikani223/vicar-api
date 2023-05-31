package com.github.okanikani223.vicar.web.errors;

import am.ik.yavi.core.ConstraintViolation;

import java.util.List;
import java.util.stream.Collectors;

public class InValidInputException extends RuntimeException{
    public InValidInputException(String message) {
        super(message);
    }

    public InValidInputException(List<ConstraintViolation> violations) {
        super(violations.stream().map(ConstraintViolation::message).collect(Collectors.joining()));
    }
}
