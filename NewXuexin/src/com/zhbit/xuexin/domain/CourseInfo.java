package com.zhbit.xuexin.domain;

// default package

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
 * CourseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_COURSEINFO")
public class CourseInfo implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 617232243679391664L;

    private String id;

    private String coursecode;

    private String coursename;

    private String academicyear;

    private String term;

    private String employNo;

    private String employName;

    private String selectedcourseno;

    private String coursetype;

    private Integer totalhours;

    private Integer labhours;
    
    private String classinfo;

    private Integer limitstudentnum;
    
    private Integer studentnum;

    private Double credit;

    private String belongto;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    private String orgId;

    private String orgName;

    // Constructors

    /** default constructor */
    public CourseInfo() {
    }

    /** full constructor */
    public CourseInfo(String coursecode, String coursename, String academicyear, String term, String employNo,
            String employName, String selectedcourseno, String coursetype, Integer totalhours, Integer labhours,
            String classinfo, Integer limitstudentnum,Integer studentnum, Double credit, String belongto, String memo, Date createTime,
            String creator, String parentOrgId, String orgId, String orgName) {
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.academicyear = academicyear;
        this.term = term;
        this.employNo = employNo;
        this.employName = employName;
        this.selectedcourseno = selectedcourseno;
        this.coursetype = coursetype;
        this.totalhours = totalhours;
        this.labhours = labhours;
        this.classinfo = classinfo;
        this.limitstudentnum = limitstudentnum;
        this.studentnum = studentnum;
        this.credit = credit;
        this.belongto = belongto;
        this.memo = memo;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
        this.orgId = orgId;
        this.orgName = orgName;
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

    @Column(name = "TERM", length = 20)
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name = "EMPLOY_NO", length = 10)
    public String getEmployNo() {
        return this.employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo = employNo;
    }

    @Column(name = "EMPLOY_NAME", length = 64)
    public String getEmployName() {
        return this.employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
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

    @Column(name = "TOTALHOURS", precision = 22, scale = 0)
    public Integer getTotalhours() {
        return this.totalhours;
    }

    public void setTotalhours(Integer totalhours) {
        this.totalhours = totalhours;
    }

    @Column(name = "LABHOURS", precision = 22, scale = 0)
    public Integer getLabhours() {
        return this.labhours;
    }

    public void setLabhours(Integer labhours) {
        this.labhours = labhours;
    }

    @Column(name = "CLASSINFO", length = 1000)
    public String getClassinfo() {
        return this.classinfo;
    }

    public void setClassinfo(String classinfo) {
        this.classinfo = classinfo;
    }

    @Column(name = "LIMITSTUDENTNUM", precision = 22,scale = 0)
    public Integer getLimitstudentnum() {
		return this.limitstudentnum;
	}

	public void setLimitstudentnum(Integer limitstudentnum) {
		this.limitstudentnum = limitstudentnum;
	}
	
    @Column(name = "STUDENTNUM", precision = 22, scale = 0)
    public Integer getStudentnum() {
        return this.studentnum;
    }

    public void setStudentnum(Integer studentnum) {
        this.studentnum = studentnum;
    }

    @Column(name = "CREDIT", precision = 4)
    public Double getCredit() {
        return this.credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Column(name = "BELONGTO", length = 100)
    public String getBelongto() {
        return this.belongto;
    }

    public void setBelongto(String belongto) {
        this.belongto = belongto;
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

}