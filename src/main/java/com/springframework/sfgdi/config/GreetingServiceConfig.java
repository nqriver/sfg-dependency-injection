package com.springframework.sfgdi.config;

import com.springframework.sfgdi.datasource.FakeDataSource;
import com.springframework.sfgdi.pets.PetService;
import com.springframework.sfgdi.pets.PetServiceFactory;
import com.springframework.sfgdi.repositories.EnglishGreetingRepository;
import com.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import com.springframework.sfgdi.services.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


@EnableConfigurationProperties(FakeDataConstructorConfiguration.class)
@Configuration
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(FakeDataConstructorConfiguration fakeDataConstructorConfiguration) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(fakeDataConstructorConfiguration.getUsername());
        fakeDataSource.setPassword(fakeDataConstructorConfiguration.getPassword());
        fakeDataSource.setJdbcUrl(fakeDataConstructorConfiguration.getJdbcUrl());
        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Bean("petService")
    @Profile("cat")
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Bean("petService")
    @Profile({"dog", "default"})
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile({"PL"})
    @Bean("i18nService")
    I18nPolishGreetingService i18nPolishGreetingService() {
        return new I18nPolishGreetingService();
    }

    @Profile({"EN"})
    @Bean("i18nService")
    I18nEnglishGreetingService i18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }


    @Bean
    ConstructorInjectedGreetingService constructorInjectedGreetingService() {
        return new ConstructorInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }
}
