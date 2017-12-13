package com.gi.ctdn.web;

import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.service.ReportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Validated
public class ReportController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value="/num")
    @ResponseBody
    public  Integer reportNum(){
        return reportService.getReportNum();
    }

    @RequestMapping(value="/name")
    @ResponseBody
    public Admin adminName(HttpServletRequest request){
        return (Admin) request.getSession().getAttribute("user_login");
    }
    @RequestMapping(value = "/reports")
    @ResponseBody
    public MessageInfo<Pagination> reports(){
        MessageInfo<Pagination> messageInfo = new MessageInfo<>();
        Pagination pagination = new Pagination();
        pagination = reportService.getReportList();
        messageInfo.setPage(pagination);
        return messageInfo;
    }
}
