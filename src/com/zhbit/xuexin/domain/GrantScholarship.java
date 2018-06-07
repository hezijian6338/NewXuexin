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
 * GrantScholarship entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_GRANT_SCHOLARSHIP")
public class GrantScholarship implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -9133612017280606587L;

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String politicalstatus;

    private String nation;

    private String orgId;

    private String orgName;

    private String major;

    private String acceptancedate;

    private String familyeconomic;

    private String memo;

    private String academicyear;

    private String term;

    private Date reportdate;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public GrantScholarship() {
    }

    /** minimal constructor */
    public GrantScholarship(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public GrantScholarship(String stuId, String studentno, String stuname, String sex, String politicalstatus,
            String nation, String orgId, String orgName, String major, String acceptancedate, String familyeconomic,
            String memo, String academicyear, String term, Date reportdate, Date createTime, String creator,
            String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.politicalstatus = politicalstatus;
        this.nation = nation;
        this.orgId = orgId;
        this.orgName = orgName;
        this.major = major;
        this.acceptancedate = acceptancedate;
        this.familyeconomic = familyeconomic;
        this.memo = memo;
        this.academicyear = academicyear;
        this.term = term;
        this.reportdate = reportdate;
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

    @Column(name = "ACCEPTANCEDATE", length = 20)
    public String getAcceptancedate() {
        return this.acceptancedate;
    }

    public void setAcceptancedate(String acceptancedate) {
        this.acceptancedate = acceptancedate;
    }

    @Column(name = "FAMILYECONOMIC", length = 20)
    public String getFamilyeconomic() {
        return this.familyeconomic;
    }

    public void setFamilyeconomic(String familyeconomic) {
        this.familyeconomic = familyeconomic;
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
    @Column(name = "REPORTDATE", length = 7)
    public Date getReportdate() {
        return this.reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
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