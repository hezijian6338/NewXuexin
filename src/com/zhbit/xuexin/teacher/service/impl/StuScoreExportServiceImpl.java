package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.dto.StuScoreQuery;
import com.zhbit.xuexin.common.utils.zip.MultipleFileZipUtil;
import com.zhbit.xuexin.common.utils.zip.MultipleFileZipUtil.CompressFile;
import com.zhbit.xuexin.common.utils.zip.TempFolderHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.teacher.dao.CourseInfoStudentsDao;
import com.zhbit.xuexin.teacher.service.StuScoreExportService;

@Transactional(readOnly = true)
@Service("stuScoreExportService")
public class StuScoreExportServiceImpl implements StuScoreExportService {
	@Resource
	private StudentsDao studentsDao;
	@Resource
	private CourseInfoStudentsDao courseInfoStudentsDao;
	@Override
	public void exportScore(StuScoreQuery query, OutputStream out) {
		// TODO Auto-generated method stub
		final TempFolderHelper tfh = new TempFolderHelper();;
		try{
			MultipleFileZipUtil m = new MultipleFileZipUtil(out);
			List<Students> stus = studentsDao.getStudentsByStuQuery(query);
			String academicYearTerm = query.getAcademicYear() + "-" + query.getTerm();
			for(Students stu : stus){
				List<CourseInfoStudents> css = courseInfoStudentsDao.listByStuNoAndAcademicYearAndTerm(
						stu.getStudentno(), query.getAcademicYear(), query.getTerm());
				StringBuilder path = new StringBuilder();
				if(StringUtils.isBlank(query.getOrgName()) && StringUtils.isBlank(query.getClassname())){
					path.append(dealNull(stu.getOrgName())).append(File.separator);
				}
				if(StringUtils.isBlank(query.getClassname())){
					path.append(dealNull(stu.getClassname())).append(File.separator);
				}
				path.append(stu.getStudentno()).append("-").append(stu.getStuname()).append(".xls");
				m.submitTask(new Task(tfh, path.toString(), stu, css,academicYearTerm));
			}
			m.endSubmitAndShutdown();
		}finally {
			if(null != tfh){
				//开启一个线程删除临时文件 
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						tfh.drop();
					}
				}).start();
			}
		}
	}
	private static String dealNull(String s){
		return null == s ? "未知" : s;
	}
	@Override
	public List<Map<String,String>> listOrgName() {
		List<String> orgNames = studentsDao.listOrgName();
		List<Map<String,String>> r = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>(1);
		map.put("value","");
		map.put("name","全部");
		r.add(map);
		for(String orgName : orgNames){
			if(StringUtils.isNotBlank(orgName)){
				map = new HashMap<String, String>(1);
				map.put("value",orgName);
				map.put("name",orgName);
				r.add(map);
			}
		}
		return r;
	}



	@Override
	public List<Map<String,String>> listClassname() {
		List<String> classnames = studentsDao.listClassname();
		List<Map<String,String>> r = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>(1);
		map.put("value","");
		map.put("name","全部");
		r.add(map);
		for(String classname : classnames){
			if(StringUtils.isNotBlank(classname)){
				 map = new HashMap<String, String>(1);
				map.put("value",classname);
				map.put("name",classname);
				r.add(map);
			}
		}
		return r;
	}
	
	private static class Task implements Callable<CompressFile> {
        private TempFolderHelper tfh;
        private String path;
        private Students stu;
        private List<CourseInfoStudents> scores;
        private String academicYearTerm;
        
        
        public Task(TempFolderHelper tfh, String path, Students stu,List<CourseInfoStudents> scores,String academicYearTerm) {
			this.tfh = tfh;
			this.path = path;
			this.stu = stu;
			this.scores = scores;
			this.academicYearTerm = academicYearTerm;
		}


		@Override
        public CompressFile call() throws Exception {
            OutputStream out = null;
            FileInputStream in = null;
    		Workbook wb = null;
    		
            try {
            	String webroot = System.getProperty("ebop-server.root");
            	in = new FileInputStream(webroot + "template" + File.separator
    					+ "stuScoreExport.xls");
    			wb = new HSSFWorkbook(in);
    			Sheet sheet = wb.getSheetAt(0);
    			Row row = sheet.getRow(1);
    			row.getCell(1).setCellValue(stu.getStudentno());
    			row.getCell(5).setCellValue(stu.getStuname());
    			row.getCell(10).setCellValue(academicYearTerm);
    			row = sheet.getRow(2);
    			row.getCell(1).setCellValue(stu.getOrgName());
    			row.getCell(5).setCellValue(stu.getMajor());
    			row.getCell(10).setCellValue(stu.getClassname());
    			for(int i=0;i<scores.size();i++){
    				row = sheet.getRow(i+4);
    				CourseInfoStudents cs = scores.get(i);
    				row.getCell(1).setCellValue(dealNull(cs.getCoursecode()));
    				row.getCell(2).setCellValue(dealNull(cs.getSelectedCoureNo()));
    				row.getCell(3).setCellValue(dealNull(cs.getCoursename()));
    				row.getCell(5).setCellValue(dealNull(cs.getEmployName()));
    				row.getCell(6).setCellValue(dealNull(cs.getCourseType()));
    				row.getCell(7).setCellValue(dealNull(cs.getTotalhours()));
    				row.getCell(8).setCellValue(dealNull(cs.getCredit()));
    				row.getCell(9).setCellValue(dealNull(cs.getLabscore()));
    				row.getCell(10).setCellValue(dealNull(cs.getUsualscore()));
    				row.getCell(11).setCellValue(dealNull(cs.getMiddlescore()));
    				row.getCell(12).setCellValue(dealNull(cs.getEndscore()));
    				row.getCell(13).setCellValue(dealNull(cs.getFinalscore()));
    				row.getCell(14).setCellValue(dealNull(cs.getResitscore()));
    				row.getCell(15).setCellValue(dealNull(cs.getResitmemo()));
    				row.getCell(16).setCellValue(dealNull(cs.getRepairscore()));
    				row.getCell(17).setCellValue(dealNull(cs.getGradepoint()));
    				row.getCell(18).setCellValue(dealNull(cs.getMemo()));
    			}
    			
                File file = tfh.getFile(path);
                out = new FileOutputStream(file);
                wb.write(out);
                return new CompressFile(path, file);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != wb) {
                    try {
                    	wb.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != in) {
                    try {
                    	in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != out) {
                	try {
                		out.close();
                	} catch (IOException e) {
                		e.printStackTrace();
                	}
                }
               
            }
            return null;
        }

		private static String dealNull(Object s) {
			return null == s ? "" : s.toString();
		}
	}



	
}
