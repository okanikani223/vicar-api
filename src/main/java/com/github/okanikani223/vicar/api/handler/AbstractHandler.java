package com.github.okanikani223.vicar.api.handler;

public abstract class AbstractHandler<I, O> implements Handler<I, O> {

    protected abstract void validate(I input);

    protected abstract O process(I input);

    @Override
    public O handle(I input) {
        validate(input);

        return process(input);
    }
}
