package com.github.okanikani223.vicar.download.domain.services;

import com.github.okanikani223.vicar.download.domain.model.vo.DownloadRequest;

public interface DownloadRequestReceptionService {
    void receive(DownloadRequest downloadRequest) throws Exception;
}
