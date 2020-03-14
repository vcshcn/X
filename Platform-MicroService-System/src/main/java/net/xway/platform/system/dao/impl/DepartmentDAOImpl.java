package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IDepartmentDAO;
import net.xway.platform.system.dto.Department;

@Repository
public class DepartmentDAOImpl extends BatisDAO<Department> implements IDepartmentDAO {

}
