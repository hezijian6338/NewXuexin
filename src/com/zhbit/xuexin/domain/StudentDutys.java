package com.zhbit.xuexin.domain;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * StudentDutys entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STUDENT_DUTYS")
public class StudentDutys implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -8356468570035290022L;

    private String id;

    private String grade;

    private String classname;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String telno;

    private String shorttelno;

    private String duty;

    private String address;

    private String memo;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public StudentDutys() {
    }

    /** minimal constructor */
    public StudentDutys(String stuId, String studentno) {
        this.stuId = stuId;
        this.studentno = studentno;
    }

    /** full constructor */
    public StudentDutys(String grade, String classname, String stuId, String studentno, String stuname, String sex,
            String telno, String shorttelno, String duty, String address, String memo, String parentOrgId) {
        this.grade = grade;
        this.classname = classname;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.telno = telno;
        this.shorttelno = shorttelno;
        this.duty = duty;
        this.address = address;
        this.memo = memo;
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

    @Column(name = "GRADE", length = 40)
    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "CLASSNAME", length = 100)
    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Column(name = "STU_ID", nullable = false, length = 32)
    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "STUDENTNO", nullable = false, length = 16)
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

    @Column(name = "TELNO", length = 16)
    public String getTelno() {
        return this.telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    @Column(name = "SHORTTELNO", length = 16)
    public String getShorttelno() {
        return this.shorttelno;
    }

    public void setShorttelno(String shorttelno) {
        this.shorttelno = shorttelno;
    }

    @Column(name = "DUTY", length = 20)
    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Column(name = "ADDRESS", length = 100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "MEMO", length = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

}