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
 * PostInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_POSTINFO")
public class PostInfo implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 7408359784591270871L;

    private String id;

    private String emsno;

    private String schoolno;

    private String stuId;

    private String studentno;

    private String stuname;

    private String major;

    private String sex;

    private String dispatchtype;

    private String chargeunit;

    private String mailno;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public PostInfo() {
    }

    /** minimal constructor */
    public PostInfo(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public PostInfo(String emsno, String schoolno, String stuId, String studentno, String stuname, String major,
            String sex, String dispatchtype, String chargeunit, String mailno, String memo, Date createTime,
            String creator, String parentOrgId) {
        this.emsno = emsno;
        this.schoolno = schoolno;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.major = major;
        this.sex = sex;
        this.dispatchtype = dispatchtype;
        this.chargeunit = chargeunit;
        this.mailno = mailno;
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

    @Column(name = "EMSNO", length = 30)
    public String getEmsno() {
        return this.emsno;
    }

    public void setEmsno(String emsno) {
        this.emsno = emsno;
    }

    @Column(name = "SCHOOLNO", length = 30)
    public String getSchoolno() {
        return this.schoolno;
    }

    public void setSchoolno(String schoolno) {
        this.schoolno = schoolno;
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

    @Column(name = "MAJOR", length = 100)
    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "SEX", length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "DISPATCHTYPE", length = 20)
    public String getDispatchtype() {
        return this.dispatchtype;
    }

    public void setDispatchtype(String dispatchtype) {
        this.dispatchtype = dispatchtype;
    }

    @Column(name = "CHARGEUNIT", length = 100)
    public String getChargeunit() {
        return this.chargeunit;
    }

    public void setChargeunit(String chargeunit) {
        this.chargeunit = chargeunit;
    }

    @Column(name = "MAILNO", length = 30)
    public String getMailno() {
        return this.mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
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