package com.zhbit.xuexin.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * 处理未知的类和实例工具类
 * 
 * @author 梁日宇
 * @version 1.0
 */
public class BeanFactoryUtil {

	/**
	 * 通过全类名实例化一个类
	 * 
	 * @param className
	 *            类名
	 * @return Object
	 */
	public static Object getNewInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 获取对象实例对应属性的值
	 * 
	 * @param o
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return Object
	 */
	public static Object getFieldValue(Object o, String fieldName) {
		Object value = null;
		try {
			Field[] fields = o.getClass().getDeclaredFields();// 获取所有属性
			for (int i = 0, len = fields.length; i < len; i++) {
				String varName = fields[i].getName();
				if (fieldName.equals(varName)) {
					value = fields[i].get(o);
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return value;
		}
	}

	/**
	 * 获得一个实体对象
	 * @param o 需要进行查询操作的类
	 * @param targetMethod 目标方法名
	 * @param arguments 参数数组
	 * @return Object
	 */
	public static Object getEntityObject(Object o, String targetMethod, Object[] arguments) {
		Object result = null;
		try {
			Method[] methods = o.getClass().getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				//System.out.println("方法名称:" + methodName);
				if(methodName.equals(targetMethod)){
					result = method.invoke(o, arguments);//执行方法
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	/**
	 * @Title: assemblingSqlResult 
	 * @Description: TODO 将SQL查询的结果实例化到对象结合中 
	 * @param resultList
	 * @param params
	 * @param className
	 * @return List
	 * @throws
	 * @author 梁日宇
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List assemblingSqlResult(List<Object[]> resultList, String[] params, String className){
		List dataList=new ArrayList();
		Object entity=null;
		//注册一个日期格式转换器
		//如果要使用特殊的日期类型，则String->Date 不能自动转换,这时候就要注册一个转换器
        ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
		for(Object[] objArray:resultList){
			entity=getNewInstance(className);
			for(int i=0;i<params.length;i++){
				try {
					BeanUtils.setProperty(entity, params[i], objArray[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dataList.add(entity);
		}
		return dataList;
	}
	/**
	 * @Title: assemblingSqlResult 
	 * @Description: TODO 将SQL查询的结果实例化到对象中
	 * @param result
	 * @param params
	 * @param className
	 * @return
	 * Object
	 * @throws
	 * @author 梁日宇
	 */
	public static Object assemblingSqlResult(Object[] result, String[] params, String className){
		Object entity=getNewInstance(className);
		for(int i=0;i<params.length;i++){
			try {
				BeanUtils.setProperty(entity, params[i], result[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
}
