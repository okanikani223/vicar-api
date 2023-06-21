package com.github.okanikani223.vicar.share.usecases.handler;

public interface NoReturnValueHandler<I>{
    void handle(I input) throws Exception;
}
