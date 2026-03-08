package com.channels.ims.plp.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class Utils {

    /**
     * Check the Request Locale (Language Code) if Null ten return EN
     * @param request HttpServletRequest
     * @return Locale
     */
    public static Locale getLocale(final HttpServletRequest request) {

        if (request != null && request.getLocale() != null) {
            return request.getLocale();
        }
        return Locale.ENGLISH;
    }
}
