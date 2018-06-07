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
 * CourseInfoStudents entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_COURSEINFO_STUDENTS")

public class CourseInfoStudents  implements java.io.Serializable {


    // Fields    

     /** 
    * @Fields serialVersionUID : TODO(简单说明是做什么的。) 
    */ 
    private static final long serialVersionUID = -9164635921716743364L;
     private String id;
     private String coursecode;
     private String coursename;
     private String stuId;
     private String studentno;
     private String stuname;
     private String orgId;
     private String orgName;
     private String classname;
     private String majorcode;
     private String major;
     private String retakeflag;
     private String usualscore;
     private String middlescore;
     private String endscore;
     private String labscore;
     private String finalscore;
     private String convertscore;
     private String resitscore;
     private String resitmemo;
     private String repairscore;
     private Double gradepoint;
     private String memo;
     private Date createTime;
     private String creator;
     private String parentOrgId;
     private Integer totalhours;
     
     
     //2017.03.29-xucaikai添加属性

     private String grade;
     private String academicYear;
     private String term;
     private String employNo;//教师工号
     private String employName;//教师姓名
     private String selectedCoureNo;
     private String courseType;
     private String credit;


    // Constructors

    /** default constructor */
    public CourseInfoStudents() {
    }

	/** minimal constructor */
    public CourseInfoStudents(String studentno) {
        this.studentno = studentno;
    }
    
    /** full constructor */
    public CourseInfoStudents(String coursecode, String coursename, String stuId, String studentno, String stuname, String orgId, String orgName, String classname, String majorcode, String major, String retakeflag, String usualscore, String middlescore, String endscore, String labscore, String finalscore, String convertscore, String resitscore, String resitmemo, String repairscore, Double gradepoint, String memo, Date createTime, String creator, String parentOrgId
    		,String grade,String academicYear,String term,String employNo,String employName,String selectedCoureNo,String courseType,String credit,Integer totalhours) {
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.orgId = orgId;
        this.orgName = orgName;
        this.classname = classname;
        this.majorcode = majorcode;
        this.major = major;
        this.retakeflag = retakeflag;
        this.usualscore = usualscore;
        this.middlescore = middlescore;
        this.endscore = endscore;
        this.labscore = labscore;
        this.finalscore = finalscore;
        this.convertscore = convertscore;
        this.resitscore = resitscore;
        this.resitmemo = resitmemo;
        this.repairscore = repairscore;
        this.gradepoint = gradepoint;
        this.memo = memo;
        this.createTime = createTime;
        this.creator = creator;
        this.parentOrgId = parentOrgId;
        
        //
        this.grade=grade;
        this.academicYear=academicYear;
        this.term=term;
        this.employNo=employNo;
        this.employName=employName;
        this.selectedCoureNo=selectedCoureNo;
        this.courseType=courseType;
        this.credit=credit;
        this.totalhours=totalhours;
    }

    @Column(name = "TOTALHOURS", precision = 22, scale = 0)
    public Integer getTotalhours() {
		return totalhours;
	}

	public void setTotalhours(Integer totalhours) {
		this.totalhours = totalhours;
	}

	// Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="COURSECODE", length=20)

    public String getCoursecode() {
        return this.coursecode;
    }
    
    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }
    
    @Column(name="COURSENAME", length=100)

    public String getCoursename() {
        return this.coursename;
    }
    
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    
    @Column(name="STU_ID", length=32)

    public String getStuId() {
        return this.stuId;
    }
    
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
    
    @Column(name="STUDENTNO", nullable=false, length=16)

    public String getStudentno() {
        return this.studentno;
    }
    
    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    @Column(name="STUNAME", length=20)

    public String getStuname() {
        return this.stuname;
    }
    
    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
    
    @Column(name="ORG_ID", length=32)

    public String getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    @Column(name="ORG_NAME", length=100)

    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    @Column(name="CLASSNAME", length=100)

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }
    
    @Column(name="MAJORCODE", length=10)

    public String getMajorcode() {
        return this.majorcode;
    }
    
    public void setMajorcode(String majorcode) {
        this.majorcode = majorcode;
    }
    
    @Column(name="MAJOR", length=100)

    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    @Column(name="RETAKEFLAG", length=2)

    public String getRetakeflag() {
        return this.retakeflag;
    }
    
    public void setRetakeflag(String retakeflag) {
        this.retakeflag = retakeflag;
    }
    
    @Column(name="USUALSCORE", length=20)

    public String getUsualscore() {
        return this.usualscore;
    }
    
    public void setUsualscore(String usualscore) {
        this.usualscore = usualscore;
    }
    
    @Column(name="MIDDLESCORE", length=20)

    public String getMiddlescore() {
        return this.middlescore;
    }
    
    public void setMiddlescore(String middlescore) {
        this.middlescore = middlescore;
    }
    
    @Column(name="ENDSCORE", length=20)

    public String getEndscore() {
        return this.endscore;
    }
    
    public void setEndscore(String endscore) {
        this.endscore = endscore;
    }
    
    @Column(name="LABSCORE", length=20)

    public String getLabscore() {
        return this.labscore;
    }
    
    public void setLabscore(String labscore) {
        this.labscore = labscore;
    }
    
    @Column(name="FINALSCORE", length=20)

    public String getFinalscore() {
        return this.finalscore;
    }
    
    public void setFinalscore(String finalscore) {
        this.finalscore = finalscore;
    }
    
    @Column(name="CONVERTSCORE", length=20)

    public String getConvertscore() {
        return this.convertscore;
    }
    
    public void setConvertscore(String convertscore) {
        this.convertscore = convertscore;
    }
    
    @Column(name="RESITSCORE", length=20)

    public String getResitscore() {
        return this.resitscore;
    }
    
    public void setResitscore(String resitscore) {
        this.resitscore = resitscore;
    }
    
    @Column(name="RESITMEMO", length=200)

    public String getResitmemo() {
        return this.resitmemo;
    }
    
    public void setResitmemo(String resitmemo) {
        this.resitmemo = resitmemo;
    }
    
    @Column(name="REPAIRSCORE", length=20)

    public String getRepairscore() {
        return this.repairscore;
    }
    
    public void setRepairscore(String repairscore) {
        this.repairscore = repairscore;
    }
    
    @Column(name="GRADEPOINT", precision=5)

    public Double getGradepoint() {
        return this.gradepoint;
    }
    
    public void setGradepoint(Double gradepoint) {
        this.gradepoint = gradepoint;
    }
    
    @Column(name="MEMO", length=200)

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATOR", length=32)

    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Column(name="PARENT_ORG_ID", length=32)

    public String getParentOrgId() {
        return this.parentOrgId;
    }
    
    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }
   

    //2017.03.29-xucaikai添加属性get.set方法
    @Column(name="GRADE" ,length=40)
	public String getGarde() {
		return grade;
	}

	public void setGarde(String grade) {
		this.grade = grade;
	}
	@Column(name="ACADEMICYEAR", length=20)
	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	@Column(name="TERM", length=20)
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	@Column(name="EMPLOY_NO", length=50)
	public String getEmployNo() {
		return employNo;
	}

	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}
	@Column(name="EMPLOY_NAME", length=64)
	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) { 
		
		this.employName = employName;
	}
	
	@Column(name="SELECTEDCOURSENO", length=50)
	public String getSelectedCoureNo() {
		return selectedCoureNo;
	}

	public void setSelectedCoureNo(String selectedCoureNo) {
		this.selectedCoureNo = selectedCoureNo;
	}

	@Column(name="COURSETYPE", length=20)
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	@Column(name="CREDIT", length=4)
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
   







}