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
 * PoliticalStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_POLITICALSTATUS")
public class PoliticalStatus implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -9041411264904810245L;

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private Date joindate;

    private String politicalstatus;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public PoliticalStatus() {
    }

    /** minimal constructor */
    public PoliticalStatus(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public PoliticalStatus(String stuId, String studentno, String stuname, Date joindate, String politicalstatus,
            String memo, Date createTime, String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.joindate = joindate;
        this.politicalstatus = politicalstatus;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINDATE", length = 7)
    public Date getJoindate() {
        return this.joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    @Column(name = "POLITICALSTATUS", length = 20)
    public String getPoliticalstatus() {
        return this.politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
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