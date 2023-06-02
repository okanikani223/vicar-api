package com.github.okanikani223.vicar.messaging.download.receiver.dto;

import com.github.okanikani223.vicar.api.download.domain.model.vo.RequestUrl;

public record DownloadRequest(String requestId, RequestUrl requestUrl) {}
