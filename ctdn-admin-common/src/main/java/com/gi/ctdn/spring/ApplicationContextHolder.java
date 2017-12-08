package com.gi.ctdn.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware
{
	private ApplicationContext applicationContext;

	public static ApplicationContextHolder instance = new ApplicationContextHolder();

	private ApplicationContextHolder(){};

	public static ApplicationContextHolder getInstance()
	{
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> clazz)
	{
		return applicationContext.getBean(clazz);
	}

}
