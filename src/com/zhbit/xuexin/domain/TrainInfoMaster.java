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
 * TrainInfoMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TRAININFO_MASTER")
public class TrainInfoMaster implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -6410023858401291661L;

    private String id;

    private Date trainsdate;

    private String traincode;

    private String trainsgrade;

    private String manager;

    private String trainstopic;

    private String trainscontent;

    private String trainsaddress;

    private String memo;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public TrainInfoMaster() {
    }

    /** full constructor */
    public TrainInfoMaster(Date trainsdate, String traincode, String trainsgrade, String manager, String trainstopic,
            String trainscontent, String trainsaddress, String memo, Date createTime, String creator, String parentOrgId) {
        this.trainsdate = trainsdate;
        this.traincode = traincode;
        this.trainsgrade = trainsgrade;
        this.manager = manager;
        this.trainstopic = trainstopic;
        this.trainscontent = trainscontent;
        this.trainsaddress = trainsaddress;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "TRAINSDATE", length = 7)
    public Date getTrainsdate() {
        return this.trainsdate;
    }

    public void setTrainsdate(Date trainsdate) {
        this.trainsdate = trainsdate;
    }

    @Column(name = "TRAINCODE", length = 20)
    public String getTraincode() {
        return this.traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    @Column(name = "TRAINSGRADE", length = 40)
    public String getTrainsgrade() {
        return this.trainsgrade;
    }

    public void setTrainsgrade(String trainsgrade) {
        this.trainsgrade = trainsgrade;
    }

    @Column(name = "MANAGER", length = 20)
    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Column(name = "TRAINSTOPIC", length = 200)
    public String getTrainstopic() {
        return this.trainstopic;
    }

    public void setTrainstopic(String trainstopic) {
        this.trainstopic = trainstopic;
    }

    @Column(name = "TRAINSCONTENT", length = 500)
    public String getTrainscontent() {
        return this.trainscontent;
    }

    public void setTrainscontent(String trainscontent) {
        this.trainscontent = trainscontent;
    }

    @Column(name = "TRAINSADDRESS", length = 200)
    public String getTrainsaddress() {
        return this.trainsaddress;
    }

    public void setTrainsaddress(String trainsaddress) {
        this.trainsaddress = trainsaddress;
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