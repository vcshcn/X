package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.ILocaleDAO;
import net.xway.platform.app.system.service.ILocaleService;
import net.xway.platform.system.dto.Locale;

@Service
public class LocaleServiceImpl implements ILocaleService {

	@Autowired
	private ILocaleDAO localeDAO;
	
	@Override
	public List<Locale> findAllLocale() {
		return localeDAO.findAll();
	}


	@Override
	public Locale findLocaleByLocaleID(int localeid) {
		return localeDAO.findByPrimaryKey(localeid);
	}


}
