package com.github.okanikani223.vicar.messaging.receiver;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadMessageReceiver {
    private final Logger logger;

    @JmsListener(destination = "${vicar.download.messaging.destination}", containerFactory = "vicarMessagingServiceFactory")
    public void receiveMessage(String requestUrl) {
        logger.info(requestUrl);
    }
}
