package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IViewDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.View;

@Repository
public class ViewDAOImpl implements IViewDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public View findViewByName(String viewname) {
		ResponseEntity<View> view = systemfeign.findViewByName(viewname);
		return view.getBody();
	}


	
}
