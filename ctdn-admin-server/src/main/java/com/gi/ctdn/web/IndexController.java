package com.gi.ctdn.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
public class IndexController
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@ApiOperation(value="系统主页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index()
	{
		return "redirect:/files";
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header()
	{
		return "/header";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value="登录页面")
	public String login()
	{
		return "/login";
	}

	@ApiOperation(value="用户登录")
	@ApiImplicitParams({
				@ApiImplicitParam(name = "login", value = "登录名", required = true, paramType="query"),
				@ApiImplicitParam(name = "password", value = "密码", required = true, paramType="query")
			})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public MessageInfo login(MessageInfo aa)
	{
		MessageInfo data = new MessageInfo<>();
		try
		{
			return data;
//			User user = userService.login(login, password);
//			//查找当前用户分组
//			if(user != null){
//				List<UserGroup> userGroupList = userGroupService.findByUserId(user.getId());
//				List<CompanyGroup> groupList = new ArrayList<>();
//				if(!userGroupList.isEmpty()){
//					for(UserGroup userGroup :userGroupList){
//						groupList.add(userGroup.getCompanyGroup());
//					}
//				}else{
//					groupList = companyGroupService.getAll();
//				}
//				user.setCompanyGroupList(groupList);
//				data.setValue(user);
//				Log log = new Log();
//				log.setCreatedId(user.getId());
//				final Date now = new Date();
//				log.setCreatedTime(now);
//				log.setLogTime(now);
//				log.setUserLogin(user.getLogin());
//				log.setUserRealName(user.getRealName());
//				log.setOperation("登录");
//				log.setSummary("登录");
//				logService.save(log);
			//request.getSession().setAttribute(FMSConstants.SESS_ATTR_USER_LOGIN, user);
		} catch (Exception e)
		{
			logger.error("登录错误", e);
//			data.setCode(ResponseStatus.ERROR);
//			data.setMessage(e.getMessage());
		}
		return data;
	}

//	@ApiOperation(value="用户退出")
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseData<User> logout(HttpSession session)
//	{
//		ResponseData<User> data = new ResponseData<User>();
//		session.invalidate();
//		return data;
//	}

}
