package com.github.okanikani223.vicar.download.others.background.messaging.receiver;

import com.github.okanikani223.vicar.download.others.background.messaging.receiver.dto.DownloadRequestDto;
import com.github.okanikani223.vicar.download.usecases.DownloadExecuteHandler;
import com.github.okanikani223.vicar.download.usecases.dto.DownloadExecuteHandlerInput;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadMessageReceiver {
    private final DownloadExecuteHandler downloadExecuteHandler;

    @JmsListener(destination = "${vicar.download.messaging.destination}", containerFactory = "vicarMessagingServiceFactory")
    public void receiveMessage(DownloadRequestDto request) throws Exception {
        var handlerInput = new DownloadExecuteHandlerInput(request.receivedNo(), request.requestUrl());
        downloadExecuteHandler.handle(handlerInput);
    }
}
