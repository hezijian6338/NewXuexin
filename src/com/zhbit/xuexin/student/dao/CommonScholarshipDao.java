package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.CommonScholarship;

/**
 *普通奖学金持久化接口
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 上午12:30:55
 *@version 1.0
 */
public interface CommonScholarshipDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<CommonScholarship>
     */
    Page<CommonScholarship> getList(Page<CommonScholarship> page);

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-14 上午2:49:53
     * @param cs
     * @return
     */
    void save(CommonScholarship cs);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-14 上午2:50:30
     * @return void
     */
    void update(CommonScholarship cs);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    void delete(CommonScholarship info);


    /**
     * @Title: getCommonScholarshipById
     * @Description: TODO(获取普通奖学金对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return CommonScholarship
     */
    CommonScholarship getCommonScholarshipById(String id);
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年5月3日 上午11:31:04
     *@Description:TODO()
     *@param studentno
     *@param stuname
     *@param major
     *@param rewardname
     *@param academicyear
     *@param term
     *@return
     */
    int getCommonScholarshipExist(String studentno,String stuname,
    		String major, String rewardname, String academicyear,String term);
}
