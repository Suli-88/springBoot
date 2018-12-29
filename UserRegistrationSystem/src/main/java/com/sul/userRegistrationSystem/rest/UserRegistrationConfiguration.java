package com.sul.userRegistrationSystem.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

//TODO
@Configuration
public class UserRegistrationConfiguration {

	@Bean("messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("classpath:messages/messages");
		message.setDefaultEncoding("UTF-8");
		return message;
	}

}
