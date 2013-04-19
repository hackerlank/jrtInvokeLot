package com.jrt.invokeLot.dao.impl;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.jrt.invokeLot.dao.AdvertDAO;
import com.jrt.invokeLot.model.Advert;

public class AdvertDAOImpl extends SqlMapDaoTemplate implements AdvertDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public AdvertDAOImpl(DaoManager daoManager) {
        super(daoManager);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public int deleteByPrimaryKey(String id) {
        Advert key = new Advert();
        key.setId(id);
        int rows = delete("Advert.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public void insert(Advert record) {
        insert("Advert.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public void insertSelective(Advert record) {
        insert("Advert.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public Advert selectByPrimaryKey(String id) {
        Advert key = new Advert();
        key.setId(id);
        Advert record = (Advert) queryForObject("Advert.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public int updateByPrimaryKeySelective(Advert record) {
        int rows = update("Advert.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table Advert
     *
     * @ibatorgenerated Wed Nov 17 16:14:31 CST 2010
     */
    public int updateByPrimaryKey(Advert record) {
        int rows = update("Advert.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	/**
	 * 查询首页上广告位的图片
	 */
	public List<Advert> getAdvert() {
		return (List<Advert>)queryForList("Advert.getAdvert");
	}
}