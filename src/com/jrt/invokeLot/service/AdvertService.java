package com.jrt.invokeLot.service;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.jrt.invokeLot.dao.AdvertDAO;
import com.jrt.invokeLot.dao.DaoConfig;
import com.jrt.invokeLot.model.Advert;

/**
 * 
 * @classname:   AdvertService
 * @description: 广告位图片地址及内容
 * @author:      Administrator
 * @date:        2010-11-17 下午04:23:36
 *
 */
public class AdvertService {
     private DaoManager daoManager;
     private AdvertDAO advertDAO;
     public AdvertService(){
    	 daoManager = DaoConfig.getDaoManager();
    	 advertDAO = (AdvertDAO)daoManager.getDao(AdvertDAO.class);
     }
     /**
      * 
      * @Title:  getAdvert
      * @Description: 查询所有广告位的图片链接
      * @return:  所有集合 
      * @exception:
      */
     public List<Advert> getAdvert(){
    	 return  advertDAO.getAdvert();
     }
}
