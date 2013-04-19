package com.jrt.invokeLot.dao;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

/**
 * 
 * @classname:   DaoConfig
 * @description: 读取dao.xml
 * @author:      徐丽
 * @date:        2010-11-17
 *
 */
public class DaoConfig {

	private static final String resource = "com/jrt/invokeLot/dao/dao.xml";
	private static final DaoManager daoManager = newDaoManager(null);

	public static DaoManager getDaoManager() {
		return daoManager;
	}

	public static DaoManager newDaoManager(Properties props) {
		try {
			Resources.setCharset(Charset.forName("UTF-8"));
			Reader reader = Resources.getResourceAsReader(resource);
			return DaoManagerBuilder.buildDaoManager(reader, props);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not initialize DaoConfig.  Cause: " + e, e);
		}
	}
}
