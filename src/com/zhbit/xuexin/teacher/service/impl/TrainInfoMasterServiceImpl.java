package com.zhbit.xuexin.teacher.service.impl;

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
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.DateUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.TrainInfoMaster;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.dao.TrainInfoMasterDao;
import com.zhbit.xuexin.teacher.service.TrainInfoMasterService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("trainInfoMasterService")
@Transactional(readOnly = true)
public class TrainInfoMasterServiceImpl implements TrainInfoMasterService {
    private Logger logger = LoggerFactory.getLogger(TrainInfoMasterServiceImpl.class);

    @Autowired
    @Qualifier("trainInfoMasterDao")
    private TrainInfoMasterDao dao;

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
    public Page<TrainInfoMaster> getList(Page<TrainInfoMaster> page) {
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
    public int save(TrainInfoMaster info, String userId, String trainsDateStr) {
        if (info != null) {
            if (dao.getTrainInfoMasterByCode(info.getTraincode()) == null) {
            info.setTrainsdate(DateUtil.formartStringToShortDate(trainsDateStr));
            info.setCreateTime(new Date());
            info.setCreator(userId);
            dao.save(info);
            return 1;
            }else{
            	return 0;
            }
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
     * @return TrainInfoMaster
     */
    @Override
    public TrainInfoMaster getTrainInfoMasterByid(String id) {
        return dao.getTrainInfoMasterByid(id);
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
    public void update(TrainInfoMaster info, TrainInfoMaster oldInfo, String trainsDateStr) {
        if (info != null) {
            info.setTrainsdate(DateUtil.formartStringToShortDate(trainsDateStr));
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
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
                TrainInfoMaster info = dao.getTrainInfoMasterByid(id);
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
                    if (!ExcelUtil.getCellValue(row.getCell(0)).equals("")) {
                        importCount++;
                        if (dao.getTrainInfoMasterByCode(ExcelUtil.getCellValue(row.getCell(0))) == null) {
                            TrainInfoMaster info = new TrainInfoMaster();
                            info.setTraincode(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setTrainsdate(DateUtil.formartStringToShortDate(ExcelUtil.getCellValue(row.getCell(1))));
                            info.setTrainsgrade(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setManager(ExcelUtil.getCellValue(row.getCell(3)));
                            info.setTrainstopic(ExcelUtil.getCellValue(row.getCell(4)));
                            info.setTrainscontent(ExcelUtil.getCellValue(row.getCell(5)));
                            info.setTrainsaddress(ExcelUtil.getCellValue(row.getCell(6)));
                            info.setMemo(ExcelUtil.getCellValue(row.getCell(7)));

                            info.setCreateTime(new Date());
                            info.setCreator(user.getUserId());
                            dao.save(info);
                            insertCount++;
                        }
                        else {
                            updateCount++;
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
    public ExportExcelVO exportExcelList(Page<TrainInfoMaster> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<TrainInfoMaster> pageResult = dao.getList(page);
        List<TrainInfoMaster> list = pageResult.getResult();
        // 设置表头
        String[] title = { "培训编码", "培训日期", "培训对象年级", "负责人", "培训主题", "培训内容", "培训地点", "备注" };
        // 设置文件名
        String filename = "学生培训情况主表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            TrainInfoMaster info = list.get(i);
            String[] str = new String[8];
            str[0] = ExcelUtil.setValue(info.getTraincode());
            str[1] = ExcelUtil.setDateValue(info.getTrainsdate(), "yyyy-MM-dd");
            str[2] = ExcelUtil.setValue(info.getTrainsgrade());
            str[3] = ExcelUtil.setValue(info.getManager());
            str[4] = ExcelUtil.setValue(info.getTrainstopic());
            str[5] = ExcelUtil.setValue(info.getTrainscontent());
            str[6] = ExcelUtil.setValue(info.getTrainsaddress());
            str[7] = ExcelUtil.setValue(info.getMemo());

            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

}
