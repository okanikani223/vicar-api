package com.github.okanikani223.vicar.api.download.domain.model.vo;

import com.github.okanikani223.vicar.api.download.others.web.errors.InValidInputException;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class RequestUrlTest {

    @Test
    void validate_NullAsUrl_ThrowsInValidInputException() {
        var requestUrl = new RequestUrl(null);

        var actual = assertThrows(InValidInputException.class, requestUrl::validate);

        assertEquals("\"url\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_EmptyAsUrl_ThrowsInValidInputException() {
        var requestUrl = new RequestUrl("");

        var actual = assertThrows(InValidInputException.class, requestUrl::validate);

        assertEquals("\"url\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_BlankAsUrl_ThrowsInValidInputException() {
        var requestUrl = new RequestUrl("     ");

        var actual = assertThrows(InValidInputException.class, requestUrl::validate);

        assertEquals("\"url\" must not be blank", actual.getMessage());
    }

    @Test
    void validate_InValidUrl_ThrowsInValidInputException() {
        var requestUrl = new RequestUrl("htp://example.com");

        var actual = assertThrows(InValidInputException.class, requestUrl::validate);

        assertEquals("\"url\" must be a valid URL", actual.getMessage());
    }

    @Test
    void protocol_ValidUrl_ReturnProtocolPartsOfUrl() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals("http", requestUrl.protocol());
    }

    @Test
    void domain_ValidUrl_ReturnProtocolPartsOfUrl() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals("localhost:8080", requestUrl.domain());
    }

    @Test
    void fullFileName_ValidUrl_ReturnProtocolPartsOfUrl() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals("page.html", requestUrl.file());
    }

    @Test
    void fileName_ValidUrl_ReturnProtocolPartsOfUrl() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals("page", requestUrl.fileName());
    }

    @Test
    void extension_ValidUrl_ReturnProtocolPartsOfUrl() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals("html", requestUrl.extension());
    }

    @Test
    void toURI_ValidUrl_ReturnURI() {
        var requestUrl = new RequestUrl("http://localhost:8080/path/to/page.html");

        assertEquals(URI.create("http://localhost:8080/path/to/page.html"), requestUrl.toURI());
    }
}