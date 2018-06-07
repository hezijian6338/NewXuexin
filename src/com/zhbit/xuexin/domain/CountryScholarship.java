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
 * CountryScholarship entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_COUNTRY_SCHOLARSHIP")
public class CountryScholarship implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 2988219054604754772L;

    // Fields

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String grade;

    private String politicalstatus;

    private String orgId;

    private String orgName;

    private String major;

    private String schoolScholarInfo;

    private String provinceScholarIinfo;

    private String awardInfo;

    private String competitionInfo;

    private String duty;

    private String rewardname;

    private String memo;

    private String academicyear;

    private String term;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public CountryScholarship() {
    }

    /** minimal constructor */
    public CountryScholarship(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public CountryScholarship(String stuId, String studentno, String stuname, String sex, String grade,
            String politicalstatus, String orgId, String orgName, String major, String schoolScholarInfo,
            String provinceScholarIinfo, String awardInfo, String competitionInfo, String duty, String rewardname,
            String memo, String academicyear, String term, Date createTime, String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.grade = grade;
        this.politicalstatus = politicalstatus;
        this.orgId = orgId;
        this.orgName = orgName;
        this.major = major;
        this.schoolScholarInfo = schoolScholarInfo;
        this.provinceScholarIinfo = provinceScholarIinfo;
        this.awardInfo = awardInfo;
        this.competitionInfo = competitionInfo;
        this.duty = duty;
        this.rewardname = rewardname;
        this.memo = memo;
        this.academicyear = academicyear;
        this.term = term;
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

    @Column(name = "SEX", length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "GRADE", length = 20)
    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "POLITICALSTATUS", length = 20)
    public String getPoliticalstatus() {
        return this.politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
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

    @Column(name = "MAJOR", length = 100)
    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "SCHOOL_SCHOLAR_INFO", length = 2000)
    public String getSchoolScholarInfo() {
        return this.schoolScholarInfo;
    }

    public void setSchoolScholarInfo(String schoolScholarInfo) {
        this.schoolScholarInfo = schoolScholarInfo;
    }

    @Column(name = "PROVINCE_SCHOLAR_IINFO", length = 2000)
    public String getProvinceScholarIinfo() {
        return this.provinceScholarIinfo;
    }

    public void setProvinceScholarIinfo(String provinceScholarIinfo) {
        this.provinceScholarIinfo = provinceScholarIinfo;
    }

    @Column(name = "AWARD_INFO", length = 2000)
    public String getAwardInfo() {
        return this.awardInfo;
    }

    public void setAwardInfo(String awardInfo) {
        this.awardInfo = awardInfo;
    }

    @Column(name = "COMPETITION_INFO", length = 2000)
    public String getCompetitionInfo() {
        return this.competitionInfo;
    }

    public void setCompetitionInfo(String competitionInfo) {
        this.competitionInfo = competitionInfo;
    }

    @Column(name = "DUTY", length = 200)
    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Column(name = "REWARDNAME", length = 20)
    public String getRewardname() {
        return this.rewardname;
    }

    public void setRewardname(String rewardname) {
        this.rewardname = rewardname;
    }

    @Column(name = "MEMO", length = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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