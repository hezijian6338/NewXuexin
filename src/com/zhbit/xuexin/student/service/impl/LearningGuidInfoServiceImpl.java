package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LearningGuidInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.LearningGuidInfoDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.LearningGuidInfoService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-18 上午2:21:07
 * @version 1.0
 */
@Service("learningGuidInfoService")
@Transactional(readOnly = true)
public class LearningGuidInfoServiceImpl implements LearningGuidInfoService {
    
    private Logger logger = LoggerFactory.getLogger(LearningGuidInfoServiceImpl.class);

    @Autowired
    @Qualifier("learningGuidInfoDao")
    private LearningGuidInfoDao dao;

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
    public Page<LearningGuidInfo> getList(Page<LearningGuidInfo> page) {
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
     * @param reportdateStr
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(LearningGuidInfo info, String userId, String guiddateStr) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNo(info.getStudentno());
            //xucaikai 2017.04.16
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           
            if (stu == null) {
                return 0;
            }
            
            //许彩开-2017.03.28-验证输入的姓名和学号对应的姓名是否一致
            if(!stu.getStuname().equals(info.getStuname())){
            	return 0;
            }
            //许彩开-2017.04.16-验证输入的导学日期和原有的导学日期是否一致
            String guiddateStrTemp=guiddateStr.substring(0,4)+guiddateStr.substring(5,7)+guiddateStr.substring(8,10);
            int existResult=getLearningGuidInfo_exist(info.getStudentno(),guiddateStrTemp);
            if(existResult==1){//该学生已经存在该日期的导学
            	return 0;
            }
          
            try {
                info.setGuiddate(df.parse(guiddateStr));
            }
            catch(ParseException e) {
                e.printStackTrace();
            }
           
            info.setCreateTime(new Date());
            info.setCreator(userId);
            info.setStuId(stu.getStuId());
            dao.save(info);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @param reportdateStr
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public int update(LearningGuidInfo info, LearningGuidInfo oldInfo, String guiddateStr) {
    	   
   	 Students stu = studentsDao.getStudentByNo(info.getStudentno());
   	 
   	 //许彩开-2017.03.28-验证输入的姓名和学号对应的姓名是否一致
        if(!stu.getStuname().equals(oldInfo.getStuname())){
        		return 0;
        }else{ 

           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           info.setCreateTime(oldInfo.getCreateTime());
           info.setCreator(oldInfo.getCreator());
           info.setStuId(oldInfo.getStuId());
           info.setStuname(oldInfo.getStuname());
           try {
               info.setGuiddate(df.parse(guiddateStr));
           }
           catch(ParseException e) {
               e.printStackTrace();
           }
           dao.update(info);
           return 1;
         }
    }

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return LearningGuidInfo
     */
    @Override
    public LearningGuidInfo getLearningGuidInfoByid(String id) {
        return dao.getLearningGuidInfoByid(id);
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
                LearningGuidInfo info = dao.getLearningGuidInfoByid(id);
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
                    if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue())) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(row.getCell(0).getStringCellValue());
                        if (stu == null) {// 存在学生信息才导入
                            updateCount++;
                        }
                        else {
                        	/**
                        	 * 许彩开 2017.04.09
                        	 */
                        	int existResult=getLearningGuidInfo_exist(row.getCell(0).getStringCellValue(), row.getCell(2).getStringCellValue());
                            LearningGuidInfo info = new LearningGuidInfo();
                            if(existResult==0){
	                            info.setStudentno(row.getCell(0).getStringCellValue());
	                            info.setStuname(row.getCell(1).getStringCellValue());
	                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	                            if (row.getCell(2) != null && !row.getCell(2).getStringCellValue().equals("")) {
	                                info.setGuiddate(df.parse(row.getCell(2).getStringCellValue()));
	                            }
	                            info.setGuidaddress(row.getCell(3) == null?"":row.getCell(3).getStringCellValue());
	                            info.setGuidcontent(row.getCell(4) == null?"":row.getCell(4).getStringCellValue());
	                            info.setDocpath(row.getCell(5) == null?"":row.getCell(5).getStringCellValue());
	                            if (row.getCell(6) != null && !row.getCell(6).getStringCellValue().equals("")) {
	                                info.setMemo(row.getCell(6).getStringCellValue());
	                            }
	                            info.setCreateTime(new Date());
	                            info.setStuId(stu.getStuId());
	                            dao.save(info);
	                            insertCount++;
                            }else{
                            	updateCount++;
                            }
                        }
                    }
                }
                catch(Exception e) {
                    msg = (row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "")
                            + (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, updateCount };
    }

    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    @Override
    public ExportExcelVO exportExcelList(Page<LearningGuidInfo> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<LearningGuidInfo> pageResult = dao.getList(page);
        List<LearningGuidInfo> list = pageResult.getResult();
        // 设置表头
        String[] title = { "学号", "姓名", "导学时间", "导学地点", "导学内容", "电子文档", "备注" };
        // 设置文件名
        String filename = "导学内容表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < list.size(); i++) {
            LearningGuidInfo info = list.get(i);
            String[] str = new String[7];
            str[0] = info.getStudentno();
            str[1] = info.getStuname();
            str[2]=info.getGuiddate()==null?"":df.format(info.getGuiddate());
            str[3]=info.getGuidaddress()==null?"":info.getGuidaddress();
            str[4]=info.getGuidcontent()==null?"":info.getGuidcontent();
            str[5]=info.getDocpath()==null?"":info.getDocpath();
            str[6]=info.getMemo()==null?"":info.getMemo();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }
/**
 * 
 *@author 许彩开 email:1121836563@qq.com
 *@date 创建时间：2017年4月9日 下午8:56:04
 *@Description:TODO()
 *@param studentno
 *@param guiddate
 *@return
 */
	@Override
	public int getLearningGuidInfo_exist(String studentno, String guiddate) {
		// TODO Auto-generated method stub
		return dao.getLearningGuidInfo_exist(studentno, guiddate);
	}

}
