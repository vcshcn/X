package net.xway.platform.app.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.View;

public interface IViewDAO extends IBaseDAO<View> {

	public View findViewByName(String viewname);

}
