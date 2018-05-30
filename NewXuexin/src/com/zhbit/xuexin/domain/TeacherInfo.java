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
 * TeacherInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TEACHERINFO")
public class TeacherInfo implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 130910768429291122L;

    private String id;

    private String employNo;

    private String employName;

    private String sex;

    private Date birthday;

    private String orgId;

    private String orgName;

    private String department;

    private String telno;

    private String email;

    private String address;

    private String category;

    private String education;

    private String degree;

    private String duty;

    private String acdemictitle;

    private String invigilatorflag;

    private String researchdirection;

    private String introduce;

    private String major;

    private String graduate;

    private String qualificationflag;

    private String jobstatus;

    private String teacherLevel;

    private String islab;

    private String isouthire;

    private String politicalstatus;

    private String nation;

    private Date createTime;

    private String creator;

    private String password;

    private String photopath;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public TeacherInfo() {
    }

    /** full constructor */
    public TeacherInfo(String employNo, String employName, String sex, Date birthday, String orgId, String orgName,
            String department, String telno, String email, String address, String category, String education,
            String degree, String duty, String acdemictitle, String invigilatorflag, String researchdirection,
            String introduce, String major, String graduate, String qualificationflag, String jobstatus,
            String teacherLevel, String islab, String isouthire, String politicalstatus, String nation,
            Date createTime, String creator, String password, String photopath, String parentOrgId) {
        this.employNo = employNo;
        this.employName = employName;
        this.sex = sex;
        this.birthday = birthday;
        this.orgId = orgId;
        this.orgName = orgName;
        this.department = department;
        this.telno = telno;
        this.email = email;
        this.address = address;
        this.category = category;
        this.education = education;
        this.degree = degree;
        this.duty = duty;
        this.acdemictitle = acdemictitle;
        this.invigilatorflag = invigilatorflag;
        this.researchdirection = researchdirection;
        this.introduce = introduce;
        this.major = major;
        this.graduate = graduate;
        this.qualificationflag = qualificationflag;
        this.jobstatus = jobstatus;
        this.teacherLevel = teacherLevel;
        this.islab = islab;
        this.isouthire = isouthire;
        this.politicalstatus = politicalstatus;
        this.nation = nation;
        this.createTime = createTime;
        this.creator = creator;
        this.password = password;
        this.photopath = photopath;
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

    @Column(name = "SEX", length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", length = 7)
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    @Column(name = "DEPARTMENT", length = 100)
    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "TELNO", length = 20)
    public String getTelno() {
        return this.telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "CATEGORY", length = 20)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "EDUCATION", length = 20)
    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name = "DEGREE", length = 20)
    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Column(name = "DUTY", length = 20)
    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Column(name = "ACDEMICTITLE", length = 20)
    public String getAcdemictitle() {
        return this.acdemictitle;
    }

    public void setAcdemictitle(String acdemictitle) {
        this.acdemictitle = acdemictitle;
    }

    @Column(name = "INVIGILATORFLAG", length = 2)
    public String getInvigilatorflag() {
        return this.invigilatorflag;
    }

    public void setInvigilatorflag(String invigilatorflag) {
        this.invigilatorflag = invigilatorflag;
    }

    @Column(name = "RESEARCHDIRECTION", length = 100)
    public String getResearchdirection() {
        return this.researchdirection;
    }

    public void setResearchdirection(String researchdirection) {
        this.researchdirection = researchdirection;
    }

    @Column(name = "INTRODUCE", length = 500)
    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Column(name = "MAJOR", length = 100)
    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "GRADUATE", length = 100)
    public String getGraduate() {
        return this.graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    @Column(name = "QUALIFICATIONFLAG", length = 2)
    public String getQualificationflag() {
        return this.qualificationflag;
    }

    public void setQualificationflag(String qualificationflag) {
        this.qualificationflag = qualificationflag;
    }

    @Column(name = "JOBSTATUS", length = 2)
    public String getJobstatus() {
        return this.jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    @Column(name = "TEACHER_LEVEL", length = 20)
    public String getTeacherLevel() {
        return this.teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    @Column(name = "ISLAB", length = 2)
    public String getIslab() {
        return this.islab;
    }

    public void setIslab(String islab) {
        this.islab = islab;
    }

    @Column(name = "ISOUTHIRE", length = 2)
    public String getIsouthire() {
        return this.isouthire;
    }

    public void setIsouthire(String isouthire) {
        this.isouthire = isouthire;
    }

    @Column(name = "POLITICALSTATUS", length = 20)
    public String getPoliticalstatus() {
        return this.politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    @Column(name = "NATION", length = 50)
    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "PHOTOPATH", length = 200)
    public String getPhotopath() {
        return this.photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

}