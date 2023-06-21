package com.github.okanikani223.vicar.download.domain.model.vo;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.github.okanikani223.vicar.download.domain.errors.InValidDataException;
import com.github.okanikani223.vicar.share.Utility;
import com.github.okanikani223.vicar.share.Validatable;
import lombok.Value;
import lombok.experimental.Accessors;

import java.net.URI;
import java.util.regex.Pattern;

@Value
@Accessors(fluent = true)
public class RequestUrl implements Validatable {
    private static final Pattern urlPartsPattern = Utility.pattern().urlPartsPattern();
    String url;
    String protocol;
    String domain;
    String file;
    String fileName;
    String extension;

    public RequestUrl(String url) {
        this.url = url;
        this.protocol = extractProtocol(url);
        this.domain = extractDomain(url);
        this.file = extractFile(url);
        this.fileName = extractFileName(url);
        this.extension = extractExtension(url);

    }
    public static final Validator<RequestUrl> validator = ValidatorBuilder.<RequestUrl>of()
            .constraint(RequestUrl::url, "url", c -> c.notBlank().url())
            .failFast(true)
            .build();

    @Override
    public void validate() {
        var violations = validator.validate(this);
        if (violations.isValid()) return;

        throw new InValidDataException(violations);
    }

    public boolean isHttpOrHttps() {
        return protocol.toLowerCase().matches("[http|https]");
    }

    public URI toURI() {
        return URI.create(url);
    }

    @Override
    public String toString() {
        return url;
    }

    private String extractProtocol(String url) {
        return extractParts(url, "scheme");
    }

    private String extractDomain(String url) {
        return extractParts(url, "authority");
    }

    private String extractFile(String url) {
        return extractParts(url, "file");
    }

    private String extractFileName(String url) {
        var fullFileName = extractFile(url);

        if (!fullFileName.contains(".")) return "";

        return fullFileName.split("\\.")[0];
    }

    private String extractExtension(String url) {
        var fullFileName = extractFile(url);

        if (!fullFileName.contains(".")) return "";

        return fullFileName.split("\\.")[1];
    }

    private String extractParts(String base, String groupName) {
        if (base == null || base.isBlank()) return "";
        if (groupName == null || groupName.isBlank()) return "";

        var matcher = urlPartsPattern.matcher(base);
        if (!matcher.matches()) return "";

        return matcher.group(groupName);
    }
}
