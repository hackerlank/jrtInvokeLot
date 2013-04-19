package com.jrt.invokeLot.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jrt.invokeLot.model.News;
import com.jrt.invokeLot.service.NewsService;
import com.jrt.invokeLot.util.ListRange;

//新闻测试类
@SuppressWarnings("unchecked")
public class NewsSercviceTest {
    private static NewsService newsService = new NewsService();
    
	@Test
    public void selectNewsList(){
    	ListRange range = new ListRange();// 分页对象
    	range.setStartrow(20);
    	range.setPageSize(5);
		Map where = new HashMap();
		where.put("categoryId", 1);
		where.put("type", "web");
	    range.setQuery((Serializable) where);
	    range =newsService.selectNewsListDesc(range);
	    System.out.println(((News)range.getRecords().get(0)).getId());
    }
    
    @Test
    public void getNewsListAscTest (){
       List<News> newsList =newsService.getNewsListByPubDateAsc(5L, "web", 0, 20);	
       for(int i=0;i<newsList.size();i++){
  		 News news =(News)newsList.get(i);
  		 System.out.println(news.getTitle());
       }
    }
    
    @Test
    public void getNewsListDescTest (){
       List<News> list =newsService.getNewsListByPubDateDesc(5L, "web", 0, 20);	
       System.out.println(list.size());
       System.out.println(((News)list.get(0)).toString());
       
    }
    
    
    
	public static void main(String[] args) {

		ListRange range = new ListRange();// 分页对象
		range.setStartrow(20);
    	range.setPageSize(5);
		Map where = new HashMap();
		where.put("categoryId", 1);
		where.put("type", "web");
	    range.setQuery((Serializable) where);
	    range =newsService.selectNewsListDesc(range);
	    System.out.println(range.getRecords().size());
	}

}
