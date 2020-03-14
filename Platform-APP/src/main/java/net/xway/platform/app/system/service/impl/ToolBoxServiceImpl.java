package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.IToolBoxDAO;
import net.xway.platform.app.system.service.IToolBoxService;
import net.xway.platform.system.dto.ToolBox;

@Service
public class ToolBoxServiceImpl implements IToolBoxService {

	@Autowired
	private IToolBoxDAO toolboxDAO;
	
	@Override
	public List<ToolBox> findAllToolBox() {
		return toolboxDAO.findAll();
	}
	
	@Override
	public List<ToolBox> findSelectedToolBoxByUserID(int userid) {
		return toolboxDAO.findSelectedToolBoxByUserID(userid);
	}

}
