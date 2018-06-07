package com.zhbit.xuexin.teacher.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TrainInfoDetail;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface TrainInfoDetailDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<TrainInfoDetail>
     */
    Page<TrainInfoDetail> getList(Page<TrainInfoDetail> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(TrainInfoDetail info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(TrainInfoDetail info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(TrainInfoDetail info);

    /**
     * @Title: getTrainInfoDetailByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return TrainInfoDetail
     */
    TrainInfoDetail getTrainInfoDetailByid(String id);
    
	TrainInfoDetail getTrainInfoDetailByCodeAndName(String trainCode, String studentno);

    
}
