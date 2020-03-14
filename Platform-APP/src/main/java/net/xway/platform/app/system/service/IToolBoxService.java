package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.platform.system.dto.ToolBox;

public interface IToolBoxService {
	
	public List<ToolBox> findAllToolBox();
	
	public List<ToolBox> findSelectedToolBoxByUserID(int userid);
}
