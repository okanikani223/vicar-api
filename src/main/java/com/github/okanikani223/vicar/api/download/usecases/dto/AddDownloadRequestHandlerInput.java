package com.github.okanikani223.vicar.api.download.usecases.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.api.download.others.web.errors.InValidInputException;
import com.github.okanikani223.vicar.share.Validatable;

public record AddDownloadRequestHandlerInput(String requestUrl) implements Validatable {
    public static final Validator<AddDownloadRequestHandlerInput> validator = ValidatorBuilder.<AddDownloadRequestHandlerInput>of()
            .constraint(AddDownloadRequestHandlerInput::requestUrl, "requestUrl", c -> c.notBlank().url())
            .build();

    @Override
    public void validate() {
        var violations = validator.validate(this);
        if (violations.isValid()) return;

        throw new InValidInputException(violations);
    }
}
