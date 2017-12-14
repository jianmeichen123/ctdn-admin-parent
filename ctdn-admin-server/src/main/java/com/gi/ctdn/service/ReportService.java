package com.gi.ctdn.service;

import com.gi.ctdn.dao.ReportDAO;
import com.gi.ctdn.pojo.Report;
import com.gi.ctdn.web.Pagination;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    ReportDAO reportDAO;

    public Integer getReportNum(){
        return reportDAO.selectReportNum();
    }

    //报告列表分页
    public Pagination getReportList(Map map){
        Pagination pagination = new Pagination();
        try {
            PageHelper.startPage((Integer) map.get("pageNo"),(Integer) map.get("pageSize"));
            List<Report> reportList = reportDAO.selectReports(map);
            if(reportList!=null){
                PageInfo pageInfo = new PageInfo<>(reportList);
                pagination.setRecords(reportList);
                pagination.setTotal(pageInfo.getTotal());
            }
        }catch (Exception e){

        }
        return pagination;
    }

    public void updateState(Integer id){
        reportDAO.updateReportState(id);
    }
}
