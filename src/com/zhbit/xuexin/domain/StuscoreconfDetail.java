package com.zhbit.xuexin.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TStuscoreconfDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STUSCORECONF_DETAIL")
public class StuscoreconfDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String masterId;
	private String coursecode;
	private String coursename;
	private String retakeflag;
	private String usualscore;
	private String middlescore;
	private String endscore;
	private String labscore;
	private String finalscore;
	private String convertscore;
	private String resitscore;
	private String resitmemo;
	private String repairscore;
	private Double gradepoint;
	private String memo;
	private Date createTime;
	private String creator;
	private String parentOrgId;

	//2017-04-10 邹方翔添加
	private String employNo;
	private String employName;
	private String courseType;
	
	//2017-04-19 邹方翔添加
	private String selectedCourseNo;
	// Constructors

	/** default constructor */
	public StuscoreconfDetail() {
	}

	/** full constructor */
	public StuscoreconfDetail(String masterId, String coursecode,
			String coursename, String retakeflag, String usualscore,
			String middlescore, String endscore, String labscore,
			String finalscore, String convertscore, String resitscore,
			String resitmemo, String repairscore, Double gradepoint,
			String memo, Date createTime, String creator, String parentOrgId,String employNo,String employName,String courseType,String selectedCourseNo) {
		this.masterId = masterId;
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.retakeflag = retakeflag;
		this.usualscore = usualscore;
		this.middlescore = middlescore;
		this.endscore = endscore;
		this.labscore = labscore;
		this.finalscore = finalscore;
		this.convertscore = convertscore;
		this.resitscore = resitscore;
		this.resitmemo = resitmemo;
		this.repairscore = repairscore;
		this.gradepoint = gradepoint;
		this.memo = memo;
		this.createTime = createTime;
		this.creator = creator;
		this.parentOrgId = parentOrgId;
		this.employNo = employNo;
		this.employName = employName;
		this.courseType = courseType;
		this.selectedCourseNo = selectedCourseNo;
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

	@Column(name = "RETAKEFLAG", length = 2)
	public String getRetakeflag() {
		return this.retakeflag;
	}

	public void setRetakeflag(String retakeflag) {
		this.retakeflag = retakeflag;
	}

	@Column(name = "USUALSCORE", length = 20)
	public String getUsualscore() {
		return this.usualscore;
	}

	public void setUsualscore(String usualscore) {
		this.usualscore = usualscore;
	}

	@Column(name = "MIDDLESCORE", length = 20)
	public String getMiddlescore() {
		return this.middlescore;
	}

	public void setMiddlescore(String middlescore) {
		this.middlescore = middlescore;
	}

	@Column(name = "ENDSCORE", length = 20)
	public String getEndscore() {
		return this.endscore;
	}

	public void setEndscore(String endscore) {
		this.endscore = endscore;
	}

	@Column(name = "LABSCORE", length = 20)
	public String getLabscore() {
		return this.labscore;
	}

	public void setLabscore(String labscore) {
		this.labscore = labscore;
	}

	@Column(name = "FINALSCORE", length = 20)
	public String getFinalscore() {
		return this.finalscore;
	}

	public void setFinalscore(String finalscore) {
		this.finalscore = finalscore;
	}

	@Column(name = "CONVERTSCORE", length = 20)
	public String getConvertscore() {
		return this.convertscore;
	}

	public void setConvertscore(String convertscore) {
		this.convertscore = convertscore;
	}

	@Column(name = "RESITSCORE", length = 20)
	public String getResitscore() {
		return this.resitscore;
	}

	public void setResitscore(String resitscore) {
		this.resitscore = resitscore;
	}

	@Column(name = "RESITMEMO", length = 200)
	public String getResitmemo() {
		return this.resitmemo;
	}

	public void setResitmemo(String resitmemo) {
		this.resitmemo = resitmemo;
	}

	@Column(name = "REPAIRSCORE", length = 20)
	public String getRepairscore() {
		return this.repairscore;
	}

	public void setRepairscore(String repairscore) {
		this.repairscore = repairscore;
	}

	@Column(name = "GRADEPOINT", precision = 5)
	public Double getGradepoint() {
		return this.gradepoint;
	}

	public void setGradepoint(Double gradepoint) {
		this.gradepoint = gradepoint;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "PARENT_ORG_ID", length = 32)
	public String getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Column(name = "EMPLOY_NO", length = 50)
	public String getEmployNo() {
		return employNo;
	}

	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}
	@Column(name = "EMPLOY_NAME", length = 64)
	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}
	@Column(name = "COURSETYPE", length = 20)
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	@Column(name = "SELECTEDCOURSENO", length = 50)
	public String getSelectedCourseNo() {
		return selectedCourseNo;
	}

	public void setSelectedCourseNo(String selectedCourseNo) {
		this.selectedCourseNo = selectedCourseNo;
	}
	
	
}