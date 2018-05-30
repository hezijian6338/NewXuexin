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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * Students entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STUDENTS", uniqueConstraints = @UniqueConstraint(columnNames = "STUDENTNO"))
public class Students implements java.io.Serializable {

    // Fields

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 1105318865752898618L;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private Date birthday;

    private String politicalstatus;

    private String nation;

    private String nativeplace;

    private String fromPlace;

    private String idcardno;

    private String orgId;

    private String orgName;

    private String department;

    private String major;

    private String majorfield;

    private String majorcategories;

    private String cultivatedirection;

    private String classname;

    private Integer educationsystem;

    private Integer schoolinglength;

    private Date acceptancedate;

    private String middleschool;

    private String mobileno;

    private String familytelno;

    private String postcode;

    private String travelrange;

    private String address;

    private String skill;

    private String selfdescription;

    private String awards;

    private String schoolstatus;

    private String dqszj;

    private String photopath;

    private String graduateflag;

    private String alumniflag;

    private Date createTime;

    private String creator;

    private String password;

    private String parentOrgId;
    
    private String grade;

    // Constructors

    /** default constructor */
    public Students() {
    }

    /** full constructor */
    public Students(String studentno, String stuname, String sex, Date birthday, String politicalstatus, String nation,
            String nativeplace, String fromPlace, String idcardno, String orgId, String orgName, String department,
            String major, String majorfield, String majorcategories, String cultivatedirection, String classname,
            Integer educationsystem, Integer schoolinglength, Date acceptancedate, String middleschool,
            String mobileno, String familytelno, String postcode, String travelrange, String address, String skill,
            String selfdescription, String awards, String schoolstatus, String dqszj, String photopath,
            String graduateflag, String alumniflag, Date createTime, String creator, String password, String parentOrgId) {
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.birthday = birthday;
        this.politicalstatus = politicalstatus;
        this.nation = nation;
        this.nativeplace = nativeplace;
        this.fromPlace = fromPlace;
        this.idcardno = idcardno;
        this.orgId = orgId;
        this.orgName = orgName;
        this.department = department;
        this.major = major;
        this.majorfield = majorfield;
        this.majorcategories = majorcategories;
        this.cultivatedirection = cultivatedirection;
        this.classname = classname;
        this.educationsystem = educationsystem;
        this.schoolinglength = schoolinglength;
        this.acceptancedate = acceptancedate;
        this.middleschool = middleschool;
        this.mobileno = mobileno;
        this.familytelno = familytelno;
        this.postcode = postcode;
        this.travelrange = travelrange;
        this.address = address;
        this.skill = skill;
        this.selfdescription = selfdescription;
        this.awards = awards;
        this.schoolstatus = schoolstatus;
        this.dqszj = dqszj;
        this.photopath = photopath;
        this.graduateflag = graduateflag;
        this.alumniflag = alumniflag;
        this.createTime = createTime;
        this.creator = creator;
        this.password = password;
        this.parentOrgId = parentOrgId;
    }
    public Students(String studentno, String stuname, String sex, Date birthday, String politicalstatus, String nation,
            String nativeplace, String fromPlace, String idcardno, String orgId, String orgName, String department,
            String major, String majorfield, String majorcategories, String cultivatedirection, String classname,
            Integer educationsystem, Integer schoolinglength, Date acceptancedate, String middleschool,
            String mobileno, String familytelno, String postcode, String travelrange, String address, String skill,
            String selfdescription, String awards, String schoolstatus, String dqszj, String photopath,
            String graduateflag, String alumniflag, Date createTime, String creator, String password, String parentOrgId,String grade) {
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.birthday = birthday;
        this.politicalstatus = politicalstatus;
        this.nation = nation;
        this.nativeplace = nativeplace;
        this.fromPlace = fromPlace;
        this.idcardno = idcardno;
        this.orgId = orgId;
        this.orgName = orgName;
        this.department = department;
        this.major = major;
        this.majorfield = majorfield;
        this.majorcategories = majorcategories;
        this.cultivatedirection = cultivatedirection;
        this.classname = classname;
        this.educationsystem = educationsystem;
        this.schoolinglength = schoolinglength;
        this.acceptancedate = acceptancedate;
        this.middleschool = middleschool;
        this.mobileno = mobileno;
        this.familytelno = familytelno;
        this.postcode = postcode;
        this.travelrange = travelrange;
        this.address = address;
        this.skill = skill;
        this.selfdescription = selfdescription;
        this.awards = awards;
        this.schoolstatus = schoolstatus;
        this.dqszj = dqszj;
        this.photopath = photopath;
        this.graduateflag = graduateflag;
        this.alumniflag = alumniflag;
        this.createTime = createTime;
        this.creator = creator;
        this.password = password;
        this.parentOrgId = parentOrgId;
        this.grade = grade;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "STU_ID", unique = true, nullable = false, length = 32)
    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "STUDENTNO", unique = true, length = 16)
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

    @Column(name = "SEX", length = 2)
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", length = 7)
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "POLITICALSTATUS", length = 20)
    public String getPoliticalstatus() {
        return this.politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    @Column(name = "NATION", length = 50)
    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Column(name = "NATIVEPLACE", length = 20)
    public String getNativeplace() {
        return this.nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    @Column(name = "FROM_PLACE", length = 100)
    public String getFromPlace() {
        return this.fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    @Column(name = "IDCARDNO", length = 18)
    public String getIdcardno() {
        return this.idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    @Column(name = "ORG_ID", length = 32)
    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "ORG_NAME", length = 100)
    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name = "DEPARTMENT", length = 100)
    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "MAJOR", length = 100)
    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "MAJORFIELD", length = 100)
    public String getMajorfield() {
        return this.majorfield;
    }

    public void setMajorfield(String majorfield) {
        this.majorfield = majorfield;
    }

    @Column(name = "MAJORCATEGORIES", length = 100)
    public String getMajorcategories() {
        return this.majorcategories;
    }

    public void setMajorcategories(String majorcategories) {
        this.majorcategories = majorcategories;
    }

    @Column(name = "CULTIVATEDIRECTION", length = 100)
    public String getCultivatedirection() {
        return this.cultivatedirection;
    }

    public void setCultivatedirection(String cultivatedirection) {
        this.cultivatedirection = cultivatedirection;
    }

    @Column(name = "CLASSNAME", length = 100)
    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Column(name = "EDUCATIONSYSTEM", precision = 22, scale = 0)
    public Integer getEducationsystem() {
        return this.educationsystem;
    }

    public void setEducationsystem(Integer educationsystem) {
        this.educationsystem = educationsystem;
    }

    @Column(name = "SCHOOLINGLENGTH", precision = 22, scale = 0)
    public Integer getSchoolinglength() {
        return this.schoolinglength;
    }

    public void setSchoolinglength(Integer schoolinglength) {
        this.schoolinglength = schoolinglength;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ACCEPTANCEDATE", length = 7)
    public Date getAcceptancedate() {
        return this.acceptancedate;
    }

    public void setAcceptancedate(Date acceptancedate) {
        this.acceptancedate = acceptancedate;
    }

    @Column(name = "MIDDLESCHOOL", length = 100)
    public String getMiddleschool() {
        return this.middleschool;
    }

    public void setMiddleschool(String middleschool) {
        this.middleschool = middleschool;
    }

    @Column(name = "MOBILENO", length = 11)
    public String getMobileno() {
        return this.mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    @Column(name = "FAMILYTELNO", length = 20)
    public String getFamilytelno() {
        return this.familytelno;
    }

    public void setFamilytelno(String familytelno) {
        this.familytelno = familytelno;
    }

    @Column(name = "POSTCODE", length = 6)
    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Column(name = "TRAVELRANGE", length = 50)
    public String getTravelrange() {
        return this.travelrange;
    }

    public void setTravelrange(String travelrange) {
        this.travelrange = travelrange;
    }

    @Column(name = "ADDRESS", length = 200)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "SKILL", length = 500)
    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Column(name = "SELFDESCRIPTION", length = 500)
    public String getSelfdescription() {
        return this.selfdescription;
    }

    public void setSelfdescription(String selfdescription) {
        this.selfdescription = selfdescription;
    }

    @Column(name = "AWARDS", length = 500)
    public String getAwards() {
        return this.awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @Column(name = "SCHOOLSTATUS", length = 20)
    public String getSchoolstatus() {
        return this.schoolstatus;
    }

    public void setSchoolstatus(String schoolstatus) {
        this.schoolstatus = schoolstatus;
    }

    @Column(name = "DQSZJ", length = 20)
    public String getDqszj() {
        return this.dqszj;
    }

    public void setDqszj(String dqszj) {
        this.dqszj = dqszj;
    }

    @Column(name = "PHOTOPATH", length = 200)
    public String getPhotopath() {
        return this.photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    @Column(name = "GRADUATEFLAG", length = 2)
    public String getGraduateflag() {
        return this.graduateflag;
    }

    public void setGraduateflag(String graduateflag) {
        this.graduateflag = graduateflag;
    }

    @Column(name = "ALUMNIFLAG", length = 2)
    public String getAlumniflag() {
        return this.alumniflag;
    }

    public void setAlumniflag(String alumniflag) {
        this.alumniflag = alumniflag;
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

    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "PARENT_ORG_ID", length = 32)
    public String getParentOrgId() {
        return this.parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }
    
    @Column(name = "GRADE", length = 20)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
    
}