package com.zhbit.xuexin.student.service;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.StuQuery;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;

/**
 * 学生信息管理接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-1 下午2:38:53
 * @version 1.0
 */
public interface StudentsService {

    /**
     * @Title: getStuToUser
     * @Description: TODO(通过学生编号获取信息封装成user对象。)
     * @author liangriyu
     * @date 2016-3-1 下午3:11:04
     * @return
     * @return User
     */
    User getStuToUser(String stuNo);

    /**
     * @Title: getList
     * @Description: TODO(获取学生用户列表。)
     * @author liangriyu
     * @date 2016-3-3 上午2:00:33
     * @param page
     * @return
     * @return Page<Students>
     */
    Page<Students> getList(Page<Students> page);

    /**
     * @Title: importStudents
     * @Description: TODO(导入学生信息。)
     * @author liangriyu
     * @date 2016-3-6 上午10:35:19
     * @param stuFile
     *            文件对象
     * @param suffix
     *            文件名（去后缀）
     * @param user
     *            当前用户
     * @param suffix
     * @return
     * @return int[]
     */
    int[] importStudents(File stuFile, User user, String suffix);

    /**
     * @Title: getStudent
     * @Description: TODO(获取学生对象。)
     * @author liangriyu
     * @date 2016-3-6 上午10:47:02
     * @return
     * @return Students
     */
    Students getStudent(String stuId);

    /**
     * @Title: getStudent
     * @Description: TODO(通过学号获取学生对象。)
     * @author liangriyu
     * @date 2016-3-6 上午10:47:02
     * @param stuNo
     *            学号
     * @return
     * @return Students
     */
    Students getStudentByNo(String stuNo);

    /**
     * @Title: exportExcelList
     * @Description: TODO(将查询结果导出到Excel表。)
     * @author liangriyu
     * @date 2016-3-8 下午8:49:09
     * @param page
     * @return ExportExcelVO
     */
    ExportExcelVO exportExcelList(Page<Students> page);

    /**
     * @Title: saveStudents
     * @Description: TODO(新增学生信息。)
     * @author liangriyu
     * @date 2016-3-9 下午11:29:05
     * @param studentno
     * @param stuname
     * @param sex
     * @param birthday
     * @param politicalstatus
     * @param nation
     * @param nativeplace
     * @param fromPlace
     * @param idcardno
     * @param orgId
     * @param orgName
     * @param department
     * @param major
     * @param majorfield
     * @param majorcategories
     * @param cultivatedirection
     * @param classname
     * @param educationsystem
     * @param schoolinglength
     * @param acceptancedate
     * @param middleschool
     * @param mobileno
     * @param familytelno
     * @param postcode
     * @param travelrange
     * @param address
     * @param skill
     * @param selfdescription
     * @param awards
     * @param schoolstatus
     * @param dqszj
     * @param photopath
     * @param graduateflag
     * @param alumniflag
     * @param creator
     * @return void
     */
    void saveStudents(String studentno, String stuname, String sex, Date birthday, String politicalstatus,
            String nation, String nativeplace, String fromPlace, String idcardno, String orgId, String orgName,
            String department, String major, String majorfield, String majorcategories, String cultivatedirection,
            String classname, Integer educationsystem, Integer schoolinglength, Date acceptancedate,
            String middleschool, String mobileno, String familytelno, String postcode, String travelrange,
            String address, String skill, String selfdescription, String awards, String schoolstatus, String dqszj,
            String photopath, String graduateflag, String alumniflag, String creator);

    /**
     * @Title: updateStudents
     * @Description: TODO(修改学生信息。)
     * @author 梁日宇
     * @date 2016-3-10 上午1:36:29
     * @param stuId
     * @param studentno
     * @param stuname
     * @param sex
     * @param parse
     * @param politicalstatus
     * @param nation
     * @param nativeplace
     * @param fromPlace
     * @param idcardno
     * @param orgId
     * @param orgName
     * @param department
     * @param major
     * @param majorfield
     * @param majorcategories
     * @param cultivatedirection
     * @param classname
     * @param educationsystem
     * @param schoolinglength
     * @param parse2
     * @param middleschool
     * @param mobileno
     * @param familytelno
     * @param postcode
     * @param travelrange
     * @param address
     * @param skill
     * @param selfdescription
     * @param awards
     * @param schoolstatus
     * @param dqszj
     * @param photopath
     * @param graduateflag
     * @param alumniflag
     * @return void
     */
    void updateStudents(String stuId, String studentno, String stuname, String sex, Date parse, String politicalstatus,
            String nation, String nativeplace, String fromPlace, String idcardno, String orgId, String orgName,
            String department, String major, String majorfield, String majorcategories, String cultivatedirection,
            String classname, Integer educationsystem, Integer schoolinglength, Date parse2, String middleschool,
            String mobileno, String familytelno, String postcode, String travelrange, String address, String skill,
            String selfdescription, String awards, String schoolstatus, String dqszj, String photopath,
            String graduateflag, String alumniflag);

    /**
     * @Title: deleteStudents
     * @Description: TODO(删除学生信息。)
     * @author liangiryu
     * @date 2016-3-10 上午2:02:19
     * @param ids
     * @return void
     */
    void deleteStudents(String ids);

    /**
     * @Title: updatePhoto
     * @Description: TODO(修改头像。)
     * @author LRY
     * @date 2016-4-27 下午3:10:17
     * @param studentno
     * @param photo
     * @param suffix
     * @return void
     * @throws Exception 
     */
    void updatePhoto(String studentno, File photo, String suffix) throws Exception;

    /**
     * 启动初始化
     * 
     * @throws
     */
    void init();

    /**
     * @Title: updatePwd
     * @Description: TODO(修改学生登录密码。)
     * @author LRY
     * @date 2016-5-19 下午5:48:08
     * @param password
     * @param passwordOld
     * @param stuId
     * @return
     * @return int
     */
    int updatePwd(String password, String passwordOld, String stuId);
    /**
     * 
     * @Title: getSelf
     * @Description: 获取学生自己的信息
     * @param page
     * @return
     * @return: Page<Students>
     */
	Page<Students> getSelf(Page<Students> page,Students stu);
	/**
	 * 根据学院id获取学院名称
	 * @Title: getOrgNameByOrgId
	 * @Description: TODO
	 * @param orgId
	 * @return
	 * @return: String
	 */
	String getOrgNameByOrgId(String orgId);
	
	/**
	 * 导出基本信息
	 * @Title: export
	 * @Description: TODO
	 * @param query
	 * @param out
	 * @return: void
	 */
	void export(StuQuery query,OutputStream out);
	public List<Map<String,String>> listOrgName();
	public List<Map<String,String>> listClassname();
	
}
