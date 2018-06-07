package com.zhbit.xuexin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * TStuconfirmDetail entity. @author MyEclipse Persistence Tools
 * Time:2017/3/26
 */
@Entity
@Table(name = "T_STUCONFIRM_DETAIL")
public class StuconfirmDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String masterId;
	private String coursecode;
	private String coursename;
	private String academicyear;
	private String selectedcourseno;
	private String coursetype;
	private Double credit;
	private Date predate;
	private String isselected;
	private String reasons;
	private String creator;
	private Date createTime;
	private String memo;

	// Constructors

	/** default constructor */
	public StuconfirmDetail() {
	}

	/** full constructor */
	public StuconfirmDetail(String masterId, String coursecode,
			String coursename, String academicyear, String selectedcourseno,
			String coursetype, Double credit, Date predate,
			String isselected, String reasons, String creator, Date createTime,
			String memo) {
		this.masterId = masterId;
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.academicyear = academicyear;
		this.selectedcourseno = selectedcourseno;
		this.coursetype = coursetype;
		this.credit = credit;
		this.predate = predate;
		this.isselected = isselected;
		this.reasons = reasons;
		this.creator = creator;
		this.createTime = createTime;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MASTER_ID", length = 32)
	public String getMasterId() {
		return this.masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	@Column(name = "COURSECODE", length = 20)
	public String getCoursecode() {
		return this.coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	@Column(name = "COURSENAME", length = 100)
	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	@Column(name = "ACADEMICYEAR", length = 20)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "SELECTEDCOURSENO", length = 50)
	public String getSelectedcourseno() {
		return this.selectedcourseno;
	}

	public void setSelectedcourseno(String selectedcourseno) {
		this.selectedcourseno = selectedcourseno;
	}

	@Column(name = "COURSETYPE", length = 20)
	public String getCoursetype() {
		return this.coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}

	@Column(name = "CREDIT", precision = 4)
	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Column(name = "PREDATE", length = 11)
	public Date getPredate() {
		return this.predate;
	}

	public void setPredate(Date predate) {
		//this.predate = new Timestamp(predate.getTime()) ;
		this.predate = predate ;
	}

	@Column(name = "ISSELECTED", length = 2)
	public String getIsselected() {
		return this.isselected;
	}

	public void setIsselected(String isselected) {
		this.isselected = isselected;
	}

	@Column(name = "REASONS", length = 200)
	public String getReasons() {
		return this.reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}