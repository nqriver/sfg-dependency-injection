package com.springframework.sfgdi.services;

public class I18nPolishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Witaj świecie - PL";
    }
}
