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
 * AttendanceMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ATTENDANCE_MASTER")
public class AttendanceMaster implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -4273892391798714364L;

    // Fields

    private String id;

    private String term;

    private String coursename;

    private String selectedcourseno;

    private String employNo;

    private String employName;

    private String academicyear;

    private String orgId;

    private String orgName;

    private String schooltime;

    private String address;

    private String sourceFile;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public AttendanceMaster() {
    }

    /** full constructor */
    public AttendanceMaster(String term, String coursename, String selectedcourseno, String employNo,
            String employName, String academicyear, String orgId, String orgName, String schooltime, String address,
            String sourceFile, String memo, Date createTime, String creator, String parentOrgId) {
        this.term = term;
        this.coursename = coursename;
        this.selectedcourseno = selectedcourseno;
        this.employNo = employNo;
        this.employName = employName;
        this.academicyear = academicyear;
        this.orgId = orgId;
        this.orgName = orgName;
        this.schooltime = schooltime;
        this.address = address;
        this.sourceFile = sourceFile;
        this.memo = memo;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
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

    @Column(name = "TERM", length = 20)
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name = "COURSENAME", length = 100)
    public String getCoursename() {
        return this.coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Column(name = "SELECTEDCOURSENO", length = 50)
    public String getSelectedcourseno() {
        return this.selectedcourseno;
    }

    public void setSelectedcourseno(String selectedcourseno) {
        this.selectedcourseno = selectedcourseno;
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

    @Column(name = "ACADEMICYEAR", length = 20)
    public String getAcademicyear() {
        return this.academicyear;
    }

    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
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

    @Column(name = "SCHOOLTIME", length = 100)
    public String getSchooltime() {
        return this.schooltime;
    }

    public void setSchooltime(String schooltime) {
        this.schooltime = schooltime;
    }

    @Column(name = "ADDRESS", length = 100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "SOURCE_FILE", length = 1000)
    public String getSourceFile() {
        return this.sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
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

}