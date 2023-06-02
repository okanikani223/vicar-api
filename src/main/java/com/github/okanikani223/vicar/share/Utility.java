package com.github.okanikani223.vicar.share;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Utility {

    public static PatternUtility pattern() {
        return new PatternUtility();
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class PatternUtility {
        private static final String URL_PARTS_PATTERN = "^(?<scheme>[a-zA-Z][a-zA-Z0-9+\\-.]*)://(?<authority>[^\\s/?$#]*)/.*/(?<file>[^\\s/?$#]*)";

        public Pattern urlPartsPattern() {
            return Pattern.compile(URL_PARTS_PATTERN);
        }
    }
}
