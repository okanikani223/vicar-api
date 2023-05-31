package com.github.okanikani223.vicar.web.controller.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import lombok.Data;

@Data
public class AddDownloadRequestInputDto {
    public static final Validator<AddDownloadRequestInputDto> validator = ValidatorBuilder.<AddDownloadRequestInputDto>of()
            .constraint(AddDownloadRequestInputDto::getRequestUrl, "requestUrl", c -> c.notBlank().pattern("^[http|https].+$").url())
            .build();
    private String requestUrl;
}
