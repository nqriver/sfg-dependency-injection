package com.springframework.sfgdi.services;


import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Creating new Singleton Bean!!!");
    }

    public String getMyScope() {
        return "I am a Singleton";
    }
}
