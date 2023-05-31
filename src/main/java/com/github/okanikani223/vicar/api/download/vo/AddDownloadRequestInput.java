package com.github.okanikani223.vicar.api.download.vo;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;

public record AddDownloadRequestInput(String requestUrl) {
    public static final Validator<AddDownloadRequestInput> validator = ValidatorBuilder.<AddDownloadRequestInput>of()
            .constraint(AddDownloadRequestInput::requestUrl, "requestUrl", c -> c.notBlank().url())
            .build();
}
