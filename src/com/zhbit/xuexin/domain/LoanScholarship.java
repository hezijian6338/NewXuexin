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
 * LoanScholarship entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LOAN_SCHOLARSHIP")
public class LoanScholarship implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -2055053688839255603L;

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String idcardno;

    private String orgId;

    private String orgName;

    private String major;

    private String classname;

    private String grade;

    private Double loanamount;

    private String censoredflag;

    private String refusereason;

    private String memo;

    private String academicyear;

    private String term;

    private Date createTime;

    private String creator;

    private String repayflag;

    private Double repayamount;

    private Date repaydate;

    private Date modifydate;

    private String modifier;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public LoanScholarship() {
    }

    /** minimal constructor */
    public LoanScholarship(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public LoanScholarship(String stuId, String studentno, String stuname, String sex, String idcardno, String orgId,
            String orgName, String major, String classname, String grade, Double loanamount, String censoredflag,
            String refusereason, String memo, String academicyear, String term, Date createTime, String creator,
            String repayflag, Double repayamount, Date repaydate, Date modifydate, String modifier, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.idcardno = idcardno;
        this.orgId = orgId;
        this.orgName = orgName;
        this.major = major;
        this.classname = classname;
        this.grade = grade;
        this.loanamount = loanamount;
        this.censoredflag = censoredflag;
        this.refusereason = refusereason;
        this.memo = memo;
        this.academicyear = academicyear;
        this.term = term;
        this.createTime = createTime;
        this.creator = creator;
        this.repayflag = repayflag;
        this.repayamount = repayamount;
        this.repaydate = repaydate;
        this.modifydate = modifydate;
        this.modifier = modifier;
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

    @Column(name = "IDCARDNO", length = 18)
    public String getIdcardno() {
        return this.idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
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

    @Column(name = "CLASSNAME", length = 100)
    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Column(name = "GRADE", length = 20)
    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "LOANAMOUNT", precision = 7)
    public Double getLoanamount() {
        return this.loanamount;
    }

    public void setLoanamount(Double loanamount) {
        this.loanamount = loanamount;
    }

    @Column(name = "CENSOREDFLAG", length = 2)
    public String getCensoredflag() {
        return this.censoredflag;
    }

    public void setCensoredflag(String censoredflag) {
        this.censoredflag = censoredflag;
    }

    @Column(name = "REFUSEREASON", length = 200)
    public String getRefusereason() {
        return this.refusereason;
    }

    public void setRefusereason(String refusereason) {
        this.refusereason = refusereason;
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

    @Column(name = "REPAYFLAG", length = 2)
    public String getRepayflag() {
        return this.repayflag;
    }

    public void setRepayflag(String repayflag) {
        this.repayflag = repayflag;
    }

    @Column(name = "REPAYAMOUNT", precision = 7)
    public Double getRepayamount() {
        return this.repayamount;
    }

    public void setRepayamount(Double repayamount) {
        this.repayamount = repayamount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REPAYDATE", length = 7)
    public Date getRepaydate() {
        return this.repaydate;
    }

    public void setRepaydate(Date repaydate) {
        this.repaydate = repaydate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFYDATE", length = 7)
    public Date getModifydate() {
        return this.modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    @Column(name = "MODIFIER", length = 32)
    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

}