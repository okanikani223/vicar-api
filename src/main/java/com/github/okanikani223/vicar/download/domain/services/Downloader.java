package com.github.okanikani223.vicar.download.domain.services;

public interface Downloader<F, T> {
    void execute() throws Exception;
}
