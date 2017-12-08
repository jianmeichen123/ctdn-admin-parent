package com.gi.ctdn.web;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zcy on 16-12-8.
 */
public class Query implements Serializable{

    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize =10;

    @ApiModelProperty(value = "当前页码")
    private Integer pageNo =0;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
