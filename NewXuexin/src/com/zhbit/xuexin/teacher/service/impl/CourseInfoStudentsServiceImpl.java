
package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
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
import com.zhbit.xuexin.common.dao.impl.BaseDaoImpl;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.ExcelErrorInfo;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.teacher.dao.CourseInfoDao;
import com.zhbit.xuexin.teacher.dao.CourseInfoStudentsDao;
import com.zhbit.xuexin.teacher.service.CourseInfoStudentsService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("CourseInfoStudentsService")
@Transactional(readOnly = true)
public class CourseInfoStudentsServiceImpl extends BaseDaoImpl<CourseInfoStudents>
		implements CourseInfoStudentsService {
	private Logger logger = LoggerFactory.getLogger(CourseInfoStudentsServiceImpl.class);

	@Autowired
	@Qualifier("courseInfoStudentsDao")
	private CourseInfoStudentsDao dao;

	@Autowired
	@Qualifier("studentsDao")
	private StudentsDao studentsDao;

	@Autowired
	@Qualifier("CourseInfoDao")
	private CourseInfoDao courseInfoDao;

	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao organizationDao;

	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao orgDao;
	// xucaikai 2017.04.13
	private HashSet addStuNO = new HashSet();

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
	public Page<CourseInfoStudents> getList(Page<CourseInfoStudents> page) {
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
	public int save(CourseInfoStudents info, String userId) {
		if (info != null) {
			Students stu = studentsDao.getStudentByNo(info.getStudentno());
			// 练浩文-2017.05.11 验证该课程代码是否存在
			CourseInfo courseInfo = courseInfoDao.getCourseInfoBySelectedcourseno(info.getSelectedCoureNo());
			Organization organization = organizationDao.getOrgByName(info.getOrgName());
			if (stu == null || courseInfo == null) {

				return 0;
			}
			// 许彩开-2017.03.28-验证输入的姓名和学号对应的姓名是否一致
			if (!stu.getStuname().equals(info.getStuname())) {
				return 0;
			}
			info.setStuname(stu.getStuname());
			info.setAcademicYear(courseInfo.getAcademicyear());
			info.setEmployName(courseInfo.getEmployName());
			info.setEmployNo(courseInfo.getEmployNo());
			info.setOrgId(organization.getOrgId());
			info.setParentOrgId(courseInfo.getParentOrgId());
			info.setTerm(courseInfo.getTerm());
			info.setCourseType(courseInfo.getCoursetype());
			info.setCredit(String.valueOf(courseInfo.getCredit()));
			info.setCoursecode(courseInfo.getCoursecode());
			info.setTotalhours(courseInfo.getTotalhours());
			info.setStuId(stu.getStuId());
			Organization org = orgDao.getOrganizationById(info.getOrgId());
			info.setSelectedCoureNo(courseInfo.getSelectedcourseno());
			info.setOrgName(org.getOrgName());
			info.setCreateTime(new Date());
			info.setCreator(userId);
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
	 * @return CourseInfoStudents
	 */
	@Override
	public CourseInfoStudents getCourseInfoStudentsByid(String id) {
		return dao.getCourseInfoStudentsByid(id);
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
	public int update(CourseInfoStudents info, CourseInfoStudents oldInfo) {
		Students stu = studentsDao.getStudentByNo(info.getStudentno());
		CourseInfo courseInfo = courseInfoDao.getCourseInfoBySelectedcourseno(info.getSelectedCoureNo());
		Organization organization = organizationDao.getOrgByName(info.getOrgName());
		// 许彩开-2017.03.28-验证输入的姓名和学号对应的姓名是否一致
		if (stu == null || courseInfo == null) {
			// System.out.println("++++++++++++++"+"输出这里"+stu.getStuname()+"
			// "+stu.getStuname()+" "+courseInfo);
			return 0;
		} else {
			info.setStuname(stu.getStuname());
			info.setAcademicYear(courseInfo.getAcademicyear());
			info.setEmployName(courseInfo.getEmployName());
			info.setEmployNo(courseInfo.getEmployNo());
			info.setOrgId(organization.getOrgId());
			info.setParentOrgId(courseInfo.getParentOrgId());
			info.setTerm(courseInfo.getTerm());
			info.setCourseType(courseInfo.getCoursetype());
			info.setCredit(String.valueOf(courseInfo.getCredit()));
			info.setCoursecode(courseInfo.getCoursecode());
			info.setTotalhours(courseInfo.getTotalhours());

			info.setStuId(stu.getStuId());
			info.setOrgName(info.getOrgName());
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			dao.update(info);
			return 1;
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
				CourseInfoStudents info = dao.getCourseInfoStudentsByid(id);
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
		int studentNoExist = 0;// 学生不存在的总条数
		int courseCodeNoExist = 0; // 课程号不存在的总条数
		// 许彩开 2017.04.20
		int existCount = 0; // 重复的总条数
		// xucaikai 2017.04.13
		Iterator iterStuNo = addStuNO.iterator();
		addStuNO.add("12");
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] { -1, insertCount, updateCount, existCount };
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
					importCount++;
					// System.out.println("客观上的分否会===" + importCount + "条");
					// xucaikai 2017.04.13
					boolean flag = false;// 标志作用
					String StuNoTemp = replaceTrim(ExcelUtil.getCellValue(row.getCell(0)));
					String selectedCoureNo = ExcelUtil.getCellValue(row.getCell(6));
					if (StringUtils.isBlank(selectedCoureNo) && StringUtils.isBlank(StuNoTemp)) {
						continue;
					}
					CourseInfo courseInfo = courseInfoDao
							.getCourseInfoBySelectedcourseno(replaceTrim(ExcelUtil.getCellValue(row.getCell(6))));
					Students students = studentsDao.getStudentByNo(StuNoTemp);
					Organization organization = organizationDao
							.getOrgByName(replaceTrim(ExcelUtil.getCellValue(row.getCell(2))));
					if (!addStuNO.contains(StuNoTemp)) { // 如果HashSet中存在该学号就跳过数据库查询
						int isExist = dao.getCourseInfoStudentsByid_exist(row.getCell(0).getStringCellValue());
						if (isExist == 0 || courseInfo == null || students == null) {// 不存在学生信息不导入
							updateCount++;
							flag = false;// 不存在该学生
							// System.out.println("查询数据库中不存在该学号===" +
							// StuNoTemp);
						} else {
							addStuNO.add(StuNoTemp);// 把新查询存在的学号加入
							flag = true;// 存在该学生
							// System.out.println("查询数据库中存在该学号===" + StuNoTemp);
						}
					} else {
						if (courseInfo == null || students == null) {
							updateCount++;
							flag = false;
						} else {
							flag = true;// 存在该学生
							// System.out.println("HashSet中存在该学号===" +
							// StuNoTemp);
						}
					}
					String courseInfoString;
					if (courseInfo != null) {
						courseInfoString = courseInfo.getCoursecode();
					} else {
						courseInfoString = "";
						courseCodeNoExist++;
						flag = false;
					}

					// int
					// isExistInfo=dao.getCourseInfoStudentsByT_COURSEINFO_STUDENTS_exist(ExcelUtil.getCellValue(row.getCell(0)),
					// courseInfoString);
					int isExistInfo = dao.getCourseInfoStudentsByT_COURSEINFO_STUDENTS_selected_exist(
							ExcelUtil.getCellValue(row.getCell(0)), ExcelUtil.getCellValue(row.getCell(6)));
					if (isExistInfo != 0) {
						existCount++;// 记录重复信息条数；
						flag = false;// 重复信息不插入
					}

					if (flag == true) {
						CourseInfoStudents info = new CourseInfoStudents();
						info.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
						info.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
						info.setOrgName(ExcelUtil.getCellValue(row.getCell(2)));
						info.setClassname(ExcelUtil.getCellValue(row.getCell(3)));
						info.setMajorcode(ExcelUtil.getCellValue(row.getCell(4)));
						info.setMajor(ExcelUtil.getCellValue(row.getCell(5)));
						info.setSelectedCoureNo(ExcelUtil.getCellValue(row.getCell(6)));

						info.setCoursename(ExcelUtil.getCellValue(row.getCell(7)));

						info.setRetakeflag(ExcelUtil.getCellValue(row.getCell(8)).equals("是") ? "Y" : "N");
						if (!ExcelUtil.getCellValue(row.getCell(9)).equals(""))
							info.setUsualscore(ExcelUtil.getCellValue(row.getCell(9)));
						if (!ExcelUtil.getCellValue(row.getCell(10)).equals(""))
							info.setMiddlescore(ExcelUtil.getCellValue(row.getCell(10)));
						if (!ExcelUtil.getCellValue(row.getCell(11)).equals(""))
							info.setEndscore(ExcelUtil.getCellValue(row.getCell(11)));
						if (!ExcelUtil.getCellValue(row.getCell(12)).equals(""))
							info.setLabscore(ExcelUtil.getCellValue(row.getCell(12)));
						if (!ExcelUtil.getCellValue(row.getCell(13)).equals(""))
							info.setFinalscore(ExcelUtil.getCellValue(row.getCell(13)));
						if (!ExcelUtil.getCellValue(row.getCell(14)).equals(""))
							info.setConvertscore(ExcelUtil.getCellValue(row.getCell(14)));
						if (!ExcelUtil.getCellValue(row.getCell(15)).equals(""))
							info.setResitscore(ExcelUtil.getCellValue(row.getCell(15)));
						info.setResitmemo(ExcelUtil.getCellValue(row.getCell(16)));
						if (!ExcelUtil.getCellValue(row.getCell(17)).equals(""))
							info.setRepairscore(ExcelUtil.getCellValue(row.getCell(17)));
						if (!ExcelUtil.getCellValue(row.getCell(18)).equals(""))
							info.setGradepoint(Double.valueOf(ExcelUtil.getCellValue(row.getCell(18))));
						info.setMemo(ExcelUtil.getCellValue(row.getCell(19)));

						info.setAcademicYear(courseInfo.getAcademicyear());
						info.setEmployName(courseInfo.getEmployName());
						info.setEmployNo(courseInfo.getEmployNo());
						info.setOrgId(organization.getOrgId());
						info.setParentOrgId(courseInfo.getParentOrgId());
						info.setTerm(courseInfo.getTerm());
						info.setCourseType(courseInfo.getCoursetype());
						info.setCredit(String.valueOf(courseInfo.getCredit()));
						info.setCoursecode(courseInfo.getCoursecode());
						info.setTotalhours(courseInfo.getTotalhours());

						info.setStuId(students.getStuId());
						info.setGarde(students.getGrade());

						info.setCreateTime(new Date());
						info.setCreator(user.getUserId());
						dao.save(info);
						insertCount++;
						flag = false;
						if (insertCount % 100 == 0) {
							dao.flush();
							System.out.println("正在导入...");
						}
					}

				} catch (Exception e) {
					msg = (ExcelUtil.getCellValue(row.getCell(0))) + (ExcelUtil.getCellValue(row.getCell(1)));
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
				}
			}
		}
		return new int[] { importCount, insertCount, updateCount, existCount, studentNoExist, courseCodeNoExist };
	}

	private static String replaceTrim(String s) {

		if (s != null) {
			return s.replaceAll(" ", "");
		}
		return null;
	}

	/**
	 * @Title: importScoreFile
	 * @Description: TODO(导入成绩文件)
	 * @author lry
	 * @date 2016-3-15 下午10:47:48
	 * @param excel
	 * @param user
	 * @return
	 * @return int[]
	 */
	final static String regix = "^[0-9]+$";

	private boolean isDataNeedsToImport(String data) {
		Pattern pattern = Pattern.compile(regix);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

	private int[] importScore(File excel, User user, String suffix, Boolean isResit, String selectedAcademicyear,
			String selectedSemester, String CourseCode, String selectedcourseno) {
		Workbook wb = null;
		FileInputStream in = null;
		int importCount = 0;// 导入的总条数
		int notImportedCount = 0;// 导入新增的总条数、
		int updateCount = 0;// 导入更新的总条数
		final int courseInfoRowsNum = 5;
		String courceDate = "";
		String academicYear = "";
		String semester = "";
		String courseNo = "";
		String courseName = "";
		String orgName = "";
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] { -1, notImportedCount, updateCount, 0 };
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
			courceDate = sheet.getRow(1).getCell(0).getStringCellValue();
			if (courceDate.split(":").length == 2)
				courceDate = courceDate.split(":")[1];
			else if (courceDate.split("：").length == 2)
				courceDate = courceDate.split("：")[1];
			else
				return new int[] { 0, 0, 0, 4 };
			academicYear = courceDate.substring(0, courceDate.lastIndexOf("-"));
			semester = courceDate.substring(courceDate.lastIndexOf("-") + 1, courceDate.length());
			courseNo = sheet.getRow(2).getCell(0).getStringCellValue();
			if (courseNo.split(":").length == 2)
				courseNo = courseNo.split(":")[1];
			else if (courseNo.split("：").length == 2)
				courseNo = courseNo.split("：")[1];
			else
				return new int[] { 0, 0, 0, 4 };
			orgName = sheet.getRow(1).getCell(5).getStringCellValue();

			if (orgName.split(":").length == 2)
				orgName = orgName.split(":")[1];
			else if (orgName.split("：").length == 2)
				orgName = orgName.split("：")[1];
			else
				return new int[] { 0, 0, 0, 4 };

			courseName = sheet.getRow(2).getCell(5).getStringCellValue();
			if (courseName.split(":").length == 2)
				courseName = courseName.split(":")[1];
			else if (courseName.split("：").length == 2)
				courseName = courseName.split("：")[1];
			else
				return new int[] { 0, 0, 0, 4 };

			if (!selectedAcademicyear.equals(academicYear) || !selectedSemester.equals(semester)
					|| !CourseCode.equals(courseNo))
				return new int[] { 0, 0, 0, 4 };

			List<CourseInfoStudents> css = dao.getCourseInfoStudentNeedUpdateScore(academicYear, semester, courseNo,
					user.getEmployNo());
			Map<String, CourseInfoStudents> cache = new HashMap<String, CourseInfoStudents>(css.size());
			for (CourseInfoStudents cs : css) {
				cache.put(cs.getStudentno(), cs);
			}
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			for (int i = 0; i < courseInfoRowsNum; i++) {
				rowIterator.next();
			}
			String msg = null;
			String studentNo = "";
			String studentName = "";
			String className = "";
			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					System.out.println("是否需要导入的行" + isDataNeedsToImport(ExcelUtil.getCellValue(row.getCell(0))));
					if (!ExcelUtil.getCellValue(row.getCell(2)).equals("")
							&& !ExcelUtil.getCellValue(row.getCell(0)).equals("")
							&& isDataNeedsToImport(ExcelUtil.getCellValue(row.getCell(0)))) {
						importCount++;
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						className = row.getCell(1).getStringCellValue();
						studentNo = row.getCell(2).getStringCellValue();
						studentName = row.getCell(4).getStringCellValue();
						CourseInfoStudents cis = cache.get(studentNo);
						if (cis != null) {
							if (isResit) {
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								cis.setUsualscore(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());
								cis.setRetakeflag("Y");
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setResitscore(
										row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setConvertscore(
										row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								System.out.println("补考成绩：" + cis.getResitscore());
							} else {
								cis.setRetakeflag("N");
								row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
								cis.setLabscore(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue());
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								cis.setUsualscore(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());
								row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
								cis.setMiddlescore(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue());
								row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
								cis.setFinalscore(row.getCell(8) == null ? null : row.getCell(8).getStringCellValue());
								row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
								System.out.println("期末成绩" + cis.getFinalscore());
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setEndscore(row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								System.out.println("最终成绩" + cis.getEndscore());
							}
							dao.update(cis);
							updateCount++;
						} else {
							cis = new CourseInfoStudents();
							cis.setCoursecode(courseNo);
							cis.setCoursename(courseName);
							cis.setStudentno(studentNo);
							cis.setAcademicYear(academicYear);
							cis.setTerm(semester);
							cis.setClassname(className);
							cis.setOrgName(orgName);
							cis.setStuname(studentName);
							cis.setSelectedCoureNo(selectedcourseno);
							cis.setEmployNo(user.getEmployNo());
							cis.setEmployName(user.getEmployName());
							if (isResit) {
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								cis.setUsualscore(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());
								cis.setRetakeflag("Y");
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setResitscore(
										row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setConvertscore(
										row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								System.out.println("补考成绩：" + cis.getResitscore());
							} else {
								cis.setRetakeflag("N");
								row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
								cis.setLabscore(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue());
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								cis.setUsualscore(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());
								row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
								cis.setMiddlescore(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue());
								row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
								cis.setFinalscore(row.getCell(8) == null ? null : row.getCell(8).getStringCellValue());
								row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
								System.out.println("期末成绩" + cis.getFinalscore());
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								cis.setEndscore(row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());
								System.out.println("最终成绩" + cis.getEndscore());
							}
							dao.save(cis);
							System.out.println("新添加的数据" + cis.getStuname());
							updateCount++;
						}
					}
				} catch (Exception e) {
					msg = (ExcelUtil.getCellValue(row.getCell(0))) + (ExcelUtil.getCellValue(row.getCell(1)));
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
					return new int[] { 0, 0, 0, 0 };
				}
			}
		}
		notImportedCount = importCount - updateCount;
		return new int[] { importCount, notImportedCount, updateCount, 0 };
	}

	@Override
	@Transactional(readOnly = false)
	public int[] importPositiveTestScoreFile(File excel, User user, String suffix, String academicyear, String semester,
			String coursecode, String selectedcourseno) {
		return importScore(excel, user, suffix, false, academicyear, semester, coursecode, selectedcourseno);
	}

	@Override
	@Transactional(readOnly = false)
	public int[] importResitTestScoreFile(File excel, User user, String suffix, String academicyear, String semester,
			String coursecode, String selectedcourseno) {
		return importScore(excel, user, suffix, true, academicyear, semester, coursecode, selectedcourseno);
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
	public ExportExcelVO exportExcelList(Page<CourseInfoStudents> page) {
		page.setPage(1);
		page.setRows(300000);
		// 获取查询结果数据集
		Page<CourseInfoStudents> pageResult = dao.getList(page);
		List<CourseInfoStudents> list = pageResult.getResult();
		// 设置表头

		String[] title = { "学号", " 姓名", " 学院名称", "班级", "专业代码", "专业", "选课课号", "课程代码", "课程名称", "是否补考", "平时成绩", "期中成绩",
				"期末成绩", "实验成绩", "总评成绩", "折算成绩", "补考成绩", "补考成绩备注", "重修成绩", "绩点", "备注" };
		// 设置文件名
		String filename = "学生选课及成绩明细表";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			CourseInfoStudents info = list.get(i);
			String[] str = new String[21];
			str[0] = ExcelUtil.setValue(info.getStudentno());
			str[1] = ExcelUtil.setValue(info.getStuname());
			str[2] = ExcelUtil.setValue(info.getOrgName());
			str[3] = ExcelUtil.setValue(info.getClassname());
			str[4] = ExcelUtil.setValue(info.getMajorcode());
			str[5] = ExcelUtil.setValue(info.getMajor());
			str[6] = ExcelUtil.setValue(info.getSelectedCoureNo());
			str[7] = ExcelUtil.setValue(info.getCoursecode());
			str[8] = ExcelUtil.setValue(info.getCoursename());
			str[9] = ExcelUtil.setValue(info.getRetakeflag()).equals("N") ? "否" : "是";
			str[10] = ExcelUtil.setValue(info.getUsualscore());
			str[11] = ExcelUtil.setValue(info.getMiddlescore());
			str[12] = ExcelUtil.setValue(info.getEndscore());
			str[13] = ExcelUtil.setValue(info.getLabscore());
			str[14] = ExcelUtil.setValue(info.getFinalscore());
			str[15] = ExcelUtil.setValue(info.getConvertscore());
			str[16] = ExcelUtil.setValue(info.getResitscore());
			str[17] = ExcelUtil.setValue(info.getResitmemo());
			str[18] = ExcelUtil.setValue(info.getRepairscore());
			str[19] = ExcelUtil.setValue(info.getGradepoint());
			str[20] = ExcelUtil.setValue(info.getMemo());
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

	/**
	 * @Title: getCourseDetail
	 * @Description: TODO(通过学号获取课程及成绩明细。)
	 * @author lry
	 * @date 2016-5-12 下午10:12:36
	 * @param studentno
	 * @return
	 * @return List<Object[]>
	 */
	@Override
	public List<Object[]> getCourseDetail(String studentno) {
		return dao.getCourseDetail(studentno);
	}

	@Override
	public List<ExcelErrorInfo> verifyFile(File excel, String suffix) throws Exception {
		List<ExcelErrorInfo> errorInfos = new ArrayList<ExcelErrorInfo>();
		Workbook wb = null;
		FileInputStream in = null;
		Set<String> stuNoCache = new HashSet<String>();
		Set<String> stuNoErrorCache = new HashSet<String>();
		Set<String> selectedcoursenoCache = new HashSet<String>();
		Set<String> selectedcoursenoErrorCache = new HashSet<String>();
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}

			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			int i = 2;
			Row row = null;
			rowIterator.next();// 第一行标题不读
			while (rowIterator.hasNext()) {
				StringBuilder msg = new StringBuilder(0);
				row = rowIterator.next();
				String stuNoTemp = ExcelUtil.getCellValue(row.getCell(0));
				if (!stuNoCache.contains(stuNoTemp)) {
					if (stuNoErrorCache.contains(stuNoTemp)) {
						// msg.append("学号").append(stuNoTemp).append("不存在,");
					} else {
						Students students = studentsDao.getStudentByNo(stuNoTemp);
						if (null == students) {
							stuNoErrorCache.add(stuNoTemp);
							msg.append("学号").append(stuNoTemp).append("不存在,");
						} else {
							stuNoCache.add(stuNoTemp);
						}
					}
				}
				String selectedcourseno = ExcelUtil.getCellValue(row.getCell(6));
				if (!selectedcoursenoCache.contains(selectedcourseno)) {
					if (selectedcoursenoErrorCache.contains(selectedcourseno)) {
						// msg.append("选课课号").append(selectedcourseno).append("不存在,");
					} else {
						CourseInfo courseInfo = courseInfoDao.getCourseInfoBySelectedcourseno(selectedcourseno);
						if (null == courseInfo) {
							selectedcoursenoErrorCache.add(selectedcourseno);
							msg.append("选课课号").append(selectedcourseno).append("   课程名")
									.append(ExcelUtil.getCellValue(row.getCell(7))).append("   教师姓名")
									.append(ExcelUtil.getCellValue(row.getCell(24))).append("   教师工号")
									.append(ExcelUtil.getCellValue(row.getCell(23))).append("不存在,");
						} else {
							selectedcoursenoCache.add(selectedcourseno);
						}
					}
				}
				if (msg.length() > 0) {
					errorInfos.add(new ExcelErrorInfo(i, msg.toString()));
				}
				i++;
			}
		} finally {
			if (wb != null) {
				wb.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("读取导入的文件时关闭输入流异常", e);
				}
			}
		}

		return errorInfos;
	}
	@Transactional(readOnly=false)
	@Override
	public int[] importScoreFile(File excel, String suffix) throws Exception {
		Workbook wb = null;
		FileInputStream in = null;
		int updateCount = 0;
		int insertCount = 0;
		int errorCount = 0;
		int totalCount = 0;
		int normalcount = 0;
		int resitcount = 0;
		int repaircount = 0;
		

		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] {  };
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
			if(rowIterator.hasNext()){
				//标题不读
				rowIterator.next();
			}
			Row row = null;
			String studentNo = "";
			String academicYearTerm = "";
			String academicYear = "";
			String term = "";
			String courseCode = "";
			String scoreType = "";
			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					totalCount++;
					studentNo = ExcelUtil.getCellValue(row.getCell(0));
					courseCode = ExcelUtil.getCellValue(row.getCell(4));
					//System.out.println(studentNo);
					if(StringUtils.isBlank(studentNo) || StringUtils.isBlank(courseCode)){
						errorCount++;
						continue;
					}
					academicYearTerm = ExcelUtil.getCellValue(row.getCell(2));
					academicYear = academicYearTerm.substring(0, 9);
					term = academicYearTerm.substring(10);
					CourseInfoStudents cs = dao.getByStuNoAndCourseCodeAndAcademicYearAndTerm(studentNo, courseCode, academicYear, term);
					if(null == cs){
						insertCount++;
						cs = new CourseInfoStudents();
						cs.setStudentno(studentNo);
						cs.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
						cs.setAcademicYear(academicYear);
						cs.setTerm(term);
						cs.setSelectedCoureNo(ExcelUtil.getCellValue(row.getCell(5)));
						cs.setCoursename(ExcelUtil.getCellValue(row.getCell(6)));
						cs.setCoursecode(courseCode);
						cs.setClassname(ExcelUtil.getCellValue(row.getCell(3)));
						cs.setCourseType(ExcelUtil.getCellValue(row.getCell(14)));
						cs.setOrgName(ExcelUtil.getCellValue(row.getCell(17)));
						String teaInfo = ExcelUtil.getCellValue(row.getCell(18));
						int i1 = teaInfo.indexOf("[");
						if(i1 != -1){
							cs.setEmployName(teaInfo.substring(0,i1));
							cs.setEmployNo(teaInfo.substring(i1+1,teaInfo.length()-1 ));
						}
						String totalhoursInfo = ExcelUtil.getCellValue(row.getCell(15));
						cs.setTotalhours(Integer.valueOf(totalhoursInfo.substring(0, totalhoursInfo.indexOf("("))));
						cs.setCredit(ExcelUtil.getCellValue(row.getCell(16)));
					}else{
						updateCount++;
					}
					scoreType = ExcelUtil.getCellValue(row.getCell(19));
					if ("正常考试".equals(scoreType)) {
						cs.setUsualscore(ExcelUtil.getCellValue(row.getCell(7)));
						cs.setMiddlescore(ExcelUtil.getCellValue(row.getCell(8)));
						cs.setEndscore(ExcelUtil.getCellValue(row.getCell(9)));
						cs.setLabscore(ExcelUtil.getCellValue(row.getCell(10)));
						cs.setFinalscore(ExcelUtil.getCellValue(row.getCell(11)));
						cs.setMemo(ExcelUtil.getCellValue(row.getCell(12)));
						cs.setRetakeflag("N");
						normalcount++;
					} else if ("补考".equals(scoreType)) {
						cs.setResitscore(ExcelUtil.getCellValue(row.getCell(11)));
						cs.setResitmemo(ExcelUtil.getCellValue(row.getCell(12)));
						cs.setRetakeflag("Y");
						resitcount++;
					} else if ("重考".equals(scoreType)){
						cs.setRepairscore(ExcelUtil.getCellValue(row.getCell(11)));
						repaircount++;
					}else{
						errorCount++;
						continue;
					}
					dao.saveOrUpdate(cs);
					if(totalCount % 50 == 0){
						dao.flush();
						System.out.println("正在导入...");
					}

				} catch (Exception e) {
					e.printStackTrace();
					errorCount++;
				}
			}
			wb.close();
		}
		
		return new int[] {totalCount,updateCount,insertCount,errorCount,normalcount,resitcount,repaircount};
	}

}
