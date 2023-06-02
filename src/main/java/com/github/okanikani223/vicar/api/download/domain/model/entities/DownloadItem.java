package com.github.okanikani223.vicar.api.download.domain.model.entities;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.constraint.base.NumericConstraintBase;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.api.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.api.download.others.web.errors.InValidInputException;
import com.github.okanikani223.vicar.share.Validatable;
import com.github.okanikani223.vicar.share.domain.model.entities.Entity;

public class DownloadItem extends Entity<String> implements Validatable {
    private static final Validator<DownloadItem> validator = ValidatorBuilder.<DownloadItem>of()
            .constraint(DownloadItem::id, "id", CharSequenceConstraint::notBlank)
            .nest(DownloadItem::requestUrl, "requestUrl", RequestUrl.validator)
            .constraint(DownloadItem::status, "status", Constraint::notNull)
            .constraint(DownloadItem::downloadedSize, "downloadedSize", NumericConstraintBase::positiveOrZero)
            .constraint(DownloadItem::contentSize, "contentSize", NumericConstraintBase::positiveOrZero)
            .failFast(true)
            .build();
    private final RequestUrl requestUrl;
    private final String status;
    private final long downloadedSize;
    private final long contentSize;

    public DownloadItem(String id, RequestUrl requestUrl, String status, long downloadedSize, long contentSize) {
        super(id);
        this.requestUrl = requestUrl;
        this.status = status;
        this.downloadedSize = downloadedSize;
        this.contentSize = contentSize;
    }

    public RequestUrl requestUrl() {
        return requestUrl;
    }

    public DownloadItem withRequestUrl(RequestUrl url){
        return this.requestUrl.equals(url) ? this : new DownloadItem(id(), url, status, downloadedSize, contentSize);
    }

    public String status() {
        return status;
    }

    public DownloadItem withStatus(String status){
        return this.status.equals(status) ? this : new DownloadItem(id(), requestUrl, status, downloadedSize, contentSize);
    }

    public long downloadedSize() {
        return downloadedSize;
    }

    public DownloadItem withDownloadedSize(long downloadedSize){
        return this.downloadedSize == downloadedSize ? this : new DownloadItem(id(), requestUrl, status, downloadedSize, contentSize);
    }

    public long contentSize() {
        return contentSize;
    }

    public DownloadItem withContentSize(long contentSize){
        return this.contentSize == contentSize ? this : new DownloadItem(id(), requestUrl, status, downloadedSize, contentSize);
    }

    @Override
    public void validate() {
        var violations = validator.validate(this);
        if (violations.isValid()) return;

        throw new InValidInputException(violations);
    }
}
