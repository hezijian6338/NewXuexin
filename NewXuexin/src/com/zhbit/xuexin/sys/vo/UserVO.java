package com.zhbit.xuexin.sys.vo;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONString;

import com.zhbit.xuexin.domain.Organization;

/**
 * 用户值对象
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-9 下午9:36:23
 * @version 1.0
 */
public class UserVO implements JSONString {

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

    /**
     * 归属角色id列表
     */
    private List<String> roleIds;

    /**
     * 归属角色名称列表
     */
    private List<String> roleNames;

    public UserVO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static final String STRUCT = "(user.userId, user.organization, user.employNo, user.employName, user.createTime, user.sex, user.tell, user.status, user.address, user.email, user.password, user.userType)";

    public UserVO(String userId, Organization organization, String employNo, String employName, Date createTime,
    		String sex, String tell, Integer status, String address, String email, String password, String userType) {
        super();
        this.userId = userId;
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
    }

    public String toJSONString() {
        return "{userId='" + userId + "', organization={orgId='" + organization.getOrgId() + "',orgName='"
                + organization.getOrgName() + "'}, employNo='" + employNo + "', employName='" + employName
                + "', createTime='" + createTime + "', sex='" + sex + "', tell='" + tell + "', status='" + status
                + "', address='" + address + "', email='" + email + "', password='" + password + "', userType='"
                + userType + "', roleIds='" + roleIds + "', roleNames='" + roleNames + "'}";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getEmployNo() {
        return employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo = employNo;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

}
