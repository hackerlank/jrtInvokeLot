package com.jrt.invokeLot.service;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.jrt.invokeLot.dao.DaoConfig;
import com.jrt.invokeLot.dao.NewsDAO;
import com.jrt.invokeLot.model.News;
import com.jrt.invokeLot.util.ListRange;


/**
 * 
 * @classname:   NewsService
 * @description: 新闻类的Service
 * @author:      徐丽
 * @date:        2010-11-17 
 *
 */
public class NewsService {
    private DaoManager daoManager;
	private NewsDAO newsDAO;
    
    public NewsService(){
    	daoManager = DaoConfig.getDaoManager();
    	newsDAO = (NewsDAO)daoManager.getDao(NewsDAO.class);
    }
    
    /**
     * 
     * @Title: getNewsCount 
     * @Description: 根据新闻类型查询共多少条新闻
     * @param: categoryId 新闻种类
	 * @param: type 代表web、wap
     * @return:   
     * @exception:
     */
    public int getNewsCount(Long categoryId, String type){
    	return newsDAO.getNewsCount(categoryId, type);
    }
    /**
	 * 
	 * @Title:  getNewsListDesc
	 * @Description: 根据种类和显示类型查询所有新闻的按时间降序排列
	 * @param: categoryId 新闻种类
	 * @param: type 代表web、wap
	 * @param: startRow 起始数
	 * @param: pageSize 每页记录条数
	 * @return:   集合
	 * @exception:
	 */
    public List<News> getNewsListByPubDateDesc(Long categoryId, String type,int startRow, int pageSize){
    	return newsDAO.getNewsListByPubDateDesc(categoryId, type,startRow, pageSize);
    }
    
    /**
	 * 
	 * @Title:  getNewsListDesc
	 * @Description: 根据种类和显示类型查询所有新闻的按时间升序排列
	 * @param: categoryId 新闻种类
	 * @param: type 代表web、wap
	 * @param: startRow 起始数
	 * @param: pageSize 每页记录条数
	 * @return:   集合
	 * @exception:
	 */
    public List<News> getNewsListByPubDateAsc(Long categoryId, String type,int startRow, int pageSize){
    	return newsDAO.getNewsListByPubDateAsc(categoryId, type,startRow, pageSize);
    }
    
    public ListRange<News> selectNewsListDesc(ListRange<News> range){
    	return newsDAO.selectNewsListDesc(range);
    }
    public ListRange<News> selectNewsListAsc(ListRange<News> range){
    	return newsDAO.selectNewsListAsc(range);
    }
    
    /**
     * 
     * @Title:  getNewsById
     * @Description: 根据id查询新闻
     * @param: id-新闻id
     * @param: type - 渠道号
     * @return:   
     * @exception:
     */
    public News getNewsById(Long id,String type){
    	return newsDAO.getNewsById(id, type);
    }
    
}
