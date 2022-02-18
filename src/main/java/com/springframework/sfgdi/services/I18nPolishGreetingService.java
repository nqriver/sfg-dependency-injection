package com.springframework.sfgdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("PL")
@Service("i18nService")
public class I18nPolishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Witaj świecie - PL";
    }
}
