package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.zhbit.xuexin.domain.NceeScore;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.NceeScoreDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.NceeScoreService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("nceeScoreService")
@Transactional(readOnly = true)
public class NceeScoreServiceImpl implements NceeScoreService {
    private Logger logger = LoggerFactory.getLogger(NceeScoreServiceImpl.class);

    @Autowired
    @Qualifier("nceeScoreDao")
    private NceeScoreDao dao;

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
    public Page<NceeScore> getList(Page<NceeScore> page) {
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
    public int save(NceeScore inf, String userId, double sxScore, double ywScore, double yyScore, double zhScore) {
        if (inf != null) {
            Students stu = studentsDao.getStudentByIdcardNo(inf.getIdcardno());
            if (stu == null) {
                return 0;
            }
            for(int i = 0;i<4;i++){
                NceeScore info = new NceeScore();
                info.setZkhNo(inf.getZkhNo());
                info.setIdcardno(inf.getIdcardno());
                if(i==0){
                    info.setCoursename("数学");
                    info.setScore(sxScore);
                }else if(i==1){
                    info.setCoursename("语文");
                    info.setScore(ywScore);
                }else if(i==2){
                    info.setCoursename("英语");
                    info.setScore(yyScore);
                }else if(i==3){
                    info.setCoursename("综合");
                    info.setScore(zhScore);
                }
                info.setCreateTime(new Date());
                info.setCreator(userId);
                info.setStuId(stu.getStuId());
                info.setStudentno(stu.getStudentno());
                info.setStuname(stu.getStuname());
                System.out.println("~~~~~~~~"+info.getScore());
                dao.save(info);
            }
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
     * @return NceeScore
     */
    @Override
    public NceeScore getNceeScoreByid(String id) {
        return dao.getNceeScoreByid(id);
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
    public void update(NceeScore info, NceeScore oldInfo) {
        if (info != null) {
            oldInfo.setScore(info.getScore());
            String stuname = info.getStuname();
            if(null != stuname && !"".equals(stuname)){
            	oldInfo.setStuname(stuname);
            }
            dao.update(oldInfo);
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
                NceeScore info = dao.getNceeScoreByid(id);
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
                    System.out.println("~~~~~~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(1)));
                    if (!"".equals(ExcelUtil.getCellValue(row.getCell(1)))) {
                        importCount++;
                        //System.out.println("~~~~~~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(1)));
                        Students stu = studentsDao.getStudentByIdcardNo(ExcelUtil.getCellValue(row.getCell(1)));
                        if (stu == null) {// 存在学生信息才导入
                            updateCount++;
                        }
                        else {
                            for(int i=0;i<4;i++){
                                NceeScore info = new NceeScore();
                                info.setZkhNo(ExcelUtil.getCellValue(row.getCell(0)));
                                info.setIdcardno(ExcelUtil.getCellValue(row.getCell(1)));
                                info.setStuname(ExcelUtil.getCellValue(row.getCell(2)));
                                if(i==0){
                                    info.setCoursename("数学");
                                    info.setScore(Double.valueOf(ExcelUtil.getCellValue(row.getCell(3))));
                                }else if(i==1){
                                    info.setCoursename("语文");
                                    info.setScore(Double.valueOf(ExcelUtil.getCellValue(row.getCell(4))));
                                }else if(i==2){
                                    info.setCoursename("英语");
                                    info.setScore(Double.valueOf(ExcelUtil.getCellValue(row.getCell(5))));
                                }else if(i==3){
                                    info.setCoursename("综合");
                                    info.setScore(Double.valueOf(ExcelUtil.getCellValue(row.getCell(6))));
                                }
                                
                                info.setCreateTime(new Date());
                                info.setCreator(user.getUserId());
                                info.setStuId(stu.getStuId());
                                info.setStudentno(stu.getStudentno());
                                dao.save(info);
                            }
                            insertCount++;
                        }
                    }
                }
                catch(Exception e) {
                    msg = (ExcelUtil.getCellValue(row.getCell(4)))
                            + (ExcelUtil.getCellValue(row.getCell(5)));
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
    public ExportExcelVO exportExcelList(Page<NceeScore> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<NceeScore> pageResult = dao.getList(page);
        List<NceeScore> list = pageResult.getResult();
        // 设置表头
        String[] title = { "准考证号", "身份证号", "姓名", "数学", "语文", "英语", "综合", "总分" };
        // 设置文件名
        String filename = "学生高考入学成绩表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            NceeScore info = list.get(i);
            String[] str = new String[8];
            str[0] = info.getZkhNo();
            str[1] = info.getIdcardno();
            str[2]=info.getStuname();
            
            str[7] = String.valueOf(info.getScore());
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

	@Override
	public Page<NceeScore> getSelf(Page<NceeScore> page, Students stu) {
		
		return dao.getSelf(page,stu);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSelf(String ids, Students stu) {
		if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                NceeScore info = dao.getNceeScoreByid(id);
                if(info != null && stu.getStudentno().equals(info.getStudentno())){
                	dao.delete(info);
                }
            }
        }
	}

}
