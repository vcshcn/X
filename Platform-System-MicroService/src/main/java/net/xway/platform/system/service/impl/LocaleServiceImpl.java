package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.system.dao.ILocaleDAO;
import net.xway.platform.system.dto.Locale;
import net.xway.platform.system.service.ILocaleService;

@Service
public class LocaleServiceImpl implements ILocaleService {

	@Autowired
	private ILocaleDAO localeDAO;
	
	@Override
	public Locale findLocaleByLocaleID(int localeid) {
		return localeDAO.findByPrimaryKey(localeid);
	}

	@Override
	public List<Locale> findAllLocale() {
		return localeDAO.findAll();
	}


}
