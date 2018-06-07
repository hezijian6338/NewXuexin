package com.zhbit.xuexin.common.domain.vo;

import java.util.List;

public class ExportExcelVO {
	
	private String			title;
	private String[]		headTitle;
	private List<Object[]>	dataList;
	
	public ExportExcelVO() {}
	
	public ExportExcelVO(String title, String[] headTitle, List<Object[]> dataList) {
		super();
		this.title = title;
		this.headTitle = headTitle;
		this.dataList = dataList;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getHeadTitle() {
		return headTitle;
	}
	
	public void setHeadTitle(String[] headTitle) {
		this.headTitle = headTitle;
	}
	
	public List<Object[]> getDataList() {
		return dataList;
	}
	
	public void setDataList(List<Object[]> dataList) {
		this.dataList = dataList;
	}
	
}
