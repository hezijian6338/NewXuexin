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
 * RewardPunishment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_REWARD_PUNISHMENT")

public class RewardPunishment  implements java.io.Serializable {


    /** 
    * @Fields serialVersionUID : TODO(简单说明是做什么的。) 
    */ 
    private static final long serialVersionUID = 7342042745880293657L;
    // Fields    

     private String id;
     private String stuId;
     private String studentno;
     private String stuname;
     private String orgId;
     private String orgName;
     private String grade;
     private String major;
     private String classname;
     private Date happenedDate;
     private Date rewardDate;
     private String fileNo;
     private String description;
     private String rewardType;
     private String rpOrgId;
     private String rpOrgName;
     private String docpath;
     private Date createTime;
     private String creator;
     private String parentOrgId;
     private String memo;


    // Constructors

    /** default constructor */
    public RewardPunishment() {
    }

	/** minimal constructor */
    public RewardPunishment(String stuId) {
        this.stuId = stuId;
    }
    
    /** full constructor */
    public RewardPunishment(String stuId, String studentno, String stuname, String orgId, String orgName, String grade, String major, String classname, Date happenedDate, Date rewardDate, String fileNo, String description, String rewardType, String rpOrgId, String rpOrgName, String docpath, Date createTime, String creator, String parentOrgId, String memo) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.orgId = orgId;
        this.orgName = orgName;
        this.grade = grade;
        this.major = major;
        this.classname = classname;
        this.happenedDate = happenedDate;
        this.rewardDate = rewardDate;
        this.fileNo = fileNo;
        this.description = description;
        this.rewardType = rewardType;
        this.rpOrgId = rpOrgId;
        this.rpOrgName = rpOrgName;
        this.docpath = docpath;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
        this.memo = memo;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="STU_ID", nullable=false, length=32)

    public String getStuId() {
        return this.stuId;
    }
    
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
    
    @Column(name="STUDENTNO", length=16)

    public String getStudentno() {
        return this.studentno;
    }
    
    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    @Column(name="STUNAME", length=20)

    public String getStuname() {
        return this.stuname;
    }
    
    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
    
    @Column(name="ORG_ID", length=32)

    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    @Column(name="ORG_NAME", length=100)

    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    @Column(name="GRADE", length=20)

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    @Column(name="MAJOR", length=128)

    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    @Column(name="CLASSNAME", length=100)

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="HAPPENED_DATE", length=7)

    public Date getHappenedDate() {
        return this.happenedDate;
    }
    
    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="REWARD_DATE", length=7)

    public Date getRewardDate() {
        return this.rewardDate;
    }
    
    public void setRewardDate(Date rewardDate) {
        this.rewardDate = rewardDate;
    }
    
    @Column(name="FILE_NO", length=20)

    public String getFileNo() {
        return this.fileNo;
    }
    
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }
    
    @Column(name="DESCRIPTION", length=200)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="REWARD_TYPE", length=30)

    public String getRewardType() {
        return this.rewardType;
    }
    
    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }
    
    @Column(name="RP_ORG_ID", length=32)

    public String getRpOrgId() {
        return this.rpOrgId;
    }
    
    public void setRpOrgId(String rpOrgId) {
        this.rpOrgId = rpOrgId;
    }
    
    @Column(name="RP_ORG_NAME", length=100)

    public String getRpOrgName() {
        return this.rpOrgName;
    }
    
    public void setRpOrgName(String rpOrgName) {
        this.rpOrgName = rpOrgName;
    }
    
    @Column(name="DOCPATH", length=200)

    public String getDocpath() {
        return this.docpath;
    }
    
    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATOR", length=32)

    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Column(name="PARENT_ORG_ID", length=32)

    public String getParentOrgId() {
        return this.parentOrgId;
    }
    
    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }
    
    @Column(name="MEMO", length=200)

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
   








}