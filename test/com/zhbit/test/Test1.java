package com.zhbit.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Test1 {
	@Test
	public void test1() {
		System.out.println("A".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("B".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("C+".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("A-".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("A++".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("Ac".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		System.out.println("A--".matches("^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$"));
		
	}

	
}
