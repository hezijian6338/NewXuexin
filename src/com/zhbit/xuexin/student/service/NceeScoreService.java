package com.zhbit.xuexin.student.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.NceeScore;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:33:18
 *@version 1.0
 */
public interface NceeScoreService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<NceeScore> getList(Page<NceeScore> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @param zhScore 
     * @param yyScore 
     * @param ywScore 
     * @param sxScore 
     * @return
     * @return int
     */
    int save(NceeScore info, String userId, double sxScore, double ywScore, double yyScore, double zhScore);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return NceeScore
     */
    NceeScore getNceeScoreByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo 
     * @return void
     */
    void update(NceeScore info, NceeScore oldInfo);
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
    ExportExcelVO exportExcelList(Page<NceeScore> page);
    /**
     * 
     * @Title: getSelf
     * @Description: 获取自己信息
     * @param page
     * @param session
     * @return
     * @return: Page<NceeScore>
     */
	Page<NceeScore> getSelf(Page<NceeScore> page,Students stu);
	/**
	 * 删除自己的信息
	 * @Title: deleteSelf
	 * @Description: TODO
	 * @param ids
	 * @param stu
	 * @return: void
	 */
	void deleteSelf(String ids, Students stu);

    

}
