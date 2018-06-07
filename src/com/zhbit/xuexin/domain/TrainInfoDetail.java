package com.zhbit.xuexin.domain;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TrainInfoDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TRAININFO_DETAIL")
public class TrainInfoDetail implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 7352475958994557034L;

    private String id;

    private String trainCode;

    private String stuId;

    private String studentno;

    private String stuname;

    private String trainsresult;

    private String memo;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public TrainInfoDetail() {
    }

    /** minimal constructor */
    public TrainInfoDetail(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public TrainInfoDetail(String trainCode, String stuId, String studentno, String stuname, String trainsresult,
            String memo, String parentOrgId) {
        this.trainCode = trainCode;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.trainsresult = trainsresult;
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

    @Column(name = "TRAIN_CODE", length = 20)
    public String getTrainCode() {
        return this.trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
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

    @Column(name = "TRAINSRESULT", length = 100)
    public String getTrainsresult() {
        return this.trainsresult;
    }

    public void setTrainsresult(String trainsresult) {
        this.trainsresult = trainsresult;
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