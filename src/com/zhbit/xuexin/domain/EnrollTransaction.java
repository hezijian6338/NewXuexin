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
 * EnrollTransaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ENROLLTRANSACTION")
public class EnrollTransaction implements java.io.Serializable {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -2151197564763223571L;

    // Fields

    private String id;

    private String transactionno;

    private String stuId;

    private String studentno;

    private String stuname;

    private String sex;

    private String academicyear;

    private String term;

    private String tansactionclass;

    private String processsymbols;

    private String tansactiontype;

    private String tansactionreason;

    private Date tansactiondate;

    private Date handledate;

    private Date canceldate;

    private String tansactionmemo;

    private String zxqschool;

    private String zxqgrade;

    private String zxqmajor;

    private String ydqcollege;

    private String ydqdepartment;

    private String ydqmajor;

    private String ydqlength;

    private String ydqmajorfield;

    private String ydqcultivatedirection;

    private String ydqgrade;

    private String ydqclassname;

    private String ydqschoolstatus;

    private String zchschool;

    private String zchgrade;

    private String zchmajor;

    private String ydhcollege;

    private String ydhdepartment;

    private String ydhmajor;

    private String ydhlength;

    private String ydhmajorfield;

    private String ydhcultivatedirection;

    private String ydhgrade;

    private String ydhclassname;

    private String ydhschoolstatus;

    private String operator;

    private String operatortime;

    private String ydqinschool;

    private String ydhinschool;

    private String ydqmajorcode;

    private String ydhmajorcode;

    private String ydqisregiste;

    private String ydhisregiste;

    private String memo;

    private String ydqeducation;

    private String ydheducation;

    private String ydqmajorcategory;

    private String ydhmajorcategory;

    private String ydresult;

    private String studentcategory;

    private String examinateno;

    private String idcardno;

    private Date createTime;

    private String creator;

    private String parentOrgId;

    // Constructors

    /** default constructor */
    public EnrollTransaction() {
    }

    /** minimal constructor */
    public EnrollTransaction(String stuId) {
        this.stuId = stuId;
    }

    /** full constructor */
    public EnrollTransaction(String transactionno, String stuId, String studentno, String stuname, String sex,
            String academicyear, String term, String tansactionclass, String processsymbols, String tansactiontype,
            String tansactionreason, Date tansactiondate, Date handledate, Date canceldate, String tansactionmemo,
            String zxqschool, String zxqgrade, String zxqmajor, String ydqcollege, String ydqdepartment,
            String ydqmajor, String ydqlength, String ydqmajorfield, String ydqcultivatedirection, String ydqgrade,
            String ydqclassname, String ydqschoolstatus, String zchschool, String zchgrade, String zchmajor,
            String ydhcollege, String ydhdepartment, String ydhmajor, String ydhlength, String ydhmajorfield,
            String ydhcultivatedirection, String ydhgrade, String ydhclassname, String ydhschoolstatus,
            String operator, String operatortime, String ydqinschool, String ydhinschool, String ydqmajorcode,
            String ydhmajorcode, String ydqisregiste, String ydhisregiste, String memo, String ydqeducation,
            String ydheducation, String ydqmajorcategory, String ydhmajorcategory, String ydresult,
            String studentcategory, String examinateno, String idcardno, Date createTime, String creator,
            String parentOrgId) {
        this.transactionno = transactionno;
        this.stuId = stuId;
        this.studentno = studentno;
        this.stuname = stuname;
        this.sex = sex;
        this.academicyear = academicyear;
        this.term = term;
        this.tansactionclass = tansactionclass;
        this.processsymbols = processsymbols;
        this.tansactiontype = tansactiontype;
        this.tansactionreason = tansactionreason;
        this.tansactiondate = tansactiondate;
        this.handledate = handledate;
        this.canceldate = canceldate;
        this.tansactionmemo = tansactionmemo;
        this.zxqschool = zxqschool;
        this.zxqgrade = zxqgrade;
        this.zxqmajor = zxqmajor;
        this.ydqcollege = ydqcollege;
        this.ydqdepartment = ydqdepartment;
        this.ydqmajor = ydqmajor;
        this.ydqlength = ydqlength;
        this.ydqmajorfield = ydqmajorfield;
        this.ydqcultivatedirection = ydqcultivatedirection;
        this.ydqgrade = ydqgrade;
        this.ydqclassname = ydqclassname;
        this.ydqschoolstatus = ydqschoolstatus;
        this.zchschool = zchschool;
        this.zchgrade = zchgrade;
        this.zchmajor = zchmajor;
        this.ydhcollege = ydhcollege;
        this.ydhdepartment = ydhdepartment;
        this.ydhmajor = ydhmajor;
        this.ydhlength = ydhlength;
        this.ydhmajorfield = ydhmajorfield;
        this.ydhcultivatedirection = ydhcultivatedirection;
        this.ydhgrade = ydhgrade;
        this.ydhclassname = ydhclassname;
        this.ydhschoolstatus = ydhschoolstatus;
        this.operator = operator;
        this.operatortime = operatortime;
        this.ydqinschool = ydqinschool;
        this.ydhinschool = ydhinschool;
        this.ydqmajorcode = ydqmajorcode;
        this.ydhmajorcode = ydhmajorcode;
        this.ydqisregiste = ydqisregiste;
        this.ydhisregiste = ydhisregiste;
        this.memo = memo;
        this.ydqeducation = ydqeducation;
        this.ydheducation = ydheducation;
        this.ydqmajorcategory = ydqmajorcategory;
        this.ydhmajorcategory = ydhmajorcategory;
        this.ydresult = ydresult;
        this.studentcategory = studentcategory;
        this.examinateno = examinateno;
        this.idcardno = idcardno;
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

    @Column(name = "TRANSACTIONNO", length = 32)
    public String getTransactionno() {
        return this.transactionno;
    }

    public void setTransactionno(String transactionno) {
        this.transactionno = transactionno;
    }

    @Column(name = "STU_ID", nullable = false, length = 32)
    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "STUDENTNO", length = 16)
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

