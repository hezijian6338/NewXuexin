package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.CountryScholarship;
import com.zhbit.xuexin.domain.User;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-19 下午9:35:45
 *@version 1.0
 */
public interface CountryScholarshipService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<CountryScholarship> getList(Page<CountryScholarship> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @return
     * @return int
     */
    int save(CountryScholarship info, String userId);
    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo 
     * @return void
     */
    void update(CountryScholarship info, CountryScholarship oldInfo);
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
    * @Title: getCountryScholarshipByid 
    * @Description: TODO(获取对象。) 
    * @author lry 
    * @date 2016-3-19 下午10:01:29 
    * @param csId
    * @return 
    * @return CountryScholarship
     */
    CountryScholarship getCountryScholarshipByid(String csId);
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
    ExportExcelVO exportExcelList(Page<CountryScholarship> page);
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年5月8日 下午5:17:00
     *@Description:TODO()
     *@param studentno
     *@param academicyear
     *@param term
     *@return
     */
    int getCountryScholarshipExist(String studentno, String academicyear,String term);
}
