package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.CommonScholarship;
import com.zhbit.xuexin.domain.User;

/**
 * 普通奖学金业务层接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 上午12:33:00
 * @version 1.0
 */
public interface CommonScholarshipService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<PoliticalStatus>
     */
    Page<CommonScholarship> getList(Page<CommonScholarship> page);

    /**
     * @Title: save
     * @Description: TODO(保存普通奖学金信息。)
     * @author lry
     * @date 2016-3-15 上午12:52:54
     * @param studentno
     * @param stuname
     * @param major
     * @param rewardname
     * @param academicyear
     * @param term
     * @param memo
     * @param userId
     * @return
     * @return int
     */
    int save(String studentno, String stuname, String major, String rewardname, String academicyear, String term,
            String memo, String userId);

    /**
     * @Title: update
     * @Description: TODO(修改普通奖学金信息。)
     * @author lry
     * @date 2016-3-15 上午12:53:40
     * @param id
     * @param studentno
     * @param stuname
     * @param major
     * @param rewardname
     * @param academicyear
     * @param term
     * @param memo
     * @return void
     */
    void update(String id, String studentno, String stuname, String major, String rewardname, String academicyear,
            String term, String memo);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    void delete(String ids);

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
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-14 上午12:59:25
     * @param excel
     * @param user
     * @return
     * @return int[]
     */
    int[] importFile(File excel, User user);

    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    ExportExcelVO exportExcelList(Page<CommonScholarship> page);
    
    
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
