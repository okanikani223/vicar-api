package com.github.okanikani223.vicar.api.download.domain.services;

import com.github.okanikani223.vicar.api.download.domain.model.vo.RequestUrl;

public interface DownloadExecutorService {
    String execute(RequestUrl requestUrl);
}
