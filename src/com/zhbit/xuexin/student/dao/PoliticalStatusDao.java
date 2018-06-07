package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.PoliticalStatus;

/**
 *党团关系持久化接口
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-11 下午1:06:09
 *@version 1.0
 */
public interface PoliticalStatusDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<PoliticalStatus>
     */
    Page<PoliticalStatus> getList(Page<PoliticalStatus> page);

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-14 上午2:49:53
     * @param ps
     * @return
     */
    void save(PoliticalStatus ps);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-14 上午2:50:30
     * @return void
     */
    void update(PoliticalStatus ps);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    void delete(PoliticalStatus info);


    /**
     * @Title: getPoliticalStatusById
     * @Description: TODO(获取党团关系对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return PoliticalStatus
     */
    PoliticalStatus getPoliticalStatusById(String id);
    
    boolean CheckIfPoliticalStatusIsExists(String studentno,String politicalstatus);
   
    boolean CheckPoliticalStatusUpdateLegitimacy(String politicalstatusid,String politicalstatus);
}
