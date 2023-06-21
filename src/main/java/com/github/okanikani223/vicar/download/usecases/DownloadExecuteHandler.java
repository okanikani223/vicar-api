package com.github.okanikani223.vicar.download.usecases;

import com.github.okanikani223.vicar.download.domain.model.vo.DownloadRequest;
import com.github.okanikani223.vicar.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.download.domain.services.DownloadRequestReceptionService;
import com.github.okanikani223.vicar.download.usecases.dto.DownloadExecuteHandlerInput;
import com.github.okanikani223.vicar.share.bind.stereotype.Usecase;
import com.github.okanikani223.vicar.share.usecases.handler.NoReturnValueHandler;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Usecase
@RequiredArgsConstructor
public class DownloadExecuteHandler implements NoReturnValueHandler<DownloadExecuteHandlerInput> {
    private final DownloadRequestReceptionService downloadRequestReceptionService;

    @Override
    public void handle(DownloadExecuteHandlerInput input) throws Exception {
        Objects.requireNonNull(input);
        var downloadRequest = new DownloadRequest(input.receivedNo(), new RequestUrl(input.requestUrl()));
        downloadRequestReceptionService.receive(downloadRequest);
    }
}
