package com.gi.ctdn.dao;

import com.gi.ctdn.pojo.Report;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportDAO
{
    List<Report> selectReports(Map map);
    Integer selectReportNum();
    Integer updateReportState(@Param("id") Integer id);
    Integer insert(Report report);
}
