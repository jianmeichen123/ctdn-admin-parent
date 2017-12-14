package com.gi.ctdn.pojo;

import com.gi.ctdn.web.Pagination;
import io.swagger.models.auth.In;

public class Report extends Pagination
{
    /**
     * id
     */
    private String id;
    /**
     *标题
     */
    private String title;
    /**
     *发布时间
     */
    private String publishDate;
    /**
     *发布来源
     */
    private String source;
    /**
     *作者
     */
    private String authorName;
    /**
     *作者头像
     */
    private String authorAvatar;
    /**
     *作者简介
     */
    private String authorDesc;
    /**
     *列表图片
     */
    private String listPic;
    /**
     *报告正文
     */
    private String reportBody;
    /**
     *报告页码
     */
    private Integer pageNum;
    /**
     *封面图片
     */
    private String coverPic;
    /**
     *报告简介
     */
    private String reportDesc;
    /**
     *报告目录
     */
    private String reportIndex;
    /**
     *文件报告地址
     */
    private String fileUrl;
    /**
     *0:文章类报告 1:文件类报告
     */
    private Integer type;
    /**
     *0:正在使用 1:下架
     */
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getAuthorDesc() {
        return authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }

    public String getListPic() {
        return listPic;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
    }

    public String getReportBody() {
        return reportBody;
    }

    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportIndex() {
        return reportIndex;
    }

    public void setReportIndex(String reportIndex) {
        this.reportIndex = reportIndex;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
