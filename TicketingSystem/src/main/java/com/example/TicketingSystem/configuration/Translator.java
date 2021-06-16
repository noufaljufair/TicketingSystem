package com.example.TicketingSystem.configuration;

import java.util.Locale;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msg) {
        Locale locale  = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msg, null, locale);
    }
}
