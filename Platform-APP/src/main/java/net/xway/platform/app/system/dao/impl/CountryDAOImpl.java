package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.ICountryDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Country;


@Repository
public class CountryDAOImpl implements ICountryDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public Country findByPrimaryKey(int countryid) {
		ResponseEntity<Country> entity = systemfeign.findCountryByCountryID(countryid);
		return entity.getBody();
	}

	@Override
	public List<Country> findAll() {
		ResponseEntity<List<Country>> entity = systemfeign.findAllCountry();
		return entity.getBody();
	}

}
