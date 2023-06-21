package com.github.okanikani223.vicar.download.usecases.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.download.domain.errors.InValidDataException;

public record AddDownloadRequestHandlerInput(String requestUrl){
    public static final Validator<AddDownloadRequestHandlerInput> validator = ValidatorBuilder.<AddDownloadRequestHandlerInput>of()
            .constraint(AddDownloadRequestHandlerInput::requestUrl, "requestUrl", c -> c.notBlank().url())
            .build();

    public AddDownloadRequestHandlerInput(String requestUrl) {
        this.requestUrl = requestUrl;
        var violations = validator.validate(this);
        if (violations.isValid()) return;

        throw new InValidDataException(violations);
    }
}
