package com.github.okanikani223.vicar.api.download.handler;

import com.github.okanikani223.vicar.api.download.vo.AddDownloadRequestInput;
import com.github.okanikani223.vicar.api.download.vo.AddDownloadRequestOutput;
import com.github.okanikani223.vicar.api.handler.AbstractHandler;
import com.github.okanikani223.vicar.web.errors.InValidInputException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddDownloadRequestHandler extends AbstractHandler<AddDownloadRequestInput, AddDownloadRequestOutput> {
    private final Logger logger;
    private final DownloadHandler downloadHandler;

    @Override
    protected void validate(AddDownloadRequestInput input) {
        var violations = AddDownloadRequestInput.validator.validate(input);
        if (violations.isValid()) return;

        logger.debug(input.toString());

        throw new InValidInputException(violations);
    }

    @Override
    protected AddDownloadRequestOutput process(AddDownloadRequestInput input) {
        var receivedId = downloadHandler.handle(input.requestUrl());

        return new AddDownloadRequestOutput(receivedId);
    }
}
