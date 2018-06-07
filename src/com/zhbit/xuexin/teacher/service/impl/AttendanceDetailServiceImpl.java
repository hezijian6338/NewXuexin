package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.DateUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.AttendanceDetail;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.teacher.dao.AttendanceDetailDao;
import com.zhbit.xuexin.teacher.service.AttendanceDetailService;
import com.zhbit.xuexin.teacher.vo.AttendanceDetailVO;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("attendanceDetailService")
@Transactional(readOnly = true)
public class AttendanceDetailServiceImpl implements AttendanceDetailService {
    private Logger logger = LoggerFactory.getLogger(AttendanceDetailServiceImpl.class);

    @Autowired
    @Qualifier("attendanceDetailDao")
    private AttendanceDetailDao dao;
    //xucaikai2017.04.18
    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    @Override
    public Page<AttendanceDetail> getList(Page<AttendanceDetail> page) {
        return dao.getList(page);
    }

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(AttendanceDetail info, String userId, String attendanceTimeStr) {
        if (info != null) {
        	//xucaikai2017.04.18
        	
            Students stu = studentsDao.getStudentByNo(info.getStudentno());
            //许彩开-2017.03.28-验证输入的姓名和学号对应的姓名是否一致
            if(!stu.getStuname().equals(info.getStuname())){
            	return 0;
            }
            
            int existResult=getSelectedcoursenol_exist(info.getSelectedcourseno());
            System.out.println("选课号======发斯蒂芬是否===="+info.getSelectedcourseno()+"===大是大非===existResult==="+existResult);
            if(existResult<=0){//不存在该选课号
            	return 0;
            }
          
            info.setAttendanceTime(DateUtil.formartStringToShortDate(attendanceTimeStr));
            info.setCreateTime(new Date());
            info.setCreator(userId);
          //xucaikai2017.04.18
            info.setStuId(stu.getStuId());
            
            dao.save(info);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return AttendanceDetail
     */
    @Override
    public AttendanceDetail getAttendanceDetailByid(String id) {
        return dao.getAttendanceDetailByid(id);
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void update(AttendanceDetail info, AttendanceDetail oldInfo, String attendanceTimeStr) {
        if (info != null) {
        	//xucaikai2017.04.18
        	Students stu = studentsDao.getStudentByNo(info.getStudentno());
            info.setAttendanceTime(DateUtil.formartStringToShortDate(attendanceTimeStr));
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
          //xucaikai2017.04.18
            info.setStuId(oldInfo.getStuId());
            dao.update(info);
        }
    }

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-15 下午10:46:26
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String ids) {
        if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                AttendanceDetail info = dao.getAttendanceDetailByid(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-15 下午10:47:48
     * @param excel
     * @param user
     * @return
     * @return int[]
     */
    @Override
    @Transactional(readOnly = false)
    public int[] importFile(File excel, User user, String suffix) {
        Workbook wb = null;
        FileInputStream in = null;
        int importCount = 0;// 成功导入的总条数
        int insertCount = 0;// 导入新增的总条数、
        int updateCount = 0;// 导入更新的总条数
        try {
            in = new FileInputStream(excel);
            if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
                wb = WorkbookFactory.create(in);
            }
            else {
                wb = new HSSFWorkbook(in);// 2007以下（2003）
            }
        }
        catch(Exception e) {
            logger.error("读取导入的文件出异常", e);
            return new int[] { -1, insertCount, updateCount };
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                    logger.error("读取导入的文件时关闭输入流异常", e);
                }
            }
        }
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            Row row = null;
            rowIterator.next();// 第一行标题不读
            String msg = null;
            while (rowIterator.hasNext()) {
                try {
                    row = rowIterator.next();
                    importCount++;
                }
                catch(Exception e) {
                    msg = (ExcelUtil.getCellValue(row.getCell(0))) + (ExcelUtil.getCellValue(row.getCell(1)));
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, updateCount };
    }

    /**
     * @Title: getDetailList 
     * @Description: TODO(通过选课号获取该课程考勤。) 
     * @author LRY 
     * @date 2016-4-24 下午11:59:13 
     * @param selectedcourseno
     * @return 
     * @return List<AttendanceDetailVO>
      */
    @Override
    public List<AttendanceDetailVO> getDetailList(String selectedcourseno) {
        Map<String,AttendanceDetailVO> map = new HashMap<String, AttendanceDetailVO>();
        List<String> times = dao.getAttendanceTimes(selectedcourseno);
        for(int i = 0;i<times.size();i++){
            List<AttendanceDetail> tmp = dao.getDetailList(selectedcourseno, times.get(i));
            for(AttendanceDetail detail:tmp){
                if(map.containsKey(detail.getStudentno())){
                    map.get(detail.getStudentno()).getAttendanceStatus().add(i,detail.getAttendanceStatus()==null?"":detail.getAttendanceStatus());
                }else{
                    AttendanceDetailVO vo = new AttendanceDetailVO();
                    vo.setStudentno(detail.getStudentno());
                    vo.setStuname(detail.getStuname());
                    vo.setSex(detail.getSex());
                    vo.setClassname(detail.getClassname());
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(i,detail.getAttendanceStatus()==null?"":detail.getAttendanceStatus());
                    vo.setAttendanceStatus(list);
                    map.put(detail.getStudentno(), vo);
                }
            }
        }
        return new ArrayList<AttendanceDetailVO>(map.values());
    }

    @Override
    public List<String> getAttendanceTimes(String selectedcourseno) {
        return dao.getAttendanceTimes(selectedcourseno);
    }

	@Override
	public int getSelectedcoursenol_exist(String selectedcoursenol) {
		// TODO Auto-generated method stub
		return dao.getSelectedcoursenol_exist(selectedcoursenol);
	}
}
