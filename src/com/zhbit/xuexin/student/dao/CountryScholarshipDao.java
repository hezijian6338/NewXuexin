package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.CountryScholarship;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-19 下午9:35:01
 * @version 1.0
 */
public interface CountryScholarshipDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<CountryScholarship>
     */
    Page<CountryScholarship> getList(Page<CountryScholarship> page);

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    void save(CountryScholarship info);

    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    void update(CountryScholarship info);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    void delete(CountryScholarship info);

    /**
     * @Title: getCountryScholarshipByid
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return CountryScholarship
     */
    CountryScholarship getCountryScholarshipByid(String id);
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年5月8日 下午5:19:49
     *@Description:TODO()
     *@param studentno
     *@param academicyear
     *@param term
     *@return
     */
	public int getCountryScholarshipExist(String studentno, String academicyear, String term);
}