    @Column(name = "ACADEMICYEAR", length = 20)
    public String getAcademicyear() {
        return this.academicyear;
    }

    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
    }

    @Column(name = "TERM", length = 20)
    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name = "TANSACTIONCLASS", length = 50)
    public String getTansactionclass() {
        return this.tansactionclass;
    }

    public void setTansactionclass(String tansactionclass) {
        this.tansactionclass = tansactionclass;
    }

    @Column(name = "PROCESSSYMBOLS", length = 20)
    public String getProcesssymbols() {
        return this.processsymbols;
    }

    public void setProcesssymbols(String processsymbols) {
        this.processsymbols = processsymbols;
    }

    @Column(name = "TANSACTIONTYPE", length = 20)
    public String getTansactiontype() {
        return this.tansactiontype;
    }

    public void setTansactiontype(String tansactiontype) {
        this.tansactiontype = tansactiontype;
    }

    @Column(name = "TANSACTIONREASON", length = 50)
    public String getTansactionreason() {
        return this.tansactionreason;
    }

    public void setTansactionreason(String tansactionreason) {
        this.tansactionreason = tansactionreason;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TANSACTIONDATE", length = 7)
    public Date getTansactiondate() {
        return this.tansactiondate;
    }

    public void setTansactiondate(Date tansactiondate) {
        this.tansactiondate = tansactiondate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "HANDLEDATE", length = 7)
    public Date getHandledate() {
        return this.handledate;
    }

    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CANCELDATE", length = 7)
    public Date getCanceldate() {
        return this.canceldate;
    }

    public void setCanceldate(Date canceldate) {
        this.canceldate = canceldate;
    }

    @Column(name = "TANSACTIONMEMO", length = 100)
    public String getTansactionmemo() {
        return this.tansactionmemo;
    }

    public void setTansactionmemo(String tansactionmemo) {
        this.tansactionmemo = tansactionmemo;
    }

    @Column(name = "ZXQSCHOOL", length = 100)
    public String getZxqschool() {
        return this.zxqschool;
    }

    public void setZxqschool(String zxqschool) {
        this.zxqschool = zxqschool;
    }

    @Column(name = "ZXQGRADE", length = 20)
    public String getZxqgrade() {
        return this.zxqgrade;
    }

    public void setZxqgrade(String zxqgrade) {
        this.zxqgrade = zxqgrade;
    }

    @Column(name = "ZXQMAJOR", length = 50)
    public String getZxqmajor() {
        return this.zxqmajor;
    }

    public void setZxqmajor(String zxqmajor) {
        this.zxqmajor = zxqmajor;
    }

    @Column(name = "YDQCOLLEGE", length = 50)
    public String getYdqcollege() {
        return this.ydqcollege;
    }

    public void setYdqcollege(String ydqcollege) {
        this.ydqcollege = ydqcollege;
    }

    @Column(name = "YDQDEPARTMENT", length = 50)
    public String getYdqdepartment() {
        return this.ydqdepartment;
    }

    public void setYdqdepartment(String ydqdepartment) {
        this.ydqdepartment = ydqdepartment;
    }

    @Column(name = "YDQMAJOR", length = 50)
    public String getYdqmajor() {
        return this.ydqmajor;
    }

    public void setYdqmajor(String ydqmajor) {
        this.ydqmajor = ydqmajor;
    }

    @Column(name = "YDQLENGTH", length = 10)
    public String getYdqlength() {
        return this.ydqlength;
    }

    public void setYdqlength(String ydqlength) {
        this.ydqlength = ydqlength;
    }

    @Column(name = "YDQMAJORFIELD", length = 100)
    public String getYdqmajorfield() {
        return this.ydqmajorfield;
    }

    public void setYdqmajorfield(String ydqmajorfield) {
        this.ydqmajorfield = ydqmajorfield;
    }

    @Column(name = "YDQCULTIVATEDIRECTION", length = 100)
    public String getYdqcultivatedirection() {
        return this.ydqcultivatedirection;
    }

    public void setYdqcultivatedirection(String ydqcultivatedirection) {
        this.ydqcultivatedirection = ydqcultivatedirection;
    }

    @Column(name = "YDQGRADE", length = 10)
    public String getYdqgrade() {
        return this.ydqgrade;
    }

    public void setYdqgrade(String ydqgrade) {
        this.ydqgrade = ydqgrade;
    }

    @Column(name = "YDQCLASSNAME", length = 100)
    public String getYdqclassname() {
        return this.ydqclassname;
    }

    public void setYdqclassname(String ydqclassname) {
        this.ydqclassname = ydqclassname;
    }

    @Column(name = "YDQSCHOOLSTATUS", length = 20)
    public String getYdqschoolstatus() {
        return this.ydqschoolstatus;
    }

    public void setYdqschoolstatus(String ydqschoolstatus) {
        this.ydqschoolstatus = ydqschoolstatus;
    }

    @Column(name = "ZCHSCHOOL", length = 100)
    public String getZchschool() {
        return this.zchschool;
    }

    public void setZchschool(String zchschool) {
        this.zchschool = zchschool;
    }

    @Column(name = "ZCHGRADE", length = 20)
    public String getZchgrade() {
        return this.zchgrade;
    }

    public void setZchgrade(String zchgrade) {
        this.zchgrade = zchgrade;
    }

    @Column(name = "ZCHMAJOR", length = 50)
    public String getZchmajor() {
        return this.zchmajor;
    }

    public void setZchmajor(String zchmajor) {
        this.zchmajor = zchmajor;
    }

    @Column(name = "YDHCOLLEGE", length = 50)
    public String getYdhcollege() {
        return this.ydhcollege;
    }

    public void setYdhcollege(String ydhcollege) {
        this.ydhcollege = ydhcollege;
    }

    @Column(name = "YDHDEPARTMENT", length = 50)
    public String getYdhdepartment() {
        return this.ydhdepartment;
    }

    public void setYdhdepartment(String ydhdepartment) {
        this.ydhdepartment = ydhdepartment;
    }

    @Column(name = "YDHMAJOR", length = 50)
    public String getYdhmajor() {
        return this.ydhmajor;
    }

    public void setYdhmajor(String ydhmajor) {
        this.ydhmajor = ydhmajor;
    }

    @Column(name = "YDHLENGTH", length = 10)
    public String getYdhlength() {
        return this.ydhlength;
    }

    public void setYdhlength(String ydhlength) {
        this.ydhlength = ydhlength;
    }

    @Column(name = "YDHMAJORFIELD", length = 100)
    public String getYdhmajorfield() {
        return this.ydhmajorfield;
    }

    public void setYdhmajorfield(String ydhmajorfield) {
        this.ydhmajorfield = ydhmajorfield;
    }

    @Column(name = "YDHCULTIVATEDIRECTION", length = 100)
    public String getYdhcultivatedirection() {
        return this.ydhcultivatedirection;
    }

    public void setYdhcultivatedirection(String ydhcultivatedirection) {
        this.ydhcultivatedirection = ydhcultivatedirection;
    }

    @Column(name = "YDHGRADE", length = 10)
    public String getYdhgrade() {
        return this.ydhgrade;
    }

    public void setYdhgrade(String ydhgrade) {
        this.ydhgrade = ydhgrade;
    }

    @Column(name = "YDHCLASSNAME", length = 100)
    public String getYdhclassname() {
        return this.ydhclassname;
    }

    public void setYdhclassname(String ydhclassname) {
        this.ydhclassname = ydhclassname;
    }

    @Column(name = "YDHSCHOOLSTATUS", length = 20)
    public String getYdhschoolstatus() {
        return this.ydhschoolstatus;
    }

    public void setYdhschoolstatus(String ydhschoolstatus) {
        this.ydhschoolstatus = ydhschoolstatus;
    }

    @Column(name = "OPERATOR", length = 20)
    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "OPERATORTIME", length = 20)
    public String getOperatortime() {
        return this.operatortime;
    }

    public void setOperatortime(String operatortime) {
        this.operatortime = operatortime;
    }

    @Column(name = "YDQINSCHOOL", length = 2)
    public String getYdqinschool() {
        return this.ydqinschool;
    }

    public void setYdqinschool(String ydqinschool) {
        this.ydqinschool = ydqinschool;
    }

    @Column(name = "YDHINSCHOOL", length = 2)
    public String getYdhinschool() {
        return this.ydhinschool;
    }

    public void setYdhinschool(String ydhinschool) {
        this.ydhinschool = ydhinschool;
    }

    @Column(name = "YDQMAJORCODE", length = 10)
    public String getYdqmajorcode() {
        return this.ydqmajorcode;
    }

    public void setYdqmajorcode(String ydqmajorcode) {
        this.ydqmajorcode = ydqmajorcode;
    }

    @Column(name = "YDHMAJORCODE", length = 10)
    public String getYdhmajorcode() {
        return this.ydhmajorcode;
    }

    public void setYdhmajorcode(String ydhmajorcode) {
        this.ydhmajorcode = ydhmajorcode;
    }

    @Column(name = "YDQISREGISTE", length = 2)
    public String getYdqisregiste() {
        return this.ydqisregiste;
    }

    public void setYdqisregiste(String ydqisregiste) {
        this.ydqisregiste = ydqisregiste;
    }

    @Column(name = "YDHISREGISTE", length = 2)
    public String getYdhisregiste() {
        return this.ydhisregiste;
    }

    public void setYdhisregiste(String ydhisregiste) {
        this.ydhisregiste = ydhisregiste;
    }

    @Column(name = "MEMO", length = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "YDQEDUCATION", length = 20)
    public String getYdqeducation() {
        return this.ydqeducation;
    }

    public void setYdqeducation(String ydqeducation) {
        this.ydqeducation = ydqeducation;
    }

    @Column(name = "YDHEDUCATION", length = 20)
    public String getYdheducation() {
        return this.ydheducation;
    }

    public void setYdheducation(String ydheducation) {
        this.ydheducation = ydheducation;
    }

    @Column(name = "YDQMAJORCATEGORY", length = 20)
    public String getYdqmajorcategory() {
        return this.ydqmajorcategory;
    }

    public void setYdqmajorcategory(String ydqmajorcategory) {
        this.ydqmajorcategory = ydqmajorcategory;
    }

    @Column(name = "YDHMAJORCATEGORY", length = 20)
    public String getYdhmajorcategory() {
        return this.ydhmajorcategory;
    }

    public void setYdhmajorcategory(String ydhmajorcategory) {
        this.ydhmajorcategory = ydhmajorcategory;
    }

    @Column(name = "YDRESULT", length = 20)
    public String getYdresult() {
        return this.ydresult;
    }

    public void setYdresult(String ydresult) {
        this.ydresult = ydresult;
    }

    @Column(name = "STUDENTCATEGORY", length = 20)
    public String getStudentcategory() {
        return this.studentcategory;
    }

    public void setStudentcategory(String studentcategory) {
        this.studentcategory = studentcategory;
    }

    @Column(name = "EXAMINATENO", length = 20)
    public String getExaminateno() {
        return this.examinateno;
    }

    public void setExaminateno(String examinateno) {
        this.examinateno = examinateno;
    }

    @Column(name = "IDCARDNO", length = 18)
    public String getIdcardno() {
        return this.idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
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