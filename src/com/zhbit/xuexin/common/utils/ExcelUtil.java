package com.zhbit.xuexin.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

/**
 * 导出和操作Excel表
 * 
 * @author liangriyu
 * @email
 * 
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {

	/**
	 * 导出为Excel数据表（array）
	 * 
	 * @param os
	 *            输出流
	 * @param title
	 *            标题
	 * @param heads
	 *            头部列表
	 * @param data
	 *            数据集合
	 * @throws Exception
	 */
	public static void exportForArray(OutputStream os, String title,
			String[] heads, List<Object[]> data) throws Exception {
		// 工作簿编号
		int sheetNum = 1;
		// 当前行号
		int currentRowCount = 1;
		// 每个工作簿显示50000条数据
		int perPageNum = 50000;
		// 工作区
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建第一个sheet
		HSSFSheet sheet = wb.createSheet(title + sheetNum);
		// sheet.setDefaultColumnWidth(10);
		// 生成头信息
		HSSFRow headRow = sheet.createRow(0);
		for (int i = 0; i < heads.length; i++) {
			if(heads[i]!=null)
				headRow.createCell(i).setCellValue(heads[i]);
			else
				headRow.createCell(i).setCellValue("");
		}
		// 生成格子数据
		int p = 1;
		for (int j = 0; j < data.size(); j++) {
			Object[] objs = data.get(j);
			HSSFRow row = sheet.createRow(p++);
			int tip = 0;
			for (int i = 0; i < objs.length; i++) {
				if(objs[i]!=null)
					row.createCell(tip).setCellValue(objs[i] + "");
				else
					row.createCell(tip).setCellValue("");
				tip++;
			}
			if (p % perPageNum == 0) {
				sheet = null;
				sheetNum++;
				sheet = wb.createSheet(title + sheetNum);
				headRow = sheet.createRow(0);
				for (int i = 0; i < heads.length; i++) {
					if(heads[i]!=null)
						headRow.createCell(i).setCellValue(heads[i]);
					else
						headRow.createCell(i).setCellValue("");
				}
				p = 1;
			}
		}

		// 写文件
		wb.write(os);
		// 关闭输出流
		os.flush();
		os.close();
	}

	/**
	 * 导出为Excel数据表（array）
	 * 
	 * @param os
	 *            输出流
	 * @param title
	 *            标题
	 * @param heads
	 *            头部列表
	 * @param data
	 *            数据集合
	 * @throws Exception
	 */
	public static void exportForArray(OutputStream os, String title,
			String[] heads, List<Object[]> data, boolean hasTitle)
			throws Exception {
		// 工作区
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建第一个sheet
		HSSFSheet sheet = wb.createSheet(title);
		// sheet.setDefaultColumnWidth(10);
		Map<String, HSSFCellStyle> styles = createStyles(wb);
		int p = 1;
		if (hasTitle) {
			p = 2;
			// 生成标题信息
			HSSFRow headRow = sheet.createRow(0);
			HSSFCell cell_title = headRow.createCell(0);
			cell_title.setCellValue(title);
			cell_title.setCellStyle(styles.get("cell_header_title"));
			sheet.addMergedRegion(new Region(0, (short) 0, 0,
					(short) (heads.length - 1)));

		}
		// 生成头信息
		HSSFRow headRow = sheet.createRow(p - 1);
		for (int i = 0; i < heads.length; i++) {
			if(heads[i]!=null)
				headRow.createCell(i).setCellValue(heads[i]);
			else
				headRow.createCell(i).setCellValue("");
		}
		// 生成格子数据

		for (int j = 0; j < data.size(); j++) {
			Object[] objs = data.get(j);
			HSSFRow row = sheet.createRow(p++);
			int tip = 0;
			for (int i = 0; i < objs.length; i++) {
				if(objs[i]!=null)
					row.createCell(tip).setCellValue(objs[i] + "");
				else
					row.createCell(tip).setCellValue("");
				tip++;
			}
		}

		// 写文件
		wb.write(os);
		// 关闭输出流
		os.flush();
		os.close();
	}

	public static void exportForScoreArray(OutputStream os, String title,
			String[] heads, List<Object[]> data) throws Exception {
		String webroot = System.getProperty("ebop-server.root");
		FileInputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream(webroot + "template" + File.separator
					+ "scoreconf.xlsx");
			wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			XSSFRow headRow = (XSSFRow) sheet.getRow(0);
			XSSFCell headCell = headRow.getCell(1);
			headCell.setCellValue(title);
			for (int i = 0, j = 2; i < heads.length; i++, j += 2) {
				XSSFRow row = (XSSFRow) sheet.getRow(1 + i / 3);
				XSSFCell cell = row.getCell(j - i / 3 * 6);
				cell.setCellValue(heads[i]);
			}
			for (int i = 0; i < data.size(); i++) {
				String arr[] = (String[]) data.get(i);
				Row row = sheet.getRow(5 + i);
				for (int j = 0; j < arr.length; j++) {
					Cell cell = row.getCell(2 + j);
					cell.setCellValue(arr[j]);
				}

			}
			// 写文件
			wb.write(os);
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
		}
		// 关闭输出流
		// os.flush();
		// os.close();
	}

	public static void exportForScore(HttpServletResponse response,
			String title, String[] heads, List<Object[]> data) throws Exception {
		boolean isNull = (null == title || null == heads || null == data);
		if(isNull){
			throw new NullPointerException("导出数据为空..");
		}
		String downName = new String(title.getBytes("GBK"), "iso8859-1")
				+ ".xlsx";
		response.setHeader("content-disposition", "attachment;filename="
				+ downName);
		response.setContentType("application/octet-stream; charset=utf-8");
		exportForScoreArray(response.getOutputStream(), title, heads, data);
	}

	/**
	 * Title: exportForConfirm Description: 把Response转换成os输出流
	 * 
	 * @param response
	 * @param title
	 * @param headTitle
	 * @param dataList
	 * @throws Exception
	 */
	public static void exportForConfirm(HttpServletResponse response,
			String title, String[] headTitle, List<Object[]> dataList)
			throws Exception {
		String downname = new String(title.getBytes("GBK"), "iso8859-1")
				+ ".xlsx";
		response.setHeader("content-disposition", "attachement;filename="
				+ downname);
		response.setContentType("application/octet-stream;charset=utf-8");
		exportForConfirmArray(response.getOutputStream(), title, headTitle,
				dataList);
	}

	/**
	 * Title: exportForConfirmArray Description: 新增的学生选课确认表导出函数
	 * 
	 * @param os
	 * @param title
	 * @param heads
	 * @param data
	 * @throws Exception
	 */
	public static void exportForConfirmArray(OutputStream os, String title,
			String[] heads, List<Object[]> data) throws Exception {
		String webroot = System.getProperty("ebop-server.root");
		FileInputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream(webroot + "template" + File.separator
					+ "Confirm.xlsx");
			wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			XSSFRow headRow = (XSSFRow) sheet.getRow(0);
			XSSFCell headCell = headRow.getCell(1);
			headCell.setCellValue(title);

			XSSFRow row1 = (XSSFRow) sheet.getRow(21);
			XSSFCell cell1 = row1.getCell(1);
			cell1.setCellValue("\t本人确认本学期学科基础课应选" + heads[4] + "学分，已选"
					+ heads[5] + "学分;必修课应选" + heads[6] + "学分,应选" + heads[7]+
					"学分;专业选修课应选" + heads[8] + "学分,已选" + heads[9]
							+ "学分;本学期已选通识必修课" + heads[10] + "学分，");

			XSSFRow row2 = (XSSFRow) sheet.getRow(22);
			XSSFCell cell2 = row2.getCell(1);
			cell2.setCellValue("通识选修课" + heads[11]
					+ "学分,跨领域选修课"+heads[12] + "学分，其他" + heads[13] + "课"
					+ heads[14] + "学分以上共计" + heads[15] + "学分，上课学期为:"
					+ heads[16]);

			for (int i = 0, j = 2; i < 4; i++, j += 3) {
				XSSFRow row = (XSSFRow) sheet.getRow(1 + i / 2);
				XSSFCell cell = row.getCell(j % 6);
				cell.setCellValue(heads[i]);
			}
			for (int i = 0; i < data.size(); i++) {
				String arr[] = (String[]) data.get(i);
				Row row = sheet.getRow(5 + i);
				for (int j = 0; j < arr.length; j++) {
					Cell cell = row.getCell(2 + j);
					cell.setCellValue(arr[j]);
				}
			}
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wb.close();
			in.close();
		}
	}

	/*
	 * 与export()不同之处在于数据结构不同(object[]输出)
	 */
	public static void exportArray(HttpServletResponse response, String title,
			String[] heads, List<Object[]> data) throws Exception {
		String downName = new String(title.getBytes("GBK"), "iso8859-1")
				+ ".xls";
		response.setHeader("content-disposition", "attachment;filename="
				+ downName);
		response.setContentType("application/octet-stream; charset=utf-8");
		exportForArray(response.getOutputStream(), title, heads, data);
	}

	/*
	 * 与export()不同之处在于数据结构不同(object[]输出)
	 */
	public static void exportArray(HttpServletResponse response, String title,
			String[] heads, List<Object[]> data, boolean hasTitle)
			throws Exception {
		String downName = new String(title.getBytes("GBK"), "iso8859-1")
				+ ".xls";
		response.setHeader("content-disposition", "attachment;filename="
				+ downName);
		response.setContentType("application/octet-stream; charset=utf-8");
		exportForArray(response.getOutputStream(), title, heads, data, hasTitle);
	}

	/**
	 * @Title: getCellValue
	 * @Description: TODO(获取cell的值。)
	 * @author lry
	 * @date 2016-3-27 上午11:46:24
	 * @param cell
	 * @return
	 * @return String
	 */
	@SuppressWarnings({ "static-access" })
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int) cell.getNumericCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
			return String.valueOf(cell.getStringCellValue());
		} else {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * @Title: getCellValue
	 * @Description: TODO(获取cell的值。)
	 * @author lry
	 * @date 2016-3-27 上午11:46:24
	 * @param cell
	 *            numericeIsDouble(ture:数字保留精度，false：整形数字字符)
	 * @return
	 * @return String
	 */
	@SuppressWarnings({ "static-access" })
	public static String getCellNumericeValue(Cell cell) {
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
			return String.valueOf(cell.getStringCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * @Title: setValue
	 * @Description: TODO(设置导出字段值。)
	 * @author lry
	 * @date 2016-3-27 下午10:55:26
	 * @param o
	 * @return
	 * @return String
	 */
	public static String setValue(Object o) {
		return o == null ? "" : o.toString();
	}

	/**
	 * @Title: setDateValue
	 * @Description: TODO(设置导出日期字段值。)
	 * @author lry
	 * @date 2016-3-27 下午10:54:30
	 * @param date
	 * @param format
	 * @return
	 * @return String
	 */
	public static String setDateValue(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return date == null ? "" : df.format(date);
	}

	/**
	 * @Title: getDateValue
	 * @Description: TODO(获取导入日期。)
	 * @author LRY
	 * @date 2016-6-5 下午8:49:25
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 * @return Date
	 */
	public static Date getDateValue(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return (Date) (date == null ? "" : df.parse(date));
	}

	/*----------創建excel styles----------------------------------*/
	private static Map<String, HSSFCellStyle> createStyles(HSSFWorkbook wb) {
		Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
		// ----------------------標題樣式---------------------------
		HSSFCellStyle cell_header_title = wb.createCellStyle();
		HSSFFont font_header_title = wb.createFont();
		font_header_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗體
		font_header_title.setFontHeight((short) (9 * 20));
		font_header_title.setFontName("Times New Roman");// 字體樣式
		cell_header_title.setFont(font_header_title);
		cell_header_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		cell_header_title.setWrapText(true);
		styles.put("cell_header_title", cell_header_title);

		// -----------------------設置字符樣式---------------------------

		HSSFCellStyle cell_data_default = wb.createCellStyle();
		HSSFFont font_data_default = wb.createFont();
		font_data_default.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_data_default.setFontHeight((short) (8 * 20));
		font_data_default.setFontName("Arial Narrow");// 字體樣式
		cell_data_default.setFont(font_data_default);
		cell_data_default.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 居中
		cell_data_default.setWrapText(true);// 自動換行
		styles.put("cell_data_default", cell_data_default);
		return styles;
	}
	
	/**
	 * @author 邹方翔
	 * @Title 格式转换
	 * @Description:将班级如“软件工程[1-3]班”转换为软件工程1班，软件工程2班，软件工程3班
	 * @param str
	 * @return
	 */
	
	public static String formatTransition(String str){
		List<String> r = new ArrayList<String>();
		if (null!=str) {
			String[] split =str.split(",");
			for (String array : split) {
				if (array.contains("[")) {
					String className = array.substring(0,array.indexOf("["));
					char min = array.charAt(array.length()-5);
					char max = array.charAt(array.length()-3);
					for (char i=min;i<=max;i++) {
						r.add(className+i+"班");
					}
				}else {
					r.add(array);
				}
			}
		}
		StringBuffer stringBuffer = new StringBuffer();
		if (r.size()==1) {
			stringBuffer.append(r.get(0));
		}else {
			for (int i = 0; i < r.size()-1; i++) {
			stringBuffer.append(r.get(i)+",");
			}
			stringBuffer.append(r.get(r.size()-1));
		}
		
		return stringBuffer.toString();
	}
}