package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.ICountryDAO;
import net.xway.platform.system.dto.Country;

@Repository
public class CountryDAOImpl extends BatisDAO<Country> implements ICountryDAO {

}
