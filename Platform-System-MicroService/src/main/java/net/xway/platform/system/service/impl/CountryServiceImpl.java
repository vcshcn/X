package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.system.dao.ICountryDAO;
import net.xway.platform.system.dto.Country;
import net.xway.platform.system.service.ICountryService;


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
