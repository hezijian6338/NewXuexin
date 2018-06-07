package com.zhbit.xuexin.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_TEA_REWARD_PUNISHMENT")
public class TeaRewardPunishment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rid;
	private String id;
	private String employno;
	private String employname;
	private String orgId;
	private String orgName;
	private Date happenedDate;
	private Date rewardDate;
	private String fileNo;
	private String description;
	private String rewardType;
    private String rpOrgId;
    private String rpOrgName;
    private String docpath;
    private Date createTime;
    private String creator;
    private String parentOrgId;
    private String memo;
    private String duty;
    private String acdemictitle;
	

    //  Constructors
    /** default constructor */
    public TeaRewardPunishment() {
	}
    
    /** minimal constructor */
    public TeaRewardPunishment(String id) {
        this.id = id;
    }
    
    /** full constructor */
	public TeaRewardPunishment(String rid, String id, String employno,
			String employname, String orgId, String orgName, Date happenedDate,
			Date rewardDate, String fileNo, String description,
			String rewardType, String rpOrgId, String rpOrgName,
			String docpath, Date createTime, String creator,
			String parentOrgId, String memo, String duty, String acdemictitle) {
		super();
		this.rid = rid;
		this.id = id;
		this.employno = employno;
		this.employname = employname;
		this.orgId = orgId;
		this.orgName = orgName;
		this.happenedDate = happenedDate;
		this.rewardDate = rewardDate;
		this.fileNo = fileNo;
		this.description = description;
		this.rewardType = rewardType;
		this.rpOrgId = rpOrgId;
		this.rpOrgName = rpOrgName;
		this.docpath = docpath;
		this.createTime = createTime;
		this.creator = creator;
		this.parentOrgId = parentOrgId;
		this.memo = memo;
		this.duty = duty;
		this.acdemictitle = acdemictitle;
	}

	// Property accessors
	@GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
	
	@Column(name="RID", unique=true, nullable=false, length=32)
	
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	
	@Column(name="ID", nullable=false, length=32)
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="EMPLOY_NO", length=10)
	
	public String getEmployno() {
		return employno;
	}

	public void setEmployno(String employno) {
		this.employno = employno;
	}
	
	@Column(name="EMPLOY_NAME", length=64)
	
	public String getEmployname() {
		return employname;
	}

	public void setEmployname(String employname) {
		this.employname = employname;
	}
	
	@Column(name="ORG_ID", length=32)
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Column(name="ORG_NAME", length=100)
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="HAPPENED_DATE", length=7)
	
	public Date getHappenedDate() {
		return happenedDate;
	}

	public void setHappenedDate(Date happenedDate) {
		this.happenedDate = happenedDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="REWARD_DATE", length=7)
	
	public Date getRewardDate() {
		return rewardDate;
	}

	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}
	
	@Column(name="FILE_NO", length=20)
	
	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
	@Column(name="DESCRIPTION", length=200)
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="REWARD_TYPE", length=30)
	
	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	
	@Column(name="RP_ORG_ID", length=32)
	
	public String getRpOrgId() {
		return rpOrgId;
	}

	public void setRpOrgId(String rpOrgId) {
		this.rpOrgId = rpOrgId;
	}
	
	@Column(name="RP_ORG_NAME", length=100)
	
	public String getRpOrgName() {
		return rpOrgName;
	}

	public void setRpOrgName(String rpOrgName) {
		this.rpOrgName = rpOrgName;
	}
	
	@Column(name="DOCPATH", length=200)
	
	public String getDocpath() {
		return docpath;
	}

	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}
	
	@Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="CREATOR", length=32)
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Column(name="PARENT_ORG_ID", length=32)
	
	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	
	@Column(name="MEMO", length=200)
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name="DUTY", length=20)
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	@Column(name="ACDEMICTITLE", length=20)
	public String getAcdemictitle() {
		return acdemictitle;
	}

	public void setAcdemictitle(String acdemictitle) {
		this.acdemictitle = acdemictitle;
	}

    
    
    
	
}
