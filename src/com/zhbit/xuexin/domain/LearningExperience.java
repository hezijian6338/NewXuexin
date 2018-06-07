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
 * LearningExperience entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LEARNINGEXPERIENCE")
public class LearningExperience implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -1016338289739590421L;

    // Fields

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String duration;

    private String schoolname;

    private String duty;

    private String witness;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public LearningExperience() {
    }

    /** minimal constructor */
    public LearningExperience(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public LearningExperience(String stuId, String studentno, String stuname, String duration, String schoolname,
            String duty, String witness, Date createTime, String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.duration = duration;
        this.schoolname = schoolname;
        this.duty = duty;
        this.witness = witness;
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

    @Column(name = "DURATION", length = 50)
    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Column(name = "SCHOOLNAME", length = 200)
    public String getSchoolname() {
        return this.schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    @Column(name = "DUTY", length = 20)
    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Column(name = "WITNESS", length = 20)
    public String getWitness() {
        return this.witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
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