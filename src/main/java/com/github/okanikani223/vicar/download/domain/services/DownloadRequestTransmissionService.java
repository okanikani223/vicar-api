package com.github.okanikani223.vicar.download.domain.services;

import com.github.okanikani223.vicar.download.domain.model.vo.RequestUrl;

public interface DownloadRequestTransmissionService {
    String transmit(RequestUrl requestUrl);
}
