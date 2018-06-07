package com.zhbit.xuexin.student.service;

import java.io.File;
import java.text.ParseException;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.PoliticalStatus;
import com.zhbit.xuexin.domain.User;

/**
 * 党团关系业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 下午1:08:46
 * @version 1.0
 */
public interface PoliticalStatusService {

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
     * @param studentno
     * @param stuname
     * @param joindate
     * @param politicalstatus
     * @param memo
     * @param userId
     * @return
     * @return int
     */
    int save(String studentno, String stuname, String joindate, String politicalstatus, String memo, String userId)throws ParseException ;

    /**
    * @Title: update 
    * @Description: TODO(修改信息。) 
    * @author lry 
    * @date 2016-3-14 上午2:50:30 
    * @param id
    * @param studentno
    * @param stuname
    * @param joindate
    * @param politicalstatus
    * @param memo 
    * @return void
     */
    int update(String id, String studentno, String stuname, String joindate, String politicalstatus, String memo) throws ParseException ;

    /**
    * @Title: delete 
    * @Description: TODO(删除。) 
    * @author lry 
    * @date 2016-3-14 上午2:50:56 
    * @param ids 
    * @return void
     */
    void delete(String ids);

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
    ExportExcelVO exportExcelList(Page<PoliticalStatus> page);

}
