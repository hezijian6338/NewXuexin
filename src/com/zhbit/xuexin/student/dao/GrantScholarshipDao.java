package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.GrantScholarship;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:39
 *@version 1.0
 */
public interface GrantScholarshipDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<CountryScholarship>
     */
    Page<GrantScholarship> getList(Page<GrantScholarship> page);
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
     * @Description: TODO(通过学生助学金的具体信息info来获取对象。根据学号和年级判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
    public  int getDateExist(GrantScholarship info);
  
    void save(GrantScholarship info);
    /**
     * @Title: GrantScholarship
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return GrantScholarship
     */
    GrantScholarship getGrantScholarshipByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    void update(GrantScholarship info);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    void delete(GrantScholarship info);

}
