package com.zhbit.xuexin.common.action;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.util.ValueStack;
import com.zhbit.xuexin.common.Const;

/**
 * 分页查询信息
 * 
 * @author 梁日宇
 * 
 */
public class Page<T> {

	/**
	 * 需要查询当前页的页数
	 */
	private int page;

	/**
	 * 页面的大小
	 */
	private int rows;

	/**
	 * 总记录数
	 */
	private long count;

	/**
	 * 分页的记录结果集
	 */
	private List<T> result;

	/**
	 * 是否需要分页
	 */
	private boolean toPage = true;

	/**
	 * 过滤条件
	 */
	private Map<String, Object> paras = new LinkedHashMap<String, Object>();

	/**
	 * 构造函数
	 * 
	 */
	public Page() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param req
	 *            请求
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page(HttpServletRequest request) {
		super();
		page = (Integer) request.getAttribute("page");
		rows = (Integer) request.getAttribute("rows");
		ValueStack  v = (ValueStack) request.getAttribute("struts.valueStack");
		Map<String,Object> map = (Map<String, Object>) v.getContext().get("com.opensymphony.xwork2.ActionContext.parameters");
		
		for (Iterator item = map.keySet().iterator(); item.hasNext();) {
			String key = (String) item.next();	
			Object value = v.findValue(key);
			if (value!=null && !"".equals(value)) {
				paras.put(key, value);
				System.out.println("---------------->params:"+key+"="+value);
			}
		}
		
		
//		Enumeration<String> enums = request.getAttributeNames();
//		while (enums.hasMoreElements()) {
//			String key = enums.nextElement();
//			Object value = request.getAttribute(key);
//			if (value != null) {
//				paras.put(key, value);
//			}
//		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public boolean isToPage() {
		return toPage;
	}

	public void setToPage(boolean toPage) {
		this.toPage = toPage;
	}

	public Map<String, Object> getParas() {
		return paras;
	}

	public void setParas(Map<String, Object> paras) {
		this.paras = paras;
	}
	
	/**
	 * 获取当前页的起始位置（从零开始）
	 * 
	 * @return
	 */
	public int getFirst() {
		return (page - 1) * rows;
	}

}
