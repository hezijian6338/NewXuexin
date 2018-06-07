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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.json.JSONString;

import org.hibernate.annotations.GenericGenerator;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SYS_ROLE")
public class Role implements JSONString,java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 7132342397770147802L;

    // Fields

    private String roleId;

    private String roleName;

    private Date createTime;

    private String roleNo;

    private String memo;
    
    private String parentOrgId;

    private Set<RoleAuthority> roleAuthorities = new HashSet<RoleAuthority>(0);

    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** full constructor */
    public Role(String roleName, Date createTime, String roleNo, String memo, Set<RoleAuthority> roleAuthorities,
            Set<UserRole> userRoles) {
        this.roleName = roleName;
        this.createTime = createTime;
        this.roleNo = roleNo;
        this.memo = memo;
        this.roleAuthorities = roleAuthorities;
        this.userRoles = userRoles;
    }
    
    /** full constructor */
    public Role(String roleName, Date createTime, String roleNo, String memo, String parentOrgId,
            Set<RoleAuthority> roleAuthorities, Set<UserRole> userRoles) {
        this.roleName = roleName;
        this.createTime = createTime;
        this.roleNo = roleNo;
        this.memo = memo;
        this.parentOrgId = parentOrgId;
        this.roleAuthorities = roleAuthorities;
        this.userRoles = userRoles;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "ROLE_NAME", length = 64)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "ROLE_NO", length = 32)
    public String getRoleNo() {
        return this.roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<RoleAuthority> getRoleAuthorities() {
        return this.roleAuthorities;
    }

    public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    
    public String toJSONString() {
        return "{roleId='" + roleId + "', roleNo='" + roleNo + "', roleName='"
                + roleName + "', createTime='" + createTime + "', memo='"
                + memo + "', parentOrgId='" + parentOrgId + "'}";
    }

}