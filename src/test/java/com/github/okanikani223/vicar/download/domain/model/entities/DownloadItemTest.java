package com.github.okanikani223.vicar.download.domain.model.entities;

import com.github.okanikani223.vicar.download.domain.model.vo.RequestUrl;
import com.github.okanikani223.vicar.download.domain.errors.InValidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DownloadItemTest {

    @Test
    void validate_NullAsId_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem(null, new RequestUrl("http://localhost:8080/test.txt"), "", 0, 0));

        assertEquals("\"id\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_EmptyAsId_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("", new RequestUrl("http://localhost:8080/test.txt"), "", 0, 0));

        assertEquals("\"id\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_BlankOnlyAsId_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("     ", new RequestUrl("http://localhost:8080/test.txt"), "", 0, 0));

        assertEquals("\"id\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_NullAsUrl_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("test", null, "", 0, 0));

        assertEquals("\"requestUrl\" must not be null", actual.getMessage());
    }

    @Test
    void validate_EmptyAsUrl_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("test", new RequestUrl(""), "", 0, 0));

        assertEquals("\"requestUrl.url\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_BlankOnlyAsUrl_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("test", new RequestUrl("     "), "", 0, 0));

        assertEquals("\"requestUrl.url\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_InValidUrl_ThrowsInValidInputException() {
        var actual = assertThrows(InValidDataException.class, () -> new DownloadItem("test", new RequestUrl("test.txt"), "", 0, 0));

        assertEquals("\"requestUrl.url\" must be a valid URL", actual.getMessage());
    }
}