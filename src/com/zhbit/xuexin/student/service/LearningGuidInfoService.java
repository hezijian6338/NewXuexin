package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LearningGuidInfo;
import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-18 上午2:20:37
 * @version 1.0
 */
public interface LearningGuidInfoService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<LearningGuidInfo> getList(Page<LearningGuidInfo> page);

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @param reportdateStr
     * @return
     * @return int
     */
    int save(LearningGuidInfo info, String userId, String guiddateStr);
    
    
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年4月9日 下午8:49:07
     *@Description:TODO(通过id获取对象。通过学生学号student,guiddate来获取对象。判断数据库中是否存在记录)
     *@param studentno
     *@param guiddate
     *@return
     */
    int getLearningGuidInfo_exist(String studentno,String guiddate);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @param reportdateStr
     * @return void
     */
    int update(LearningGuidInfo info, LearningGuidInfo oldInfo, String guiddateStr);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return GrantScholarship
     */
    LearningGuidInfo getLearningGuidInfoByid(String id);

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
    ExportExcelVO exportExcelList(Page<LearningGuidInfo> page);

}
