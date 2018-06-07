package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.SubjectContest;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:39
 *@version 1.0
 */
public interface SubjectContestDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<CountryScholarship>
     */
    Page<SubjectContest> getList(Page<SubjectContest> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    void save(SubjectContest info);
    /**
     * @Title: SubjectContest
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return SubjectContest
     */
    SubjectContest getSubjectContestByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    void update(SubjectContest info);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    void delete(SubjectContest info);
    
    boolean CheckSubjectContestIfExsits(SubjectContest info);

}
