package com.gi.ctdn.dao;

import com.gi.ctdn.pojo.Report;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDAO
{
    List<Report> selectReports();
    Integer selectReportNum();
}
