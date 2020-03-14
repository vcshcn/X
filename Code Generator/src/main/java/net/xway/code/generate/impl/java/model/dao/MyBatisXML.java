package net.xway.code.generate.impl.java.model.dao;

import net.xway.code.generate.impl.AbstractFile;

public class MyBatisXML extends AbstractFile {

	public final static String MYBATIS_XML_TEMPLATE = "/META-INF/templates/mybatisxml.vm";

	private final IDAO dao;
	
	public MyBatisXML(IDAO dao) {
		super(MYBATIS_XML_TEMPLATE);
		this.dao = dao;
	}

	@Override
	public String getFileName() {
		return "mybatis-" + dao.getDto().getName().toLowerCase() + ".xml";
	}

	public IDAO getDao() {
		return dao;
	}
	
}
