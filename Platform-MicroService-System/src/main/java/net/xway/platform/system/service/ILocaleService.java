package net.xway.platform.system.service;

import java.util.List;

import net.xway.platform.system.dto.Locale;

public interface ILocaleService {

	public Locale findLocaleByLocaleID(int localeid);
	
	public List<Locale> findAllLocale();
}
