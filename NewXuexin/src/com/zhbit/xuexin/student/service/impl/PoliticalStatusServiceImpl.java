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
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.PoliticalStatus;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.PoliticalStatusDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.PoliticalStatusService;

/**
 * 党团关系业务层
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 下午1:09:18
 * @version 1.0
 */
@Service("politicalStatusService")
@Transactional(readOnly = true)
public class PoliticalStatusServiceImpl implements PoliticalStatusService {
    private Logger logger = LoggerFactory.getLogger(PoliticalStatusServiceImpl.class);
    @Autowired
    @Qualifier("politicalStatusDao")
    private PoliticalStatusDao dao;

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
    public Page<PoliticalStatus> getList(Page<PoliticalStatus> page) {
        return dao.getList(page);
    }
     
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
     * @throws ParseException 
     */
    @Override
    @Transactional(readOnly = false)
    public int save(String studentno, String stuname, String joindate, String politicalstatus, String memo,
            String userId) throws ParseException {
        Students stu = studentsDao.getStudentByNo(studentno);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PoliticalStatus ps = new PoliticalStatus();
        if (stu != null&&stuname.equals(stu.getStuname())&&
        		dao.CheckIfPoliticalStatusIsExists(studentno, politicalstatus)) {
            ps.setStuId(stu.getStuId());
            ps.setStudentno(studentno);
            ps.setStuname(stuname);
            ps.setJoindate(df.parse(joindate));
            ps.setPoliticalstatus(politicalstatus);
            ps.setMemo(memo);
            ps.setCreator(userId);
            ps.setCreateTime(new Date());
            dao.save(ps);
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
     * @date 2016-3-14 上午2:50:30
     * @param id
     * @param studentno
     * @param stuname
     * @param joindate
     * @param politicalstatus
     * @param memo
     * @return void
     * @throws ParseException 
     */
    @Override
    @Transactional(readOnly = false)
	public int update(String id, String studentno, String stuname, String joindate, String politicalstatus, String memo)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (dao.CheckPoliticalStatusUpdateLegitimacy(id, politicalstatus)) {
			PoliticalStatus ps = dao.getPoliticalStatusById(id);
			ps.setStuname(stuname);
			ps.setJoindate(df.parse(joindate));
			ps.setPoliticalstatus(politicalstatus);
			ps.setMemo(memo);
			dao.update(ps);
			return 1;
		}
		else
			return 0;
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
                PoliticalStatus info = dao.getPoliticalStatusById(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: getPoliticalStatusById
     * @Description: TODO(获取党团关系对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return PoliticalStatus
     */
    @Override
    public PoliticalStatus getPoliticalStatusById(String id) {
        return dao.getPoliticalStatusById(id);
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
    public int[] importFile(File excel, User user, String suffix) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
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
                    if (!ExcelUtil.getCellValue(row.getCell(0)).equals("")) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(ExcelUtil.getCellValue(row.getCell(0)));
                        if (stu == null) {//存在学生信息才导入
                            updateCount++;
                        }
                        else {
                            PoliticalStatus info = new PoliticalStatus();
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
                            if (!"".equals(ExcelUtil.getCellValue(row.getCell(2)))){
                                info.setJoindate((df.parse(ExcelUtil.getCellValue(row.getCell(2)))));
                            }
                            if (row.getCell(3)!=null)
                            info.setPoliticalstatus(ExcelUtil.getCellValue(row.getCell(3)));
                            if (row.getCell(4)!=null)
                                info.setMemo(ExcelUtil.getCellValue(row.getCell(4)));
                            
                            info.setStuId(stu.getStuId());
                            info.setCreateTime(new Date());
                            info.setCreator(user.getUserId());
                            dao.save(info);
                            insertCount++;
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
    public ExportExcelVO exportExcelList(Page<PoliticalStatus> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<PoliticalStatus> pageResult = dao.getList(page);
        List<PoliticalStatus> list = pageResult.getResult();
        // 设置表头
        String[] title = { "学号", "姓名", "入党团日期", "政治面貌", "备注"};
        // 设置文件名
        String filename = "党团关系信息表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < list.size(); i++) {
            String[] str = new String[5];
            str[0] = list.get(i).getStudentno();
            str[1] = list.get(i).getStuname();
            if(list.get(i).getJoindate()!=null)
            str[2] = df.format(list.get(i).getJoindate());
            str[3] = list.get(i).getPoliticalstatus();
            str[4] = list.get(i).getMemo();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }
}
