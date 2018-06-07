package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LoanScholarship;
import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-21 上午12:22:26
 * @version 1.0
 */
public interface LoanScholarshipService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<LoanScholarship> getList(Page<LoanScholarship> page);

    /**
     * @Title: getDateExist
     * @Description: TODO(通过学生贷款的具体信息info来获取对象。根据学号和年级判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
    public  int getDateExist(LoanScholarship info);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @param modifydateStr 
     * @param repaydateStr 
     * @return
     * @return int
     */
    int save(LoanScholarship info, String userId, String repaydateStr, String modifydateStr);

    /**
     * @Title: getCountryScholarshipByid
     * @Description: TODO(获取对象。)
     * @author lry
     * @date 2016-3-19 下午10:01:29
     * @param csId
     * @return
     * @return CountryScholarship
     */
    LoanScholarship getLoanScholarshipByid(String id);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @param modifydateStr 
     * @param repaydateStr 
     * @return void
     */
    int update(LoanScholarship info, LoanScholarship oldInfo, String repaydateStr, String modifydateStr);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-15 下午10:46:26
     * @param ids
     * @return void
     */
    void delete(String ids);

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-15 下午10:47:48
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
    ExportExcelVO exportExcelList(Page<LoanScholarship> page);

}
