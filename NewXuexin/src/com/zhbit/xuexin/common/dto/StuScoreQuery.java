package com.zhbit.xuexin.common.dto;

public class StuScoreQuery extends StuQuery{
	private String academicYear;
	private String term;
	
	
	public StuScoreQuery() {
		
	}


	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}
	
}
