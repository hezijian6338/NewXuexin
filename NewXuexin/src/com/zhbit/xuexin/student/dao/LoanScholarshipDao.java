package com.zhbit.xuexin.student.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LoanScholarship;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-21 上午12:21:50
 *@version 1.0
 */
public interface LoanScholarshipDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<LoanScholarship>
     */
    Page<LoanScholarship> getList(Page<LoanScholarship> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    /**
     * @Title: getDateExist
     * @Description: TODO(通过学生贷款的具体信息info来获取对象。根据学号、年级、学年、学期判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
	public int getDateExist(LoanScholarship info);

    void save(LoanScholarship info);
    /**
     * @Title: LoanScholarship
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return LoanScholarship
     */
    LoanScholarship getLoanScholarshipByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    void update(LoanScholarship info);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    void delete(LoanScholarship info);

}
