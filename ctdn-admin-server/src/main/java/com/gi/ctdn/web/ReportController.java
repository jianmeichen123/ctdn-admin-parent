package com.gi.ctdn.web;

import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.service.ReportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    /**
     * 报告列表
     * @return
     */
    @RequestMapping(value = "/reports")
    @ResponseBody
    public MessageInfo<Pagination> reports(@RequestBody Map map){
        MessageInfo<Pagination> messageInfo =  reportService.getReportList(map);
        return messageInfo;
    }

    @RequestMapping(value="updateState/{id}")
    @ResponseBody
    public MessageInfo<Integer> updateState(@PathVariable Integer id){
        return  reportService.updateState(id);
    }

    @RequestMapping(value="updateStateTo/{id}")
    @ResponseBody
    public MessageInfo<Integer> updateStateTo(@PathVariable Integer id){
        return  reportService.updateStateTo(id);
    }
}
