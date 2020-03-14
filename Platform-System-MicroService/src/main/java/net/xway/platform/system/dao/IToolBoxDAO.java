package net.xway.platform.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.ToolBox;

public interface IToolBoxDAO  extends IBaseDAO<ToolBox> {

	public List<ToolBox> findSelectedToolBoxByUserID(int userid);
	
	public int deleteToolBoxByUserID(int userid);
	
	public int insertUserToolBox(int userid, int[] toolboxids);
}
