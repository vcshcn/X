package net.xway.code.generate.impl.java.model.dao;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.model.Project;

public class DAOImpl extends AbstractJavaClass {

	public final static String DAOIMPL_TEMPLATE = "/META-INF/templates/daoimpl.vm";

	private final IDAO idao;
	
	public DAOImpl(Project project, IDAO idao, String packageName) {
		super(DAOIMPL_TEMPLATE, project, idao.getDto().getName(), packageName, idao.getDto().getName() + "DAOImpl", null, idao);
		this.idao = idao;
	}

	public IDAO getIdao() {
		return idao;
	}

}
