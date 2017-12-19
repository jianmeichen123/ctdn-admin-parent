package com.gi.ctdn.service;

import com.gi.ctdn.dao.ReportDAO;
import com.gi.ctdn.pojo.Report;
import com.gi.ctdn.web.MessageInfo;
import com.gi.ctdn.web.MessageStatus;
import com.gi.ctdn.web.Pagination;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
    @Autowired
    ReportDAO reportDAO;

    public Integer getReportNum(){
        return reportDAO.selectReportNum();
    }

    //报告列表分页
    public MessageInfo<Pagination> getReportList(Map map){
        MessageInfo<Pagination> messageInfo = new MessageInfo();
        Pagination pagination = new Pagination();
        try {
            PageHelper.startPage((Integer) map.get("pageNo"),(Integer) map.get("pageSize"));
            List<Report> reportList = reportDAO.selectReports(map);
            if(reportList!=null){
                PageInfo pageInfo = new PageInfo<>(reportList);
                pagination.setRecords(reportList);
                pagination.setTotal(pageInfo.getTotal());
                messageInfo = new MessageInfo(MessageStatus.OK_CODE,MessageStatus.OK_MESSAGE,pagination);
            }
        }catch (Exception e){
            LOGGER.error("getReportList","查询getReportList失败", e);
            messageInfo = new MessageInfo(MessageStatus.ERROR_CODE,e.getMessage());
        }
        return messageInfo;
    }

    //state为0(使用中)
    public MessageInfo<Integer> updateState(Integer id){
        MessageInfo<Integer> messageInfo = new MessageInfo<>();
        try{
            Integer result = reportDAO.updateReportState(id);
            messageInfo.setData(result);
            messageInfo.setStatus(MessageStatus.OK_CODE);
            messageInfo.setMessage(MessageStatus.OK_MESSAGE);
        }catch (Exception e){
            messageInfo.setStatus(MessageStatus.ERROR_CODE);
            messageInfo.setMessage(MessageStatus.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        }
        return messageInfo;
    }

    //state为1(已下架)
    public MessageInfo<Integer> updateStateTo(Integer id){
        MessageInfo<Integer> messageInfo = new MessageInfo<>();
        try{
            Integer result = reportDAO.updateState(id);
            messageInfo.setData(result);
            messageInfo.setStatus(MessageStatus.OK_CODE);
            messageInfo.setMessage(MessageStatus.OK_MESSAGE);
        }catch (Exception e){
            messageInfo.setStatus(MessageStatus.ERROR_CODE);
            messageInfo.setMessage(MessageStatus.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        }
        return messageInfo;
    }

    //创建report
    public MessageInfo<Integer> insertReport(Report report){
        MessageInfo<Integer> messageInfo = new MessageInfo();
        try{
            Integer result = reportDAO.insert(report);
            messageInfo.setData(result);
            messageInfo.setStatus(MessageStatus.OK_CODE);
            messageInfo.setMessage(MessageStatus.OK_MESSAGE);
        }catch (Exception e){
            messageInfo.setStatus(MessageStatus.ERROR_CODE);
            messageInfo.setMessage(MessageStatus.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        }
        return  messageInfo;
    }
}
