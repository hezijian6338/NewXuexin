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
import com.zhbit.xuexin.domain.PostInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.PostInfoDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.PostInfoService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("postInfoService")
@Transactional(readOnly = true)
public class PostInfoServiceImpl implements PostInfoService {
	private Logger logger = LoggerFactory.getLogger(PostInfoServiceImpl.class);

	@Autowired
	@Qualifier("postInfoDao")
	private PostInfoDao dao;

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
	public Page<PostInfo> getList(Page<PostInfo> page) {
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
	public int save(PostInfo info, String userId, Page<PostInfo> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<PostInfo> pageResult = dao.getList(page);
		List<PostInfo> list = pageResult.getResult();
		if (info != null) {
			Students stu = studentsDao.getStudentByNoAndName(info.getStudentno(), info.getStuname());
			if (stu == null) {
				return 0;
			} else {
				for (int i = 0; i < list.size(); i++) {
					PostInfo info_old = list.get(i);
					if (!info_old.getStuname().equals(info.getStuname())) {
						return 0;
					}
				}
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
	 * @return PostInfo
	 */
	@Override
	public PostInfo getPostInfoByid(String id) {
		return dao.getPostInfoByid(id);
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
	public void update(PostInfo info, PostInfo oldInfo) {
		if (info != null) {
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
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
				PostInfo info = dao.getPostInfoByid(id);
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
	public int[] importFile(File excel, User user, String suffix,
			Page<PostInfo> page) {
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

			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					if (!"".equals(ExcelUtil.getCellValue(row.getCell(4)))) {
						importCount++;
						Students stu = studentsDao.getStudentByNo(ExcelUtil
								.getCellValue(row.getCell(4))); // 获取当前表的学生学号信息
						if (stu == null) {// 存在学生信息才导入
							updateCount++;
						} else {
							page.setPage(1);
							page.setRows(100000);
							// 获取查询结果数据集
							Page<PostInfo> pageResult = dao.getList(page);
							List<PostInfo> list = pageResult.getResult();
							for (int i = 0; i < list.size(); i++) {
								PostInfo info_old = list.get(i);
								if (info_old.getStuname().equals(
										ExcelUtil.getCellValue(row.getCell(3)))
										&& info_old.getStudentno().equals(
												ExcelUtil.getCellValue(row
														.getCell(4)))) {
									dao.delete(info_old);
								}
							}

							PostInfo info = new PostInfo();
							info.setEmsno(ExcelUtil.getCellValue(row.getCell(0)));
							info.setSchoolno(ExcelUtil.getCellValue(row
									.getCell(1)));
							info.setMajor(ExcelUtil.getCellValue(row.getCell(2)));
							info.setStuname(ExcelUtil.getCellValue(row
									.getCell(3)));
							info.setStudentno(ExcelUtil.getCellValue(row
									.getCell(4)));
							info.setSex(ExcelUtil.getCellValue(row.getCell(5)) == "男" ? "0"
									: "1");
							info.setDispatchtype(ExcelUtil.getCellValue(row
									.getCell(6)));
							info.setChargeunit(ExcelUtil.getCellValue(row
									.getCell(7)));
							info.setMailno(ExcelUtil.getCellValue(row
									.getCell(8)));
							info.setMemo(ExcelUtil.getCellValue(row.getCell(9)));
							info.setCreateTime(new Date());
							info.setStuId(stu.getStuId());
							dao.save(info);
							insertCount++;

						}
					}
				} catch (Exception e) {
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
	public ExportExcelVO exportExcelList(Page<PostInfo> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<PostInfo> pageResult = dao.getList(page);
		List<PostInfo> list = pageResult.getResult();
		// 设置表头
		String[] title = { "EMS号", "北理工编号", "专业名称", "姓名", "学号", "性别", "派遣性质",
				"主管单位", "邮件号*", "备注" };
		// 设置文件名
		String filename = "学生档案邮寄信息表";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			PostInfo info = list.get(i);
			String[] str = new String[10];
			str[0] = info.getEmsno();
			str[1] = info.getSchoolno();
			str[2] = info.getMajor() == null ? "" : info.getMajor();
			str[3] = info.getStuname();
			str[4] = info.getStudentno();
			String sex = "男";
			if ("1".equals(info.getSex())) {
				sex = "女";
			}
			str[5] = sex;
			str[6] = info.getDispatchtype() == null ? "" : info
					.getDispatchtype();
			str[7] = info.getChargeunit() == null ? "" : info.getChargeunit();
			str[8] = info.getMailno() == null ? "" : info.getMailno();
			str[9] = info.getMemo() == null ? "" : info.getMemo();
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

}
