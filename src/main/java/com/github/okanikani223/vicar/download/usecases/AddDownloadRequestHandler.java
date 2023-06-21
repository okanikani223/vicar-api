package com.github.okanikani223.vicar.download.usecases;

import com.github.okanikani223.vicar.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.download.domain.services.DownloadRequestTransmissionService;
import com.github.okanikani223.vicar.download.usecases.dto.AddDownloadRequestHandlerInput;
import com.github.okanikani223.vicar.download.usecases.dto.AddDownloadRequestHandlerOutput;
import com.github.okanikani223.vicar.share.bind.stereotype.Usecase;
import com.github.okanikani223.vicar.share.usecases.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@Usecase
@RequiredArgsConstructor
public class AddDownloadRequestHandler implements Handler<AddDownloadRequestHandlerInput, AddDownloadRequestHandlerOutput> {
    private final Logger logger;
    private final DownloadRequestTransmissionService downloadRequestTransmissionService;

    @Override
    public AddDownloadRequestHandlerOutput handle(AddDownloadRequestHandlerInput input) {
        logger.debug(input.toString());

        var requestUrl = new RequestUrl(input.requestUrl());
        requestUrl.validate();

        var receivedNo = downloadRequestTransmissionService.transmit(requestUrl);

        return new AddDownloadRequestHandlerOutput(receivedNo);
    }
}
