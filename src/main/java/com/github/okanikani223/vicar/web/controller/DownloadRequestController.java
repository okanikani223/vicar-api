package com.github.okanikani223.vicar.web.controller;

import com.github.okanikani223.vicar.api.download.handler.AddDownloadRequestHandler;
import com.github.okanikani223.vicar.api.download.vo.AddDownloadRequestInput;
import com.github.okanikani223.vicar.web.controller.dto.AddDownloadRequestInputDto;
import com.github.okanikani223.vicar.web.controller.dto.AddDownloadRequestOutputDto;
import com.github.okanikani223.vicar.web.errors.InValidInputException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DownloadRequestController {
    private final Logger logger;
    private final AddDownloadRequestHandler addDownloadRequestHandler;

    @PostMapping(value = "/downloadRequests", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AddDownloadRequestOutputDto add(@RequestBody AddDownloadRequestInputDto input) {
        validate(input);

        var handlerInput = new AddDownloadRequestInput(input.getRequestUrl());
        var handlerOutput = addDownloadRequestHandler.handle(handlerInput);
        var outputDto = new AddDownloadRequestOutputDto();
        outputDto.setReceivedId(handlerOutput.receivedId());

        return outputDto;
    }

    public void validate(AddDownloadRequestInputDto input) {
        var violations = AddDownloadRequestInputDto.validator.validate(input);
        if (violations.isValid()) return;

        logger.debug(input.toString());

        throw new InValidInputException(violations);
    }
}
