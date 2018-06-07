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
 * GuidanceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_GUIDANCEINFO")
public class GuidanceInfo implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 8245440848186098856L;

    // Fields

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String classname;

    private Date guiddate;

    private String guidcontent;

    private String guidaddress;

    private String counselor;

    private String docpath;

    private String mediapath;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public GuidanceInfo() {
    }

    /** minimal constructor */
    public GuidanceInfo(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public GuidanceInfo(String stuId, String studentno, String stuname, String classname, Date guiddate,
            String guidcontent, String guidaddress, String counselor, String docpath, String mediapath,
            Date createTime, String creator, String parentOrgId) {
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.classname = classname;
        this.guiddate = guiddate;
        this.guidcontent = guidcontent;
        this.guidaddress = guidaddress;
        this.counselor = counselor;
        this.docpath = docpath;
        this.mediapath = mediapath;
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

    @Column(name = "CLASSNAME", length = 200)
    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "GUIDDATE", length = 7)
    public Date getGuiddate() {
        return this.guiddate;
    }

    public void setGuiddate(Date guiddate) {
        this.guiddate = guiddate;
    }

    @Column(name = "GUIDCONTENT", length = 500)
    public String getGuidcontent() {
        return this.guidcontent;
    }

    public void setGuidcontent(String guidcontent) {
        this.guidcontent = guidcontent;
    }

    @Column(name = "GUIDADDRESS", length = 200)
    public String getGuidaddress() {
        return this.guidaddress;
    }

    public void setGuidaddress(String guidaddress) {
        this.guidaddress = guidaddress;
    }

    @Column(name = "COUNSELOR", length = 20)
    public String getCounselor() {
        return this.counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    @Column(name = "DOCPATH", length = 200)
    public String getDocpath() {
        return this.docpath;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

    @Column(name = "MEDIAPATH", length = 200)
    public String getMediapath() {
        return this.mediapath;
    }

    public void setMediapath(String mediapath) {
        this.mediapath = mediapath;
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