package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.system.dao.IToolBoxDAO;
import net.xway.platform.system.dto.ToolBox;
import net.xway.platform.system.service.IToolBoxService;

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

	@Override
	public ToolBox findToolBoxByToolBoxID(int toolboxid) {
		return toolboxDAO.findByPrimaryKey(toolboxid);
	}

	@Override
	public void saveUserToolBox(int userid, int[] toolboxid) {
		toolboxDAO.deleteToolBoxByUserID(userid);
		toolboxDAO.insertUserToolBox(userid, toolboxid);
	}

}
