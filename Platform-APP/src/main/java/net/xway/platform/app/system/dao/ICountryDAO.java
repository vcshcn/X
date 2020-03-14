package net.xway.platform.app.system.dao;


import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Country;

public interface ICountryDAO extends IBaseDAO<Country> {

	public Country findByPrimaryKey(int countryid) ;
	
	public List<Country> findAll();
}
