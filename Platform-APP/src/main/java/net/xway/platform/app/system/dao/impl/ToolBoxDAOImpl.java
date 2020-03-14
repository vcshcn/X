package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IToolBoxDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Role;
import net.xway.platform.system.dto.ToolBox;

@Repository
public class ToolBoxDAOImpl implements IToolBoxDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	public List<ToolBox> findAll() {
		ResponseEntity<List<ToolBox>> tools = systemfeign.findAllToolBox();
		return tools.getBody();
	}
	
	@Override
	public List<ToolBox> findSelectedToolBoxByUserID(int userid) {
		ResponseEntity<List<ToolBox>> tools = systemfeign.findSelectedToolBoxByUserID(userid);
		return tools.getBody();
	}

}
