package com.github.okanikani223.vicar.download.domain.model.vo;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.download.domain.model.entities.DownloadItem;
import com.github.okanikani223.vicar.share.validation.ValidateHelper;

public record DownloadRequest(String receivedNo, RequestUrl requestUrl) {
    private static final Validator<DownloadRequest> validator = ValidatorBuilder.<DownloadRequest>of()
            .constraint(DownloadRequest::receivedNo, "id", CharSequenceConstraint::notBlank)
            .nest(DownloadRequest::requestUrl, "requestUrl", RequestUrl.validator)
            .build();

    public DownloadRequest(String receivedNo, RequestUrl requestUrl) {
        this.receivedNo = receivedNo;
        this.requestUrl = requestUrl;

        ValidateHelper.validate(this, validator);
    }

    public DownloadItem toItem() {
        return new DownloadItem(this);
    }
}
