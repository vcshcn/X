package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.ILocaleDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Locale;

@Repository
public class LocaleDAOImpl implements ILocaleDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public List<Locale> findAll() {
		ResponseEntity<List<Locale>> entity = systemfeign.findAllLocale();
		return entity.getBody();
	}

}
