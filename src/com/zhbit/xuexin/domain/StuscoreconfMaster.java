package com.zhbit.xuexin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * TStuscoreconfMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STUSCORECONF_MASTER")
public class StuscoreconfMaster implements java.io.Serializable {

	// Fields

	private String id;
	private String stuId;
	private String studentno;
	private String stuname;
	private String orgId;
	private String orgName;
	private String telno;
	private String grade;
	private String majorcode;
	private String major;
	private String academicyear;
	private String term;
	private String classname;
	private String teacherno;
	private String teachername;
	private Date confirmdate;
	private String status;
	private String creator;
	private Date createTime;
	private String parentOrgId;
	private String memo;

	// Constructors

	/** default constructor */
	public StuscoreconfMaster() {
	}

	/** minimal constructor */
	public StuscoreconfMaster(String stuId) {
		this.stuId = stuId;
	}

	/** full constructor */
	public StuscoreconfMaster(String stuId, String studentno, String stuname,
			String orgId, String orgName, String telno, String grade,
			String majorcode, String major, String academicyear, String term,
			String classname, String teacherno, String teachername,
			Date confirmdate, String status, String creator, Date createTime,
			String parentOrgId, String memo) {
		this.stuId = stuId;
		this.studentno = studentno;
		this.stuname = stuname;
		this.orgId = orgId;
		this.orgName = orgName;
		this.telno = telno;
		this.grade = grade;
		this.majorcode = majorcode;
		this.major = major;
		this.academicyear = academicyear;
		this.term = term;
		this.classname = classname;
		this.teacherno = teacherno;
		this.teachername = teachername;
		this.confirmdate = confirmdate;
		this.status = status;
		this.creator = creator;
		this.createTime = createTime;
		this.parentOrgId = parentOrgId;
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

	@Column(name = "STU_ID", nullable = false, length = 32)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "STUDENTNO", length = 16)
	public String getStudentno() {
		return this.studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}

	@Column(name = "STUNAME", length = 20)
	public String getStuname() {
		return this.stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "TELNO", length = 20)
	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	@Column(name = "GRADE", length = 20)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "MAJORCODE", length = 10)
	public String getMajorcode() {
		return this.majorcode;
	}

	public void setMajorcode(String majorcode) {
		this.majorcode = majorcode;
	}

	@Column(name = "MAJOR", length = 128)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "ACADEMICYEAR", length = 20)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "TERM", length = 20)
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "CLASSNAME", length = 100)
	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "TEACHERNO", length = 5)
	public String getTeacherno() {
		return this.teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}

	@Column(name = "TEACHERNAME", length = 20)
	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONFIRMDATE", length = 7)
	public Date getConfirmdate() {
		return this.confirmdate;
	}

	public void setConfirmdate(Date confirmdate) {
		this.confirmdate = confirmdate;
	}

	@Column(name = "STATUS", length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "PARENT_ORG_ID", length = 32)
	public String getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}