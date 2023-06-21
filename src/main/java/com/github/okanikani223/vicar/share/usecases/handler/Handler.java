package com.github.okanikani223.vicar.share.usecases.handler;

public interface Handler<I, O> {
    O handle(I input);
}
