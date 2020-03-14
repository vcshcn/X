package net.xway.platform.system.service;

import java.util.List;

import net.xway.platform.system.dto.ToolBox;

public interface IToolBoxService {
	
	public List<ToolBox> findAllToolBox();
	
	public List<ToolBox> findSelectedToolBoxByUserID(int userid);

	public ToolBox findToolBoxByToolBoxID(int toolboxid);
	
	public void saveUserToolBox(int userid, int[] toolboxid);
}
