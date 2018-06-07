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

import net.sf.json.JSONString;

import org.hibernate.annotations.GenericGenerator;

/**
 * LoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SYS_LOGIN_LOG")
public class LoginLog implements JSONString,java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -2931782625863061001L;

    // Fields

    private String logicId;

    private String employNo;

    private Date loginTime;

    private String loginIp;

    private Integer ifSuccess;
    
    private String parentOrgId;

    // Constructors

    /** default constructor */
    public LoginLog() {
    }

    /** full constructor */
    public LoginLog(String employNo, Date loginTime, String loginIp, Integer ifSuccess, String parentOrgId) {
        this.employNo = employNo;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.ifSuccess = ifSuccess;
        this.parentOrgId = parentOrgId;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "LOGIC_ID", unique = true, nullable = false, length = 32)
    public String getLogicId() {
        return this.logicId;
    }

    public void setLogicId(String logicId) {
        this.logicId = logicId;
    }

    @Column(name = "EMPLOY_NO", length = 32)
    public String getEmployNo() {
        return this.employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo = employNo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LOGIN_TIME", length = 7)
    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "LOGIN_IP", length = 100)
    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Column(name = "IF_SUCCESS", precision = 22, scale = 0)
    public Integer getIfSuccess() {
        return this.ifSuccess;
    }

    public void setIfSuccess(Integer ifSuccess) {
        this.ifSuccess = ifSuccess;
    }
    
    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    @Override
    public String toJSONString() {
        return "{logicId='" + logicId + "', employNo='" + employNo + "', loginTime='" + loginTime + "', loginIp='"
                + loginIp + "', ifSuccess='" + ifSuccess + "', parentOrgId='" + parentOrgId + "'}";
    }

}