package com.zhbit.xuexin.student.dao;

import java.io.Serializable;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.GuidanceInfo;

/**
 * 辅导记录持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 下午2:37:25
 * @version 1.0
 */
public interface GuidanceInfoDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取辅导信息列表。)
     * @author liangriyu
     * @date 2016-3-12 下午6:43:15
     * @param page
     * @return
     * @return Page<GuidanceInfo>
     */
    Page<GuidanceInfo> getList(Page<GuidanceInfo> page);

    /**
     * @Title: save
     * @Description: TODO(保存辅导信息对象。)
     * @author lry
     * @date 2016-3-13 下午2:58:39
     * @param info
     * @return
     * @return Serializable
     */
    Serializable save(GuidanceInfo info);

    /**
     * @Title: update
     * @Description: TODO(修改辅导信息对象。)
     * @author lry
     * @date 2016-3-13 下午2:58:39
     * @param info
     * @return
     * @return void
     */
    void update(GuidanceInfo info);

    /**
     * @Title: getGuidanceInfoByid
     * @Description: TODO(获取辅导记录对象信息。)
     * @author lry
     * @date 2016-3-13 下午8:30:25
     * @param id
     * @return
     * @return GuidanceInfo
     */
    GuidanceInfo getGuidanceInfoByid(String id);

    /**
    * @Title: delete 
    * @Description: TODO(删除辅导记录信息。) 
    * @author lry 
    * @date 2016-3-13 下午8:38:11 
    * @param info 
    * @return void
     */
    void delete(GuidanceInfo info);

    /**
     * @Title: 判断导学信息是否存在
     * @Description: TODO(删除辅导记录信息。) 
     * @author lry 
     * @date 2016-3-13 下午8:38:11 
     * @param info 
     * @return void
      */
    boolean CheckGuidanceInfoIfExist(GuidanceInfo info);
}
