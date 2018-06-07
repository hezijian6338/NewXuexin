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
import org.apache.poi.ss.usermodel.Cell;
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
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.SubjectContest;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.dao.SubjectContestDao;
import com.zhbit.xuexin.student.service.SubjectContestService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("subjectContestService")
@Transactional(readOnly = true)
public class SubjectContestServiceImpl implements SubjectContestService {
	private Logger logger = LoggerFactory.getLogger(SubjectContestServiceImpl.class);

	@Autowired
	@Qualifier("subjectContestDao")
	private SubjectContestDao dao;

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
	public Page<SubjectContest> getList(Page<SubjectContest> page) {
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
	public int save(SubjectContest info, String userId, String dateStr) {
		if (info != null) {
			Students stu = studentsDao.getStudentByNo(info.getStudentno());
			if (stu == null ||!stu.getStuname().equals(info.getStuname())) {
				return 0;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				System.out.println("================" + dateStr);
				info.setRewarddate(df.parse(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			info.setCreateTime(new Date());
			info.setCreator(userId);
			info.setStuId(stu.getStuId());
			dao.save(info);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @Description: TODO(通过id获取对象实体。)
	 * @author lry
	 * @date 2016-3-20 下午9:55:52
	 * @param id
	 * @return
	 * @return SubjectContest
	 */
	@Override
	public SubjectContest getSubjectContestByid(String id) {
		return dao.getSubjectContestByid(id);
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
	public void update(SubjectContest info, SubjectContest oldInfo, String dateStr) {
		if (info != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			info.setStuId(oldInfo.getStuId());
			try {
				info.setRewarddate(df.parse(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
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
				SubjectContest info = dao.getSubjectContestByid(id);
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
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}

		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] { -1, insertCount, updateCount };
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
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
			int columnNumber = 11;
			
			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					//设置所有单元格的读取格式为文本格式读取
					for(int i=0;i<columnNumber;i++){
						if(row.getCell(i)!=null)
							row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
					}
					if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue())) {
						importCount++;
						Students stu = studentsDao.getStudentByNo(row.getCell(0).getStringCellValue());
						if (stu == null||!stu.getStuname().equals(row.getCell(1).getStringCellValue())) {// 存在学生信息才导入
							updateCount++;
						} else {
							SubjectContest info = new SubjectContest();
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
							info.setStudentno(row.getCell(0).getStringCellValue());
							info.setStuname(row.getCell(1).getStringCellValue());
							info.setRewardname(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());
							info.setRewardlevel(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
							info.setRewardgrade(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
							info.setGrantunits(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());
							info.setRewardproject(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());
							info.setGuidteacher(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());
							info.setRewarddate(
									row.getCell(8) == null ? null : df.parse(row.getCell(8).getStringCellValue()));
							info.setAcademicyear(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());
							info.setTerm(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());
							info.setMemo(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());
							info.setCreateTime(new Date());
							info.setStuId(stu.getStuId());
							if(dao.CheckSubjectContestIfExsits(info)){
								dao.save(info);
								insertCount++;
							}
							else
								updateCount++;
						}
					}
				} catch (Exception e) {
					msg = "";
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
	public ExportExcelVO exportExcelList(Page<SubjectContest> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<SubjectContest> pageResult = dao.getList(page);
		List<SubjectContest> list = pageResult.getResult();
		// 设置表头
		String[] title = { "学号", "获奖者姓名", "获奖名称", "获奖级别", "获奖等级", "授予单位", "获奖项目", "指导老师", "获奖时间", "学年", "学期", "备注" };
		// 设置文件名
		String filename = "学科竞赛表";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			SubjectContest info = list.get(i);
			String[] str = new String[12];
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			str[0] = info.getStudentno();
			str[1] = info.getStuname();
			str[2] = info.getRewardname() == null ? "" : info.getRewardname();
			str[3] = info.getRewardlevel() == null ? "" : info.getRewardlevel();
			str[4] = info.getRewardgrade() == null ? "" : info.getRewardgrade();
			str[5] = info.getGrantunits() == null ? "" : info.getGrantunits();
			str[6] = info.getRewardproject() == null ? "" : info.getRewardproject();
			str[7] = info.getGuidteacher() == null ? "" : info.getGuidteacher();
			str[8] = info.getRewarddate() == null ? "" : df.format(info.getRewarddate());
			str[9] = info.getAcademicyear() == null ? "" : info.getAcademicyear();
			str[10] = info.getTerm() == null ? "" : info.getTerm();
			str[11] = info.getMemo() == null ? "" : info.getMemo();
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

	/**
	 * @Title: removeFile
	 * @Description: TODO(源文件名称。)
	 * @author lry
	 * @date 2016-4-10 上午1:26:06
	 * @param sourceFile
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void removeFile(String sourceFile) {
		if (sourceFile != null && !"".equals(sourceFile)) {
			String[] files = sourceFile.split(",");
			String diskPath = Const.student_contest;
			for (String file : files) {
				// 删除物理路径对应的文件
				File delFile = new File(new File(diskPath), file);
				if (delFile.exists()) {
					delFile.delete();
				}
			}
		}

	}

	/**
	 * @Title: uploadAttachment
	 * @Description: TODO(上传附件。)
	 * @author 梁日宇
	 * @date 2016-4-11 下午8:36:00
	 * @param sid
	 * @param suffix
	 * @return void
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void uploadFile(String sid, File attachment, String suffix) throws Exception {
		SubjectContest info = dao.getSubjectContestByid(sid);
		String file = info.getStudentno() + "_" + suffix;
		removeFile(info.getDocpath());
		String savePath = Const.student_contest + file;
		// 保存到物理路径
		FileOP.writeFile(savePath, attachment);
		info.setDocpath(file);
		dao.update(info);
	}

}
