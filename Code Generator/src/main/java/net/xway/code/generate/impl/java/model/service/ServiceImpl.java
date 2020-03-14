package net.xway.code.generate.impl.java.model.service;

import java.util.List;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.dao.IDAO;
import net.xway.code.model.Project;

public class ServiceImpl extends AbstractJavaClass {

	public final static String SERVICEIMPL_TEMPLATE = "/META-INF/templates/serviceimpl.vm";

	private final List<IDAO> idaos;
	private final IService iservice;

	public ServiceImpl(Project project, String componentName, IService iservice, List<IDAO> idaos, String packageName) {
		super(SERVICEIMPL_TEMPLATE, project, componentName+"Service", packageName, componentName + "ServiceImpl", null, iservice);
		this.iservice = iservice;
		this.idaos = idaos;
	}

	public IService getIservice() {
		return iservice;
	}

	public List<IDAO> getIdaos() {
		return idaos;
	}
}
