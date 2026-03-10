package com.channels.ims.plp.util;

import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.Locale;

public class Utils {

    /**
     * Check the Request Locale (Language Code) if Null ten return EN
     *
     * @param request HttpServletRequest
     * @return Locale
     */
    public static Locale getLocale(final HttpServletRequest request) {

        if (request != null && request.getLocale() != null) {
            return request.getLocale();
        }
        return Locale.ENGLISH;
    }


    /**
     * Utils Method to Parse String Value To Integer Value
     *
     * @param value  Value To Parse
     * @param locale Locale
     * @return Integer
     */
    public static Integer convertToInteger(String value, Locale locale) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new ResourceException(ExceptionKey.NOT_VALID_NUMBER, HttpStatus.NOT_ACCEPTABLE, locale);
        }
    }
}
