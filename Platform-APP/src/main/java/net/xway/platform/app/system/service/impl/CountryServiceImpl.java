package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.ICountryDAO;
import net.xway.platform.app.system.service.ICountryService;
import net.xway.platform.system.dto.Country;


@Service
public class CountryServiceImpl implements ICountryService {

	@Autowired
	private ICountryDAO countryDAO;
	
	@Override
	public Country findCountryByCountryID(int countryid) {
		return countryDAO.findByPrimaryKey(countryid);
	}

	@Override
	public List<Country> findAllCountry() {
		return countryDAO.findAll();
	}

}
