package com.zhbit.xuexin.domain;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.json.JSONString;

import org.hibernate.annotations.GenericGenerator;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SYS_USER")
public class User implements JSONString,java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 5192902348951904950L;

    // Fields

    private String userId;

    private Organization organization;

    private String employNo;

    private String employName;

    private Date createTime;

    private String sex;

    private String tell;

    private Integer status;

    private String address;

    private String email;

    private String password;

    private String userType;
    
    private String parentOrgId;

    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    // Constructors

    /** default constructor */
    public User() {
    }

    /** full constructor */
    public User(Organization organization, String employNo, String employName, Date createTime, String sex,
            String tell, Integer status, String address, String email, String password, String userType,
            String parentOrgId, Set<UserRole> userRoles) {
        this.organization = organization;
        this.employNo = employNo;
        this.employName = employName;
        this.createTime = createTime;
        this.sex = sex;
        this.tell = tell;
        this.status = status;
        this.address = address;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.parentOrgId = parentOrgId;
        this.userRoles = userRoles;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID", unique = true, nullable = false, length = 32)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_ID")
    public Organization getOrganization() {
        return this.organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Column(name = "EMPLOY_NO", length = 16)
    public String getEmployNo() {
        return this.employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo = employNo;
    }

    @Column(name = "EMPLOY_NAME", length = 64)
    public String getEmployName() {
        return this.employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "SEX",length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "TELL", length = 20)
    public String getTell() {
        return this.tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Column(name = "STATUS", precision = 22, scale = 0)
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_TYPE", length = 2)
    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    
    public String toJSONString() {
        return "{userId='" + userId + "', organization={orgId='"
                + organization.getOrgId() + "',orgName='"
                + organization.getOrgName() + "'}, employNo='" + employNo
                + "', employName='" + employName + "', createTime='"
                + createTime + "', sex='" + sex + "', tell='" + tell
                + "', status='" + status + "', address='" + address
                + "', email='" + email + "', password='" + password + "', userType='"+userType+"', parentOrgId='" + parentOrgId + "'}";
    }

}