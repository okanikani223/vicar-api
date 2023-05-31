package com.github.okanikani223.vicar.api.handler;

public interface Handler<I, O> {
    O handle(I input);
}
