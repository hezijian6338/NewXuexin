package com.zhbit.xuexin.student.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningGuidInfo;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-18 上午2:19:34
 *@version 1.0
 */
public interface LearningGuidInfoDao {

    Page<LearningGuidInfo> getList(Page<LearningGuidInfo> page);

    void save(LearningGuidInfo info);

    void update(LearningGuidInfo info);

    LearningGuidInfo getLearningGuidInfoByid(String id);

    void delete(LearningGuidInfo info);
    
    
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
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年4月10日 下午10:38:06
     *@Description:TODO()
     *@return
     */
    public List<LearningGuidStudentsList> getStuNoList();

}
