package com.springframework.sfgdi;

import com.springframework.sfgdi.config.FakeDataConstructorConfiguration;
import com.springframework.sfgdi.controllers.*;
import com.springframework.sfgdi.datasource.FakeDataSource;
import com.springframework.sfgdi.services.PrototypeBean;
import com.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		System.out.println("----------Pet controller");
		PetController petController = (PetController) ctx.getBean("petController");
		System.out.println(petController.whichPetIsTheBest());

		System.out.println("---------Default profile");
		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());


		System.out.println("--------Primary BEAN");
		MyController myController = (MyController) ctx.getBean("myController");
		String greeting = myController.sayHello();
		System.out.println(greeting);


		System.out.println("--------Constructor injected controller");
		ConstructorInjectedController constructorInjectedController =
				(ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("--------Setter injected controller");
		SetterInjectedController setterInjectedController = (SetterInjectedController)
				ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());


		// Bean scopes
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean2.getMyScope());

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getMyScope());

		//Properties
		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println(fakeDataSource.getJdbcUrl());
		System.out.println(fakeDataSource.getUsername());
		System.out.println(fakeDataSource.getPassword());

		//Constructor config
		System.out.println("----Constructor configuration");
		FakeDataConstructorConfiguration fakeDataConstructorConfiguration = ctx.getBean(FakeDataConstructorConfiguration.class);
		System.out.println(fakeDataConstructorConfiguration.getUsername());
		System.out.println(fakeDataConstructorConfiguration.getPassword());
		System.out.println(fakeDataConstructorConfiguration.getJdbcUrl());

	}

}
