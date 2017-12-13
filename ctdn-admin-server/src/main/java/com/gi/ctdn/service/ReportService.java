package com.gi.ctdn.service;

import com.gi.ctdn.dao.ReportDAO;
import com.gi.ctdn.pojo.Report;
import com.gi.ctdn.web.Pagination;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportDAO reportDAO;

    public Integer getReportNum(){
        return reportDAO.selectReportNum();
    }

    public Pagination getReportList(){
        Pagination pagination = new Pagination();
        try {
            List<Report> reportList = reportDAO.selectReports();
            if(reportList!=null){
                pagination.setRecords(reportList);
                pagination.setTotal((long)reportList.size());
            }
        }catch (Exception e){

        }
        return pagination;
    }
}
