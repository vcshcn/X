package net.xway.platform.app.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.ToolBox;

public interface IToolBoxDAO  extends IBaseDAO<ToolBox> {

	public List<ToolBox> findSelectedToolBoxByUserID(int userid);

}
