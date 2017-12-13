package com.gi.ctdn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.web.CtdnConstants;
import com.gi.ctdn.web.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter implements EnvironmentAware
{
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	private String[] excludes;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String uri = request.getRequestURI();
		if (uri != null && excludes != null && excludes.length > 0)
		{
			for (String exclude : excludes)
			{
				if (uri.indexOf(exclude) > -1)
				{
					return true;
				}
			}
		}
		Object user = request.getSession().getAttribute(CtdnConstants.SESS_ATTR_USER_LOGIN);
		if (user != null)
		{
			return true;
		}
		logger.warn(String.format("非法访问 - Path:%s, IP:%s", uri, getRemortIP(request)));

		if(handler instanceof HandlerMethod)
		{
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			MethodParameter type = handlerMethod.getReturnType();
			Class<?> clazz = type.getMethod().getReturnType();
			if(clazz != null && clazz.equals(MessageInfo.class))
			{
				MessageInfo data = new MessageInfo<>();
				data.setData(CtdnConstants.NOT_LOGIN);
				data.setMessage("用户未登录或登录失效，请重新登录");

				ObjectMapper objectMapper = new ObjectMapper();
				String msg = objectMapper.writeValueAsString(data);
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(msg);
				writer.flush();
			}
			else
			{
				String loginPath = request.getContextPath()+"/login";
				response.sendRedirect(loginPath);
			}
		}
		return false;
	}

	@Override
	public void setEnvironment(Environment environment)
	{
		String excludeStr = environment.getProperty("fms.loginInterceptor.excludes");
		if (excludeStr != null)
		{
			excludes = excludeStr.split(",");
		}
	}

	private String getRemortIP(HttpServletRequest request)
	{
		if (request.getHeader("x-forwarded-for") == null)
		{
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
