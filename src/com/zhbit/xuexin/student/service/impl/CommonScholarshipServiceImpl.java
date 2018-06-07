package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.CommonScholarship;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.CommonScholarshipDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.CommonScholarshipService;

/**
 *普通奖学金业务层
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 上午12:33:52
 *@version 1.0
 */
@Service("commonScholarshipService")
@Transactional(readOnly = true)
public class CommonScholarshipServiceImpl implements CommonScholarshipService {

    private Logger logger = LoggerFactory.getLogger(PoliticalStatusServiceImpl.class);
    @Autowired
    @Qualifier("commonScholarshipDao")
    private CommonScholarshipDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<PoliticalStatus>
     */
    @Override
    public Page<CommonScholarship> getList(Page<CommonScholarship> page) {
        return dao.getList(page);
    }

    /**
     * @Title: save
     * @Description: TODO(保存普通奖学金信息。)
     * @author lry
     * @date 2016-3-15 上午12:52:54
     * @param studentno
     * @param stuname
     * @param major
     * @param rewardname
     * @param academicyear
     * @param term
     * @param memo
     * @param userId
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(String studentno, String stuname, String major, String rewardname, String academicyear,
            String term, String memo, String userId) {
        Students stu = studentsDao.getStudentByNo(studentno);
        //许彩开-2017.04.18-验证输入的姓名和学号对应的姓名是否一致
        if(!stu.getStuname().equals(stuname)){
        	return 0;
        }
        CommonScholarship cs = new CommonScholarship();
        if (stu != null) {
            cs.setStuId(stu.getStuId());
            cs.setStudentno(studentno);
            cs.setStuname(stuname);
            cs.setMajor(major);
            cs.setRewardname(rewardname);
            cs.setAcademicyear(academicyear);
            cs.setTerm(term);
            cs.setMemo(memo);
            cs.setCreator(userId);
            cs.setCreateTime(new Date());
            dao.save(cs);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Title: update
     * @Description: TODO(修改普通奖学金信息。)
     * @author lry
     * @date 2016-3-15 上午12:53:40
     * @param id
     * @param studentno
     * @param stuname
     * @param major
     * @param rewardname
     * @param academicyear
     * @param term
     * @param memo
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void update(String id, String studentno, String stuname, String major, String rewardname,
            String academicyear, String term, String memo) {
        CommonScholarship cs = dao.getCommonScholarshipById(id);
        cs.setStuname(stuname);
        cs.setMajor(major);
        cs.setRewardname(rewardname);
        cs.setAcademicyear(academicyear);
        cs.setTerm(term);
        cs.setMemo(memo);
        dao.update(cs);
    }

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String ids) {
        if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                CommonScholarship info = dao.getCommonScholarshipById(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: getCommonScholarshipById
     * @Description: TODO(获取普通奖学金对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return CommonScholarship
     */
    @Override
    public CommonScholarship getCommonScholarshipById(String id) {
        return dao.getCommonScholarshipById(id);
    }

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-14 上午12:59:25
     * @param excel
     * @param user
     * @return
     * @return int[]
     */
    @Override
    @Transactional(readOnly = false)
    public int[] importFile(File excel, User user) {
        Workbook wb = null;
        FileInputStream in = null;
        int importCount = 0;// 成功导入的总条数
        int insertCount = 0;// 导入新增的总条数、
        int updateCount = 0;// 导入更新的总条数
        int existCount=0;//导入文件中的数据跟数据库已存在的相同记录的重复数
        int dataNullCount=0;//导入文件中某些字段是否含有空值
        try {
            in = new FileInputStream(excel);
            wb = new HSSFWorkbook(in);
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
                    
                  //2017/04/13 许彩开解决导入普通奖学金导入模板时字段值含有空格问题
					String[] infoExcle = { row.getCell(0).getStringCellValue(),
							row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(),
							row.getCell(3).getStringCellValue(),
							row.getCell(4).getStringCellValue(),
							row.getCell(5).getStringCellValue() };
                    for(int i=0;i<=5;i++){
						infoExcle[i]=infoExcle[i].replaceAll(" ","");
						if(infoExcle[i].equals("")){
							dataNullCount=1;
						}
                    }
                    
                    if (!"".equals(ExcelUtil.getCellValue(row.getCell(0)))) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(ExcelUtil.getCellValue(row.getCell(0)));
                        if (stu == null) {//存在学生信息才导入
                            updateCount++;
                        }
                        else {
                        	
                        	int existResult = getCommonScholarshipExist(
									infoExcle[0], infoExcle[1], infoExcle[2],
									infoExcle[3],infoExcle[4], infoExcle[5]);
                        	
                        	
                            CommonScholarship info = new CommonScholarship();
                            if(existResult==0&&dataNullCount==0){
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
                            info.setMajor(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setRewardname(ExcelUtil.getCellValue(row.getCell(3)));
                            if (row.getCell(4)!=null)
                                info.setAcademicyear(ExcelUtil.getCellValue(row.getCell(4)));
                            if (row.getCell(5)!=null)
                                info.setTerm(ExcelUtil.getCellValue(row.getCell(5)));
                            if (row.getCell(6)!=null)
                                info.setMemo(ExcelUtil.getCellValue(row.getCell(6)));
                            info.setCreateTime(new Date());
                            info.setStuId(stu.getStuId());
                            dao.save(info);
                            insertCount++;
                            }
                            else{
                            	existCount++;
                            }
                        }
                    }
                }
                catch(Exception e) {
                    msg = (ExcelUtil.getCellValue(row.getCell(0)))
                            + (ExcelUtil.getCellValue(row.getCell(1)));
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, updateCount ,dataNullCount,existCount};
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
    public ExportExcelVO exportExcelList(Page<CommonScholarship> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<CommonScholarship> pageResult = dao.getList(page);
        List<CommonScholarship> list = pageResult.getResult();
        // 设置表头
        String[] title = { "学号", "姓名", "专业", "获奖名称","学年","学期", "备注"};
        // 设置文件名
        String filename = "普通奖学金表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            String[] str = new String[7];
            str[0] = list.get(i).getStudentno();
            str[1] = list.get(i).getStuname();
            str[2] = list.get(i).getMajor()==null?"":list.get(i).getMajor();
            str[3] = list.get(i).getRewardname()==null?"":list.get(i).getRewardname();
            str[4] = list.get(i).getAcademicyear()==null?"":list.get(i).getAcademicyear();
            str[5] = list.get(i).getTerm()==null?"":list.get(i).getTerm();
            str[6] = list.get(i).getMemo()==null?"":list.get(i).getMemo();
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
 *@date 创建时间：2017年5月3日 上午11:38:05
 *@Description:TODO()
 *@param studentno
 *@param stuname
 *@param major
 *@param rewardname
 *@param academicyear
 *@param term
 *@return
 */
	@Override
	public int getCommonScholarshipExist(String studentno, String stuname, String major, String rewardname,
			String academicyear, String term) {
		// TODO Auto-generated method stub
		return dao.getCommonScholarshipExist(studentno, stuname, major, rewardname, academicyear, term);
	}
    
}
