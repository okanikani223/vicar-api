package com.github.okanikani223.vicar.api.download.others;

import com.github.okanikani223.vicar.api.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.api.download.domain.services.DownloadExecutorService;
import com.github.okanikani223.vicar.messaging.download.receiver.dto.DownloadRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DownloadExecutorServiceImplByJms implements DownloadExecutorService {
    @Value("${vicar.download.messaging.destination}")
    private String destination;
    private final Logger logger;
    private final JmsTemplate jmsTemplate;

    @Override
    public String execute(RequestUrl requestUrl) {
        var requestId = UUID.randomUUID().toString();
        var downloadRequest = new DownloadRequest(requestId, requestUrl);
        jmsTemplate.convertAndSend(destination, downloadRequest);
        return requestId;
    }
}
