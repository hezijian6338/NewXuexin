package com.zhbit.xuexin.student.service;

import java.io.File;
import java.text.ParseException;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.GuidanceInfo;
import com.zhbit.xuexin.domain.User;

/**
 * 辅导记录业务接口上
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 下午2:41:51
 * @version 1.0
 */
public interface GuidanceInfoService {

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
     * @Description: TODO(保存辅导信息。)
     * @author liangriyu
     * @date 2016-3-12 下午6:44:01
     * @param studentno
     * @param stuname
     * @param classname
     * @param guiddate
     * @param guidcontent
     * @param guidaddress
     * @param counselor
     * @param docpath
     * @param mediapath
     * @param userId
     * @return void
     */
    void save(String studentno, String stuname, String classname, String guiddate, String guidcontent,
            String guidaddress, String counselor, String docpath, String mediapath, String userId) throws ParseException;

    /**
     * @Title: update
     * @Description: TODO(修改辅导信息。)
     * @author liangiryu
     * @date 2016-3-12 下午6:44:42
     * @param id
     * @param studentno
     * @param stuname
     * @param classname
     * @param guiddate
     * @param guidcontent
     * @param guidaddress
     * @param counselor
     * @param docpath
     * @param mediapath
     * @param userId
     * @return void
     */
    void update(String id, String studentno, String stuname, String classname, String guiddate, String guidcontent,
            String guidaddress, String counselor, String docpath, String mediapath, String userId) throws ParseException;

    /**
     * @Title: delete
     * @Description: TODO(删除辅导信息。)
     * @author liangriyu
     * @date 2016-3-12 下午6:45:32
     * @param ids
     * @return void
     */
    void delete(String ids);

    /**
     * @Title: getGuidanceInfoByid
     * @Description: TODO(通过id获取辅导信息。)
     * @author liangriyu
     * @date 2016-3-12 下午6:47:18
     * @param id
     * @return
     * @return GuidanceInfo
     */
    GuidanceInfo getGuidanceInfoByid(String id);

    /**
     * @Title: save
     * @Description: TODO(保存新增。)
     * @author lry
     * @date 2016-3-13 下午3:32:52
     * @param info
     * @param guiddate
     * @param userId
     * @return 0-保存失败学号不存在 1-保存成功
     */
    int save(GuidanceInfo info, String guiddate, String userId) throws ParseException;

    /**
     * @Title: update
     * @Description: TODO(修改辅导信息。)
     * @author lry
     * @date 2016-3-13 下午8:21:17
     * @param info
     * @param guiddate
     * @param userId
     * @return void
     */
    void update(GuidanceInfo info, String guiddate, String userId) throws ParseException;

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-14 上午12:59:25
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
    ExportExcelVO exportExcelList(Page<GuidanceInfo> page);

     
}
