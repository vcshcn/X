package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.ILocaleDAO;
import net.xway.platform.system.dto.Locale;

@Repository
public class LocaleDAOImpl extends BatisDAO<Locale> implements ILocaleDAO {

}
