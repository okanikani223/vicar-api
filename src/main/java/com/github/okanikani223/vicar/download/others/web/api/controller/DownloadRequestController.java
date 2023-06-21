package com.github.okanikani223.vicar.download.others.web.api.controller;

import com.github.okanikani223.vicar.download.others.web.api.controller.dto.AddDownloadRequestInputDto;
import com.github.okanikani223.vicar.download.others.web.api.controller.dto.AddDownloadRequestOutputDto;
import com.github.okanikani223.vicar.download.usecases.AddDownloadRequestHandler;
import com.github.okanikani223.vicar.download.usecases.dto.AddDownloadRequestHandlerInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DownloadRequestController {
    private final AddDownloadRequestHandler addDownloadRequestHandler;

    @PostMapping(value = "/downloadRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
    public AddDownloadRequestOutputDto add(@RequestBody AddDownloadRequestInputDto input) {
        input.validate();

        var handlerInput = new AddDownloadRequestHandlerInput(input.getRequestUrl());
        var handlerOutput = addDownloadRequestHandler.handle(handlerInput);
        var outputDto = new AddDownloadRequestOutputDto();
        outputDto.setReceivedNo(handlerOutput.receivedNo());

        return outputDto;
    }
}
