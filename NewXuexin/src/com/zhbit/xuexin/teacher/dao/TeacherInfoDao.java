package com.zhbit.xuexin.teacher.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeacherInfo;

/**
 * 教师基本信息持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface TeacherInfoDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取教师信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<TeacherInfo> getList(Page<TeacherInfo> page);

    /**
     * @Title: save
     * @Description: TODO(保存教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(TeacherInfo info);

    /**
     * @Title: update
     * @Description: TODO(修改教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(TeacherInfo info);

    /**
     * @Title: delete
     * @Description: TODO(删除教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(TeacherInfo info);

    /**
     * @Title: getTeacherInfoByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return TeacherInfo
     */
    TeacherInfo getTeacherInfoByid(String id);

    /**
     * @Title: getTeacherInfoByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return TeacherInfo
     */
    TeacherInfo getTeacherInfoByNo(String employNo);

    /**
     * @Title: getTeacherInfoByName
     * @Description: TODO(通过职工姓名获取信息。)
     * @author lry
     * @date 2016-3-17 下午11:10:29
     * @param employName
     * @return
     * @return TeacherInfo
     */
    TeacherInfo getTeacherInfoByName(String employName);

    /**
     * @Title: queryPhotoPath
     * @Description: TODO(获取头像路径)
     * @author LRY
     * @date 2016-4-27 上午1:21:54
     * @param sql
     * @return
     * @return List<String>
     */
    List<String> queryPhotoPath(String sql);

}
