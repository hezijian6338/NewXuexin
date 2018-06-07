package com.zhbit.xuexin.common.dto;

/**
 * 保存Excel导入时错误信息的媒介
 * @ClassName: ExcelErrorInfo
 * @Description: TODO
 * @author: zouxiang
 * @date: 2017年6月8日 上午8:35:47
 */
public class ExcelErrorInfo {
	private int row;
	private String msg;
	
	
	public ExcelErrorInfo() {
		super();
	}
	public ExcelErrorInfo(int row, String msg) {
		super();
		this.row = row;
		this.msg = msg;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
