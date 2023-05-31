package com.github.okanikani223.vicar.api.download.handler;

import com.github.okanikani223.vicar.api.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DownloadHandler implements Handler<String, String> {
    @Value("${vicar.download.messaging.destination}")
    private String destination;
    private final Logger logger;
    private final JmsTemplate jmsTemplate;

    @Override
    public String handle(String requestUrl) {
        jmsTemplate.convertAndSend(destination, requestUrl);

        return UUID.randomUUID().toString();
    }
}
