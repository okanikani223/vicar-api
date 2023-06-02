package com.github.okanikani223.vicar.api.download.usecases;

import com.github.okanikani223.vicar.api.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.api.download.domain.services.DownloadExecutorService;
import com.github.okanikani223.vicar.api.download.usecases.dto.AddDownloadRequestHandlerInput;
import com.github.okanikani223.vicar.api.download.usecases.dto.AddDownloadRequestHandlerOutput;
import com.github.okanikani223.vicar.api.handler.InputValificationRequiredHandler;
import com.github.okanikani223.vicar.stereotype.Usecase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@Usecase
@RequiredArgsConstructor
public class AddDownloadRequestHandler extends InputValificationRequiredHandler<AddDownloadRequestHandlerInput, AddDownloadRequestHandlerOutput> {
    private final Logger logger;
    private final DownloadExecutorService downloadExecutorService;

    @Override
    protected AddDownloadRequestHandlerOutput process(AddDownloadRequestHandlerInput input) {
        var receivedId = downloadExecutorService.execute(new RequestUrl(input.requestUrl()));

        return new AddDownloadRequestHandlerOutput(receivedId);
    }
}
