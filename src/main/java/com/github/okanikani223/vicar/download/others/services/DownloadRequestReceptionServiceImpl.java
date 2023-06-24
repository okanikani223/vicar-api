package com.github.okanikani223.vicar.download.others.services;

import com.github.okanikani223.vicar.download.domain.model.repositories.DownloadItemRepository;
import com.github.okanikani223.vicar.download.domain.model.vo.DownloadRequest;
import com.github.okanikani223.vicar.download.domain.services.DownloadRequestReceptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
public class DownloadRequestReceptionServiceImpl implements DownloadRequestReceptionService {
    private final ExecutorService executorService;
    private final Logger logger;
    private final DownloadItemRepository downloadItemRepository;

    @Override
    public void receive(DownloadRequest downloadRequest) throws Exception {
        logger.debug(downloadRequest.toString());
        downloadItemRepository.save(downloadRequest.toItem());
        executorService.submit(createDownloader(downloadRequest));
    }

    private Runnable createDownloader(DownloadRequest downloadRequest) {
        var url = downloadRequest.requestUrl();

        return () -> {
            logger.info(url.toString());
        };
    }
}
