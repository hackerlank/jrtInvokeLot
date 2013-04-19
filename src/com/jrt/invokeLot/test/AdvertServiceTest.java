package com.jrt.invokeLot.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.jrt.invokeLot.service.AdvertService;

/**
 * 
 * @classname:   AdvertSercviceTest
 * @description: 广告测试
 * @author:      Administrator
 * @date:        2010-11-17 下午05:12:32
 *
 */
public class AdvertServiceTest {
   
	private static AdvertService advertService = new AdvertService();
	
	@Test
	public void getAdvertTest(){
		List list = advertService.getAdvert();
		Assert.assertEquals(5, list.size());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = advertService.getAdvert();
		System.out.println(list.size());

	}

}
