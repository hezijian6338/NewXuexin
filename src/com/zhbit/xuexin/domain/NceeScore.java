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
 * NceeScore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_NCEE_SCORE")
public class NceeScore implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -2107146324015915707L;

    // Fields

    private String id;

    private String zkhNo;

    private String idcardno;

    private String stuId;

    private String studentno;

    private String stuname;

    private String coursename;

    private Double score;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    private String memo;

    // Constructors

    /** default constructor */
    public NceeScore() {
    }

    /** minimal constructor */
    public NceeScore(String idcardno) {
        this.idcardno = idcardno;
    }

    /** full constructor */
    public NceeScore(String zkhNo, String idcardno, String stuId, String studentno, String stuname, String coursename,
            Double score, Date createTime, String creator, String parentOrgId, String memo) {
        this.zkhNo = zkhNo;
        this.idcardno = idcardno;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.coursename = coursename;
        this.score = score;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
        this.memo = memo;
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

    @Column(name = "ZKH_NO", length = 16)
    public String getZkhNo() {
        return this.zkhNo;
    }

    public void setZkhNo(String zkhNo) {
        this.zkhNo = zkhNo;
    }

    @Column(name = "IDCARDNO", nullable = false, length = 18)
    public String getIdcardno() {
        return this.idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    @Column(name = "STU_ID", length = 32)
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

    @Column(name = "COURSENAME", length = 100)
    public String getCoursename() {
        return this.coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Column(name = "SCORE", precision = 6)
    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    @Column(name = "MEMO", length = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}