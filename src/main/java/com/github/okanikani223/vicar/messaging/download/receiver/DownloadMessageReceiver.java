package com.github.okanikani223.vicar.messaging.download.receiver;

import com.github.okanikani223.vicar.messaging.download.receiver.dto.DownloadRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DownloadMessageReceiver {
    private final Logger logger;
    private final ExecutorService executorService;

    @JmsListener(destination = "${vicar.download.messaging.destination}", containerFactory = "vicarMessagingServiceFactory")
    public void receiveMessage(DownloadRequest request) {
        logger.info(request.toString());
        logger.info(executorService.toString());
        IntStream.range(0, 100).forEach(i -> executorService.execute(() -> {
            System.out.println(i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
