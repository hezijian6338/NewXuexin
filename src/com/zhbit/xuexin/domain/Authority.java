package com.zhbit.xuexin.domain;

// default package

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

import net.sf.json.JSONString;

import org.hibernate.annotations.GenericGenerator;

/**
 * Authority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SYS_AUTHORITY")
public class Authority implements JSONString,java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -1397032583255590314L;

    private String authorityId;

    private String parentId;

    private String parentIds;

    private String authorityName;

    private Integer authorityType;

    private String moduleName;

    private String url;

    private String operation;

    private String menuNo;

    private String memo;

    private Set<RoleAuthority> roleAuthorities = new HashSet<RoleAuthority>(0);

    // Constructors

    /** default constructor */
    public Authority() {
    }

    /** minimal constructor */
    public Authority(String authorityName, Integer authorityType) {
        this.authorityName = authorityName;
        this.authorityType = authorityType;
    }

    /** full constructor */
    public Authority(String parentId, String parentIds, String authorityName, Integer authorityType, String moduleName,
            String url, String operation, String menuNo, String memo, Set<RoleAuthority> roleAuthorities) {
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.authorityName = authorityName;
        this.authorityType = authorityType;
        this.moduleName = moduleName;
        this.url = url;
        this.operation = operation;
        this.menuNo = menuNo;
        this.memo = memo;
        this.roleAuthorities = roleAuthorities;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 32)
    public String getAuthorityId() {
        return this.authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    @Column(name = "PARENT_ID", length = 32)
    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "PARENT_IDS", length = 1000)
    public String getParentIds() {
        return this.parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Column(name = "AUTHORITY_NAME", nullable = false, length = 64)
    public String getAuthorityName() {
        return this.authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Column(name = "AUTHORITY_TYPE", nullable = false, precision = 22, scale = 0)
    public Integer getAuthorityType() {
        return this.authorityType;
    }

    public void setAuthorityType(Integer authorityType) {
        this.authorityType = authorityType;
    }

    @Column(name = "MODULE_NAME", length = 64)
    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Column(name = "URL", length = 200)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "OPERATION", length = 32)
    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Column(name = "MENU_NO", length = 32)
    public String getMenuNo() {
        return this.menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    @Column(name = "MEMO", length = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authority")
    public Set<RoleAuthority> getRoleAuthorities() {
        return this.roleAuthorities;
    }

    public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }
    
    public String toJSONString() {
        return "{id='" + authorityId + "', parentId='" + parentId
                + "', parentIds='" + parentIds + "', authorityName='"
                + authorityName + "', authorityType='" + authorityType
                + "', moduleName='" + moduleName + "', url='" + url
                + "', operation='" + operation + "', memo='" + memo + "', menuNo='" + menuNo + "'}";
    }

}