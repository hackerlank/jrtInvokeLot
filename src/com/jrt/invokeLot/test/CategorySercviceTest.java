package com.jrt.invokeLot.test;

import org.junit.Test;

import com.jrt.invokeLot.service.CategoryService;

public class CategorySercviceTest {
   private CategoryService service = new CategoryService();
	@Test
	public void getCategoryByTypeTest(){
		service.getCategoryByType("web", 4);
	}
	
}
