package com.zhbit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhbit.test.service.IStuService;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.Students;

public class StuServiceImplTest {
	IStuService ss;

	//@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:applicationContext.xml");

		ss = (IStuService) ctx.getBean("stuService");

		assertNotNull(ss);
	}

	@Test
	public void test1() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		PageResult result = ss.queryPageResult(qh, 1, 10);
		System.out.println(result);
		
	}

	@Test
	public void test2() {
		PageResult result = ss.queryPageResult("FROM Students", 1, 10);
		System.out.println(result);
	}

	@Test
	public void test3() {
		Students stu = new Students();
		stu.setStuname("邹翔");
		stu.setStudentno("1402020610121");
		ss.save(stu);
		System.out.println(stu);
	}
	
	@Test
	public void test4() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s")
			.setWhere("s.studentno=?", "140202061012");
		List<Students> stu = ss.query(qh);
		System.out.println(stu);
	}
	
	@Test
	public void test5() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
	
		qh.setWhereAND("s.studentno=?", "1402020610121");

		
		Students stu = (Students) ss.query(qh).get(0);
		stu.setStuname("zouxiang1");
		ss.update(stu);
		stu = (Students) ss.query(qh).get(0);
		System.out.println(stu);
	}
	@Test
	public void test6() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s")
			.setWhere("s.studentno=?", "1402020610121");
		Students stu = (Students) ss.query(qh).get(0);
		
		ss.delete(stu.getStuId());
		List<Students> stu1 = ss.query(qh);
		System.out.println(stu1);
	}
	
	@Test
	public void test7() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s")
			.setWhere("s.studentno=?", "1402020610121");
		Students stu = (Students) ss.query(qh).get(0);
		System.out.println(stu);
		ss.delete(stu);
		List<Students> stu1 = ss.query(qh);
		System.out.println(stu1);
	}
	
	@Test
	public void test8() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		qh.setWhere("s.no=?", "110");
		qh.setWhereAND("s.name=?", "zouxiang");
		qh.setGroup("s.class").setOrder("s.id", QueryHelper.DESC);
		qh.setSelect("s.name,s.id,s.no");
		System.out.println(qh.getHQL());
		System.out.println(qh.getArgs());
		
	}
	
}
