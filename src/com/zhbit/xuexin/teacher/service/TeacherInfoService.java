package com.zhbit.xuexin.teacher.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:54:34
 * @version 1.0
 */
public interface TeacherInfoService {

    /**
     * @Title: getList
     * @Description: TODO(分页获取教师基本信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<TeacherInfo> getList(Page<TeacherInfo> page);

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param birth
     *            生日
     * @param userId
     *            创建者
     * @return
     * @return int
     */
    int save(TeacherInfo info, String birth, String userId);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     *            教师对象
     * @param oldInfo
     * @param birth
     *            生日
     * @return void
     */
    void update(TeacherInfo info, TeacherInfo oldInfo, String birth);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-15 下午10:46:26
     * @param ids
     * @return void
     */
    void delete(String ids);

    /**
     * @Title: getTeacherInfoByid
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-15 下午10:46:55
     * @param id
     * @return
     * @return TeacherInfo
     */
    TeacherInfo getTeacherInfoByid(String id);

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-15 下午10:47:48
     * @param excel
     * @param user
     * @param suffix
     * @return
     * @return int[]
     */
    int[] importFile(File excel, User user, String suffix);

    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    ExportExcelVO exportExcelList(Page<TeacherInfo> page);

    /**
     * @Title: updatePhoto
     * @Description: TODO(更新头像。)
     * @author LRY
     * @date 2016-4-26 下午11:52:27
     * @param employNo
     * @param photo
     *            图片文件
     * @param suffix
     *            文件后缀
     * @return void
     */
    void updatePhoto(String employNo, File photo, String suffix) throws Exception;

    /**
     * @Title: getTeacherInfoByNo
     * @Description: TODO(通过教工号获取教师信息。)
     * @author LRY
     * @date 2016-4-27 上午12:51:40
     * @param employNo
     * @return
     * @return TeacherInfo
     */
    TeacherInfo getTeacherInfoByNo(String employNo);

    /**
     * 启动初始化
     * 
     * @throws
     */
    void init();

    /**
     * @Title: getTeacherToUser
     * @Description: TODO(通过教工号获取信息组装成User对象。)
     * @author LRY
     * @date 2016-4-28 下午8:43:35
     * @param employNo
     * @return
     * @return User
     */
    User getTeacherToUser(String employNo);

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
    int updatePwd(String password, String passwordOld, String id);

}
