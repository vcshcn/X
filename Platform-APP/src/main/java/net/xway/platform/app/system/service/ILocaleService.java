package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.platform.system.dto.Locale;

public interface ILocaleService {
	
	public List<Locale> findAllLocale();
	
	public Locale findLocaleByLocaleID(int localeid);
}
