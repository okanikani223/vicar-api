package com.github.okanikani223.vicar.api.handler;

import com.github.okanikani223.vicar.share.Validatable;

public abstract class InputValificationRequiredHandler<I extends Validatable, O> implements Handler<I, O> {
    protected abstract O process(I input);

    @Override
    public O handle(I input) {
        input.validate();

        return process(input);
    }
}
