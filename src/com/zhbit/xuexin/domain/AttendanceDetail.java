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
 * AttendanceDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ATTENDANCE_DETAIL")
public class AttendanceDetail implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 4570556575125113308L;

    private String id;

    private String selectedcourseno;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String classname;

    private Date attendanceTime;

    private String attendanceStatus;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public AttendanceDetail() {
    }
    //xucaikai2017.04.17
    /** minimal constructor */
    public AttendanceDetail(String stuId) {
        this.stuId = stuId;

    }

 /*   *//** minimal constructor *//*
    public AttendanceDetail(String stuId, String studentno) {
        this.stuId = stuId;
        this.studentno = studentno;
    }*/
   
    /** full constructor */
    public AttendanceDetail(String selectedcourseno, String stuId, String studentno, String stuname, String sex,
            String classname, Date attendanceTime, String attendanceStatus, Date createTime, String creator,
            String parentOrgId) {
        this.selectedcourseno = selectedcourseno;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.classname = classname;
        this.attendanceTime = attendanceTime;
        this.attendanceStatus = attendanceStatus;
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

    @Column(name = "SELECTEDCOURSENO", length = 50)
    public String getSelectedcourseno() {
        return this.selectedcourseno;
    }

    public void setSelectedcourseno(String selectedcourseno) {
        this.selectedcourseno = selectedcourseno;
    }

    @Column(name = "STU_ID", nullable = false, length = 32)
    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "STUDENTNO", nullable = false, length =16)
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

    @Column(name = "CLASSNAME", length = 100)
    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ATTENDANCE_TIME", length = 7)
    public Date getAttendanceTime() {
        return this.attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    @Column(name = "ATTENDANCE_STATUS", length = 32)
    public String getAttendanceStatus() {
        return this.attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
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