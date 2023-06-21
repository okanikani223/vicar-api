package com.github.okanikani223.vicar.download.others.services;

import com.github.okanikani223.vicar.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.download.domain.services.DownloadRequestTransmissionService;
import com.github.okanikani223.vicar.download.others.background.messaging.receiver.dto.DownloadRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DownloadRequestTransmissionServiceImplByJms implements DownloadRequestTransmissionService {
    @Value("${vicar.download.messaging.destination}")
    private String destination;
    private final Logger logger;
    private final JmsTemplate jmsTemplate;

    @Override
    public String transmit(RequestUrl requestUrl) {
        var receivedNo = UUID.randomUUID().toString();
        var downloadRequest = new DownloadRequestDto(receivedNo, requestUrl.toString());

        logger.debug(downloadRequest.toString());

        jmsTemplate.convertAndSend(destination, downloadRequest);

        return receivedNo;
    }
}
