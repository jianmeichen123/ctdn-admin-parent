package com.gi.ctdn.web;

import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.pojo.Report;
import com.gi.ctdn.service.ReportService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value="report列表")
    @RequestMapping(value = "/reports")
    @ResponseBody
    public MessageInfo<Pagination> reports(@RequestBody Map map){
        MessageInfo<Pagination> messageInfo =  reportService.getReportList(map);
        return messageInfo;
    }

    @ApiOperation(value="更新report状态，state为0时")
    @RequestMapping(value="updateState/{id}")
    @ResponseBody
    public MessageInfo<Integer> updateState(@PathVariable Integer id){
        return  reportService.updateState(id);
    }

    @ApiOperation(value="更新report状态，state为1时")
    @RequestMapping(value="updateStateTo/{id}")
    @ResponseBody
    public MessageInfo<Integer> updateStateTo(@PathVariable Integer id){
        return  reportService.updateStateTo(id);
    }

    @ApiOperation(value="创建report")
    @RequestMapping(value="insertReport")
    @ResponseBody
    public MessageInfo<Integer> insertReport(@RequestBody Report report){
        return  reportService.insertReport(report);
    }

    @ApiOperation(value="根据id查询report")
    @RequestMapping(value="getReport/{id}")
    @ResponseBody
    public MessageInfo<Report> getReport(@PathVariable Integer id){
        return  reportService.getReportById(id);
    }

    @ApiOperation(value="更新report")
    @RequestMapping(value="updateReport")
    @ResponseBody
    public MessageInfo<Integer> updateReport(@RequestBody Report report){
        return  reportService.updateReport(report);
    }

    @ApiOperation(value="预览report")
    @RequestMapping(value="previewReport")
    @ResponseBody
    public MessageInfo<Report> previewReport(){
        return  reportService.getLastReport();
    }
}
