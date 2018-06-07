package com.zhbit.xuexin.teacher.vo;

import java.util.ArrayList;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-4-24 下午11:30:25
 *@version 1.0
 */
public class AttendanceDetailVO {
    
    private String studentno;

    private String stuname;

    private String sex;

    private String classname;

    private ArrayList<String> attendanceStatus;

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public ArrayList<String> getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(ArrayList<String> attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
    
    
    
}
