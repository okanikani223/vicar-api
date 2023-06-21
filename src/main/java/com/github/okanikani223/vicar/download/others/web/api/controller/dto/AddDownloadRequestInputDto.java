package com.github.okanikani223.vicar.download.others.web.api.controller.dto;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.download.domain.errors.InValidDataException;
import com.github.okanikani223.vicar.share.Validatable;
import lombok.Data;

@Data
public class AddDownloadRequestInputDto implements Validatable {
    private static final Validator<AddDownloadRequestInputDto> validator = ValidatorBuilder.<AddDownloadRequestInputDto>of()
            .constraint(AddDownloadRequestInputDto::getRequestUrl, "requestUrl", c -> c.notBlank().pattern("^[http|https].+$").url())
            .build();
    private String requestUrl;

    @Override
    public void validate() {
        var violations = validator.validate(this);
        if (violations.isValid()) return;

        throw new InValidDataException(violations);
    }
}
