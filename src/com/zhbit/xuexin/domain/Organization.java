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
 * Organization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_SYS_ORGANIZATION")
public class Organization implements JSONString,java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 1653182980042373437L;

    // Fields

    private String orgId;

    private String orgName;

    private String parentId;

    private String parentIds;

    private String address;

    private String postCode;

    private String contactMan;

    private String tell;

    private String fax;

    private String email;

    private String status;
    
    private String schoolFlag;

    private String schoolCode;

    private Set<User> users = new HashSet<User>(0);

    // Constructors

    /** default constructor */
    public Organization() {
    }

    /** full constructor */
    public Organization(String orgName, String parentId, String parentIds, String address, String postCode,
            String contactMan, String tell, String fax, String email, String status, String schoolFlag,
            String schoolCode, Set<User> users) {
        this.orgName = orgName;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.address = address;
        this.postCode = postCode;
        this.contactMan = contactMan;
        this.tell = tell;
        this.fax = fax;
        this.email = email;
        this.status = status;
        this.schoolFlag = schoolFlag;
        this.schoolCode = schoolCode;
        this.users = users;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "ORG_NAME", length = 128)
    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    @Column(name = "ADDRESS", length = 128)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "POST_CODE", length = 16)
    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name = "CONTACT_MAN", length = 32)
    public String getContactMan() {
        return this.contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    @Column(name = "TELL", length = 16)
    public String getTell() {
        return this.tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Column(name = "FAX", length = 16)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "EMAIL", length = 32)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "STATUS", length = 16)
    public String getState() {
        return this.status;
    }

    public void setState(String state) {
        this.status = state;
    }
    
    @Column(name = "SCHOOL_FLAG", length = 2)
    public String getSchoolFlag() {
        return this.schoolFlag;
    }

    public void setSchoolFlag(String schoolFlag) {
        this.schoolFlag = schoolFlag;
    }

    @Column(name = "SCHOOL_CODE", length = 20)
    public String getSchoolCode() {
        return this.schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
  
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toJSONString() {
        return "{id='" + orgId + "',orgId='" + orgId + "', orgName='" + orgName + "', parentId='" + parentId + "', parentIds='"
                + parentIds + "', address='" + address + "', postCode='" + postCode + "', contactMan='" + contactMan
                + "', tell='" + tell + "', fax='" + fax + "', email='" + email + "', status='" + status + "', schoolFlag='" + schoolFlag + "', schoolCode='" + schoolCode + "'}";
    }


}