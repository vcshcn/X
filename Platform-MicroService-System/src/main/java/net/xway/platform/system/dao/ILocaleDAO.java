package net.xway.platform.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Locale;

public interface ILocaleDAO  extends IBaseDAO<Locale> {

	public List<Locale> findAll();
}
