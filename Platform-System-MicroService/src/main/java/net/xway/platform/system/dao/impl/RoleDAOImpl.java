package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IRoleDAO;
import net.xway.platform.system.dto.Role;

@Repository
public class RoleDAOImpl extends BatisDAO<Role> implements IRoleDAO {

}
