package com.jrt.invokeLot.service;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.jrt.invokeLot.dao.CategoryDAO;
import com.jrt.invokeLot.dao.DaoConfig;
import com.jrt.invokeLot.model.Category;

/**
 * 
 * @classname: CategoryService
 * @description: 新闻分类的service
 * @author: 徐丽
 * @date: 2010-11-17 下午01:13:44
 * 
 */
public class CategoryService {
	private DaoManager daoManager;
	private CategoryDAO categoryDAO;

	public CategoryService() {
		daoManager = DaoConfig.getDaoManager();
		categoryDAO = (CategoryDAO) daoManager.getDao(CategoryDAO.class);
	}
	
	/**
	 * 
	 * @Title:  getCategoryByType
	 * @Description: 根据新闻类型查询同类新闻
	 * @param: type 类型
	 * @return:   
	 * @exception:
	 */
	public List<Category> getCategoryByType(String type,int categoryType) {
		return categoryDAO.getCategoryByType(type,categoryType);
	}
	
	/**
	 * 
	 * @Title:  selectCategory
	 * @Description: 查询web网站上面所有新闻
	 * @param: type-渠道类型
	 * @return:   
	 * @exception:
	 */
	public List<Category> selectCategoryByType(String type) {
		return categoryDAO.selectCategoryByType(type);
	}
	
	/**
	 * 
	 * @Title:  selectCategoryByTypeAndId
	 * @Description: 根据id查询新闻类型
	 * @param: type-渠道类型
	 * @param: id-具体新闻种类id 
	 * @return:   
	 * @exception:
	 */
	public Category selectCategoryByTypeAndId(String type, Long id){
		return categoryDAO.selectCategoryByTypeAndId(type, id);
	}
}
