package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.TrainInfoDetail;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.teacher.dao.TrainInfoDetailDao;
import com.zhbit.xuexin.teacher.dao.TrainInfoMasterDao;
import com.zhbit.xuexin.teacher.service.TrainInfoDetailService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("trainInfoDetailService")
@Transactional(readOnly = true)
public class TrainInfoDetailServiceImpl implements TrainInfoDetailService {
    private Logger logger = LoggerFactory.getLogger(TrainInfoDetailServiceImpl.class);

    @Autowired
    @Qualifier("TrainInfoDetailDao")
    private TrainInfoDetailDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

    @Autowired
    @Qualifier("trainInfoMasterDao")
    private TrainInfoMasterDao masterDao;

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
    public Page<TrainInfoDetail> getList(Page<TrainInfoDetail> page) {
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
    public int save(TrainInfoDetail info, String userId) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNoAndName(info.getStudentno(),info.getStuname());
            if (stu ==null || masterDao.getTrainInfoMasterByCode(info.getTrainCode()) == null) {
                return 0;
            }
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
     * @return TrainInfoDetail
     */
    @Override
    public TrainInfoDetail getTrainInfoDetailByid(String id) {
        return dao.getTrainInfoDetailByid(id);
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
    public void update(TrainInfoDetail info) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNo(info.getStudentno());
            info.setStuId(stu.getStuId());
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
                TrainInfoDetail info = dao.getTrainInfoDetailByid(id);
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
                    TrainInfoDetail trainInfoDetail = new TrainInfoDetail();
                    if (!ExcelUtil.getCellValue(row.getCell(0)).equals("")
                            && !ExcelUtil.getCellValue(row.getCell(1)).equals("")) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(row.getCell(1).getStringCellValue());
                        if (stu == null
                                || masterDao.getTrainInfoMasterByCode(ExcelUtil.getCellValue(row.getCell(0))) == null||dao.getTrainInfoDetailByCodeAndName(
                                		ExcelUtil.getCellValue(row.getCell(0)), ExcelUtil.getCellValue(row.getCell(1)))!=null) {// 存在学生信息才导入
                            updateCount++;
                        }
                        else {
                            TrainInfoDetail info = new TrainInfoDetail();
                            info.setTrainCode(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(1)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setTrainsresult(ExcelUtil.getCellValue(row.getCell(3)));
                            info.setMemo(ExcelUtil.getCellValue(row.getCell(4)));

                            info.setStuId(stu.getStuId());
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
    public ExportExcelVO exportExcelList(Page<TrainInfoDetail> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<TrainInfoDetail> pageResult = dao.getList(page);
        List<TrainInfoDetail> list = pageResult.getResult();
        // 设置表头
        String[] title = { "培训编码", "学号", "姓名", "培训结果", "备注" };
        // 设置文件名
        String filename = "学生培训情况主表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            TrainInfoDetail info = list.get(i);
            String[] str = new String[5];
            str[0] = ExcelUtil.setValue(info.getTrainCode());
            str[1] = ExcelUtil.setValue(info.getStudentno());
            str[2] = ExcelUtil.setValue(info.getStuname());
            str[3] = ExcelUtil.setValue(info.getTrainsresult());
            str[4] = ExcelUtil.setValue(info.getMemo());

            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

}
