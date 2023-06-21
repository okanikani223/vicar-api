package com.github.okanikani223.vicar.download.usecases.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.share.validation.ValidateHelper;

public record DownloadExecuteHandlerInput(String receivedNo, String requestUrl) {
    private static final Validator<DownloadExecuteHandlerInput> validator = ValidatorBuilder.<DownloadExecuteHandlerInput>of()
            .constraint(DownloadExecuteHandlerInput::receivedNo, "receivedNo", CharSequenceConstraint::notBlank)
            .constraint(DownloadExecuteHandlerInput::requestUrl, "requestUrl", c -> c.notBlank().url())
            .build();

    public DownloadExecuteHandlerInput(String receivedNo, String requestUrl) {
        this.receivedNo = receivedNo;
        this.requestUrl = requestUrl;

        ValidateHelper.validate(this, validator);
    }
}
