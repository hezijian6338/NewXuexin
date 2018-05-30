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
 * StudentCertificate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_STUDENT_CERTIFICATE")

public class StudentCertificate  implements java.io.Serializable {


    /** 
    * @Fields serialVersionUID : TODO(简单说明是做什么的。) 
    */ 
    private static final long serialVersionUID = -6889302529588334900L;
    // Fields    

     private String id;
     private String stuId;
     private String studentno;
     private String stuname;
     private String certificatename;
     private String description;
     private String docpath;
     private Date createTime;
     private String creator;
     private String parentOrgId;


    // Constructors

    /** default constructor */
    public StudentCertificate() {
    }

	/** minimal constructor */
    public StudentCertificate(String stuId) {
        this.stuId = stuId;
    }
    
    /** full constructor */
    public StudentCertificate(String stuId, String studentno, String stuname, String certificatename, String description, String docpath, Date createTime, String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.certificatename = certificatename;
        this.description = description;
        this.docpath = docpath;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
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
    
    @Column(name="CERTIFICATENAME", length=50)

    public String getCertificatename() {
        return this.certificatename;
    }
    
    public void setCertificatename(String certificatename) {
        this.certificatename = certificatename;
    }
    
    @Column(name="DESCRIPTION", length=200)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
   








}