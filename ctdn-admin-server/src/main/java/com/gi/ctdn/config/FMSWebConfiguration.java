package com.gi.ctdn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gi.ctdn.config.LoginInterceptor;

@Configuration
public class FMSWebConfiguration extends WebMvcConfigurerAdapter
{

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(loginIntecetpor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	@Bean
	public HandlerInterceptor loginIntecetpor()
	{
		return new LoginInterceptor();
	}


	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor()
	{
		return new MethodValidationPostProcessor();
	}

}
