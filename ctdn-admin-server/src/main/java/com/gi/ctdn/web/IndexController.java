package com.gi.ctdn.web;

import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.service.AdminService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Validated
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private  String domain = "ctdnqa.gi.com";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    AdminService adminService;

    @ApiOperation(value = "系统主页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @ApiOperation(value = "发布页面")
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String header() {
        return "/publish";
    }

    @ApiOperation(value = "发布页面")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {
        return "/update";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ApiOperation(value = "欢迎页面")
    public String welcome() {
        return "/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录页面")
    public String login() {
        return "/login";
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", value = "登录名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageInfo login(@NotEmpty(message = "用户名不能为空")
                             @RequestParam("login")
                                     String login,
                             @RequestParam("password")
                                     String password,
                             HttpServletRequest request,HttpServletResponse response) {
        MessageInfo data = new MessageInfo<>();
        try {
            Admin admin = adminService.getBylogin(login);
            if (admin != null && admin.getPassword().equals(password)) {
                data.setData(admin);
                request.getSession().setAttribute(CtdnConstants.SESS_ATTR_USER_LOGIN, admin);
                 String seessioid = request.getSession().getId();
                stringRedisTemplate.boundValueOps("ctdn:admin:"+seessioid+":code").set(admin.getLogin());
                addCookie(response,"_uid_",seessioid);
                addCookie(response,"s_","admin");
                return data;
            } else {
                data.setMessage("账号密码不匹配");
                return data;
            }
        } catch (Exception e) {
            logger.error("登录错误", e);
            data.setStatus(MessageStatus.ERROR_CODE);
            data.setMessage(e.getMessage());
        }
        return data;
    }

    @ApiOperation(value = "用户退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public MessageInfo<Admin> logout(HttpSession session) {
        MessageInfo<Admin> data = new MessageInfo<>();
        session.invalidate();
        return data;
    }

    public void addCookie(HttpServletResponse response,String k,String v){
        Cookie cookie = new Cookie(k,v);//创建新cookie
        cookie.setMaxAge( 60*60*24*7);
        cookie.setDomain(domain);
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
    }
}
