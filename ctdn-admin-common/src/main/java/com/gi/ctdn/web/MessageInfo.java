package com.gi.ctdn.web;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MessageInfo<E> implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO 
	 */ 
	private static final long serialVersionUID = -7122226153545621086L;

	@ApiModelProperty(value = "OK/ERROR/缺少参数")
	private String message;

	@ApiModelProperty(value = "成功:10000 失败:10001 缺少参数:10002")
	private int status;

	@ApiModelProperty(value = "非分页数据")
	private E data;

	/**
	 * 返回分页数据
	 */
	@ApiModelProperty(value = "分页数据")
	Pagination page;

	@ApiModelProperty(value = "true/false")
	private boolean success;

	public MessageInfo()
	{
		this.setStatus(MessageStatus.OK_CODE);
		this.setMessage("成功");
	}

	public MessageInfo(int status2, String message2) {
		this.setStatus(status2);
		this.setMessage(message2);
	}

	public MessageInfo(int status2, String message2,Pagination page) {
		this.setStatus(status2);
		this.setMessage(message2);
		this.setPage(page);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;

	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public Pagination getPage() {
		return page;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return status == MessageStatus.OK_CODE;
	}


}
