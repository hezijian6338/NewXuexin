package com.zhbit.xuexin.student.dao;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.NceeScore;
import com.zhbit.xuexin.domain.Students;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:39
 *@version 1.0
 */
public interface NceeScoreDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<CountryScholarship>
     */
    Page<NceeScore> getList(Page<NceeScore> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    void save(NceeScore info);
    /**
     * @Title: NceeScore
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return NceeScore
     */
    NceeScore getNceeScoreByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    void update(NceeScore info);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    void delete(NceeScore info);
    /**
     * 
     * @Title: getSelf
     * @Description: 获取自己信息
     * @param page
     * @param session
     * @return
     * @return: Page<NceeScore>
     */
	Page<NceeScore> getSelf(Page<NceeScore> page, Students stu);

}
