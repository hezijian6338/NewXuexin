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
 * SubjectContest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SUBJECTCONTEST")
public class SubjectContest implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -3001905762982642014L;

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String academicyear;

    private String term;

    private String rewardname;

    private String rewardlevel;

    private String rewardgrade;

    private String grantunits;

    private String rewardproject;

    private String guidteacher;

    private Date rewarddate;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    private String docpath;

    // Constructors

    /** default constructor */
    public SubjectContest() {
    }

    /** minimal constructor */
    public SubjectContest(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public SubjectContest(String stuId, String studentno, String stuname, String academicyear, String term,
            String rewardname, String rewardlevel, String rewardgrade, String grantunits, String rewardproject,
            String guidteacher, Date rewarddate, String memo, Date createTime, String creator, String parentOrgId,
            String docpath) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.academicyear = academicyear;
        this.term = term;
        this.rewardname = rewardname;
        this.rewardlevel = rewardlevel;
        this.rewardgrade = rewardgrade;
        this.grantunits = grantunits;
        this.rewardproject = rewardproject;
        this.guidteacher = guidteacher;
        this.rewarddate = rewarddate;
        this.memo = memo;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
        this.docpath = docpath;
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

    @Column(name = "REWARDNAME", length = 50)
    public String getRewardname() {
        return this.rewardname;
    }

    public void setRewardname(String rewardname) {
        this.rewardname = rewardname;
    }

    @Column(name = "REWARDLEVEL", length = 50)
    public String getRewardlevel() {
        return this.rewardlevel;
    }

    public void setRewardlevel(String rewardlevel) {
        this.rewardlevel = rewardlevel;
    }

    @Column(name = "REWARDGRADE", length = 50)
    public String getRewardgrade() {
        return this.rewardgrade;
    }

    public void setRewardgrade(String rewardgrade) {
        this.rewardgrade = rewardgrade;
    }

    @Column(name = "GRANTUNITS", length = 100)
    public String getGrantunits() {
        return this.grantunits;
    }

    public void setGrantunits(String grantunits) {
        this.grantunits = grantunits;
    }

    @Column(name = "REWARDPROJECT", length = 100)
    public String getRewardproject() {
        return this.rewardproject;
    }

    public void setRewardproject(String rewardproject) {
        this.rewardproject = rewardproject;
    }

    @Column(name = "GUIDTEACHER", length = 20)
    public String getGuidteacher() {
        return this.guidteacher;
    }

    public void setGuidteacher(String guidteacher) {
        this.guidteacher = guidteacher;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REWARDDATE", length = 7)
    public Date getRewarddate() {
        return this.rewarddate;
    }

    public void setRewarddate(Date rewarddate) {
        this.rewarddate = rewarddate;
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

    @Column(name = "DOCPATH", length = 200)
    public String getDocpath() {
        return this.docpath;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

}