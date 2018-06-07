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
 * FamilyInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FAMILYINFO")
public class FamilyInfo implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -4016142081908530628L;

    // Fields

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String call;

    private String name;

    private String politicalstatus;

    private String jobduty;

    private String telno;

    private String company;

    private String companyaddress;

    private String postcode;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public FamilyInfo() {
    }

    /** minimal constructor */
    public FamilyInfo(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public FamilyInfo(String stuId, String studentno, String stuname, String call, String name, String politicalstatus,
            String jobduty, String telno, String company, String companyaddress, String postcode, Date createTime,
            String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.call = call;
        this.name = name;
        this.politicalstatus = politicalstatus;
        this.jobduty = jobduty;
        this.telno = telno;
        this.company = company;
        this.companyaddress = companyaddress;
        this.postcode = postcode;
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

    @Column(name = "CALL", length = 20)
    public String getCall() {
        return this.call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    @Column(name = "NAME", length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "POLITICALSTATUS", length = 20)
    public String getPoliticalstatus() {
        return this.politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    @Column(name = "JOBDUTY", length = 20)
    public String getJobduty() {
        return this.jobduty;
    }

    public void setJobduty(String jobduty) {
        this.jobduty = jobduty;
    }

    @Column(name = "TELNO", length = 20)
    public String getTelno() {
        return this.telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    @Column(name = "COMPANY", length = 200)
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "COMPANYADDRESS", length = 200)
    public String getCompanyaddress() {
        return this.companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    @Column(name = "POSTCODE", length = 6)
    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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