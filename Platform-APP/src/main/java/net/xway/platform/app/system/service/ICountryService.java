package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.platform.system.dto.Country;

public interface ICountryService {

	public Country findCountryByCountryID(int countryid);
	
	public List<Country> findAllCountry();
}
