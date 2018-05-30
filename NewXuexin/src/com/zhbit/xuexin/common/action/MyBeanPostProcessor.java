package com.zhbit.xuexin.common.action;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.zhbit.xuexin.student.service.StudentsService;
import com.zhbit.xuexin.sys.service.SystemService;
import com.zhbit.xuexin.teacher.service.TeacherInfoService;

/**
 * 
 * spring环境启动完成后初始化一些必要系统参数
 * 
 * @author <b>梁日宇<br/>
 *         
 * @version 1.0
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	    if (bean instanceof SystemService) {// 系统缓存
            ((SystemService) bean).initSystem();
        }else if (bean instanceof TeacherInfoService) {
            ((TeacherInfoService) bean).init();
        }else if (bean instanceof StudentsService) {
            ((StudentsService) bean).init();
        }
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
