package net.xway.code.generate.impl.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xway.code.generate.Env;
import net.xway.code.generate.impl.AbstractGenerate;
import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.JavaField;
import net.xway.code.generate.impl.java.model.action.AddStruts2Action;
import net.xway.code.generate.impl.java.model.action.DeleteStruts2Action;
import net.xway.code.generate.impl.java.model.action.ListStruts2Action;
import net.xway.code.generate.impl.java.model.action.SaveStruts2Action;
import net.xway.code.generate.impl.java.model.action.Struts2Action;
import net.xway.code.generate.impl.java.model.action.Struts2XML;
import net.xway.code.generate.impl.java.model.action.ViewStruts2Action;
import net.xway.code.generate.impl.java.model.dao.DAOImpl;
import net.xway.code.generate.impl.java.model.dao.IDAO;
import net.xway.code.generate.impl.java.model.dao.MyBatisXML;
import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.generate.impl.java.model.service.ServiceImpl;
import net.xway.code.generate.impl.java.model.service.SpringXML;
import net.xway.code.model.Component;
import net.xway.code.model.Field;
import net.xway.code.model.Group;
import net.xway.code.model.Project;
import net.xway.code.model.type.BooleanTimeType;
import net.xway.code.model.type.ComponentType;
import net.xway.code.model.type.DateTimeType;
import net.xway.code.model.type.DateType;
import net.xway.code.model.type.IntegerType;
import net.xway.code.model.type.NameType;
import net.xway.code.model.type.PrimaryKeyType;
import net.xway.code.model.type.StringType;

public class JavaGenerate extends AbstractGenerate {

	Map<Component, DTO> cds = new HashMap<>();

	private List<DTO> getComponentDTO(List<DTO> dtos, Component component) {
		List<Component> components = getComponentAndAllSubComponents(component);		
		List<DTO> r = new ArrayList<>();
		
		for (DTO dto : dtos) {
			for (Component c : components) {
				if (dto.getComponent() == c) {
					r.add(dto);
					break ;
				}
			}
		}
		return r;
	}
	
	private List<DTO> getGroupDTO(Group group) {
		List<DTO> dtos = new ArrayList<>();
		for (Project project : group.getProjects()) {
			for (Component component : project.getComponents()) {
				List<Component> components = getComponentAndAllSubComponents(component);
				for (Component c : components) {
					DTO dto = new DTO( project, c, getDTOPackageName(project, component));
					dtos.add(dto);
					cds.put(c, dto);
				}
			}
		}
		return dtos;
	}
	
	@Override
	public void generate(Group group, Project project, Component component) throws IOException {
		// collection 
		List<DTO> alldto = getGroupDTO(group);
		List<DTO> dtos = getComponentDTO(alldto, component);
		List<IDAO> daos = new ArrayList<>();
		List<DAOImpl> daoimpls = new ArrayList<>();
		List<Struts2Action> actions = new ArrayList<>();

		// generate fields
		for (DTO dto : alldto) {
			List<JavaField> jfs = new ArrayList<>();
			for (Field field : dto.getComponent().getFields()) {
				jfs.add(makeJavaField(field, cds) );
			}
			
			jfs.add(makeJavaField(Field.CreatedTime, cds) );
			jfs.add(makeJavaField(Field.ModifiedTime, cds) );
			dto.setJavaFields(jfs);
		}

		// generate dao
		for (DTO dto : dtos) {
			IDAO iDAO = new IDAO(project, dto, getDAOPackageName(project, component));
			DAOImpl dAOImpl = new DAOImpl(project, iDAO, getDAOImplPackageName(project, component));
			daos.add(iDAO);
			daoimpls.add(dAOImpl);
		}

		// generate service
		IService iService = new IService(project, component.getName(), dtos, getServicePackageName(project, component));
		
		ServiceImpl serviceImpl = new ServiceImpl(project, component.getName(), iService, daos, getServiceImplPackageName(project, component));
		
		// generate action
		for (DTO dto : dtos) {
			if (dto.getComponent().isGenerate() == false) {
				continue ;
			}
			AddStruts2Action addAction = new AddStruts2Action(project, iService, dto, getActionPackageName(project, component));
			actions.add(addAction);
			
			ListStruts2Action listAction = new ListStruts2Action(project, iService, dto, getActionPackageName(project, component));
			actions.add(listAction);
			
			ViewStruts2Action viewAction = new ViewStruts2Action(project, iService, dto, getActionPackageName(project, component));
			actions.add(viewAction);
			
			DeleteStruts2Action deleteAction = new DeleteStruts2Action(project, iService,  dto, getActionPackageName(project, component));
			actions.add(deleteAction);
			
			SaveStruts2Action saveAction = new SaveStruts2Action(project, iService, dto, getActionPackageName(project, component));
			actions.add(saveAction);
		}

		// write dto 
		for (DTO dto : dtos) {
			if (dto.getComponent().isGenerate()) {
				File dtoDirectory = Env.getComponentJavaClassDirectory(dto.getPackageName());
				writeToFile(dto, dtoDirectory);
			}
		}
		
		// write dao
		File daoDirectory = Env.getComponentJavaClassDirectory(getDAOPackageName(project, component));
		File daoImplDirectory = Env.getComponentJavaClassDirectory(getDAOImplPackageName(project, component));
		for (IDAO dao : daos) {
			if (dao.getDto().getComponent().isGenerate()) {
				writeToFile(dao, daoDirectory);
			}
		}
		for (DAOImpl impl : daoimpls) {
			if (impl.getIdao().getDto().getComponent().isGenerate()) {
				writeToFile(impl, daoImplDirectory);
			}
		}

		// write iservice
		File serviceDirectory = Env.getComponentJavaClassDirectory(getServicePackageName(project, component));
		writeToFile(iService, serviceDirectory);

		// write serviceimple
		File serviceImplDirectory = Env.getComponentJavaClassDirectory(getServiceImplPackageName(project, component));
		writeToFile(serviceImpl, serviceImplDirectory);

		// write actions
		File directory = Env.getComponentJavaClassDirectory(getActionPackageName(project, component));
		for (Struts2Action action : actions) {
			writeToFile(action, directory);			
		}
		
		// generate action xml
		File configDirectory = Env.getComponentJavaClassDirectory(project.getPackageName());
		
		Struts2XML struts2Xml = new Struts2XML(component.getName(), actions);
		writeToFile(struts2Xml, configDirectory);

		// generate spring xml
//		SpringXML springXml = new SpringXML(component.getName(), actions, serviceImpl, daos);
//		writeToFile(springXml, configDirectory);
		
		// generate mybatis xml
		for (DAOImpl dao : daoimpls) {
			MyBatisXML mybatisXml = new MyBatisXML(dao.getIdao());
			writeToFile(mybatisXml, configDirectory);
		}
		
	}

	private JavaField makeJavaField(Field field, Map<Component, DTO> cds) {
		if (field.getType() instanceof ComponentType) {
			DTO type = cds.get(field.getReference());
			return  new JavaField(field, type);
		}
		else if (field.getType() instanceof StringType || field.getType() instanceof NameType) {
			return new JavaField(field, AbstractJavaClass.StringJavaClass);
		}
		else if (field.getType() instanceof BooleanTimeType) {
			if (field.isNotNull()) {
				return new JavaField(field, AbstractJavaClass.booleanJavaClass);
			}
			else {
				return new JavaField(field, AbstractJavaClass.BooleanJavaClass);
			}
		}
		else if (field.getType() instanceof DateTimeType || field.getType() instanceof DateType) {
			return new JavaField(field,AbstractJavaClass.DateJavaClass );
		}
		else if (field.getType() instanceof PrimaryKeyType) {
			return new JavaField(field, AbstractJavaClass.IntegerJavaClass);
		}
		else if (field.getType() instanceof IntegerType) {
			if (field.isNotNull()) {
				return new JavaField(field, AbstractJavaClass.intJavaClass);
			}
			else {
				return new JavaField(field, AbstractJavaClass.IntegerJavaClass);
			}
		}
		
		return new JavaField(field, AbstractJavaClass.ObjectJavaClass);
	}

	public String getDTOPackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName() + ".dto";
	}
	
	public String getActionPackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName()  + ".action";
	}
	
	public String getServicePackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName() + ".service";
	}
	
	public String getServiceImplPackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName() + ".service.impl";
	}
	
	public String getDAOPackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName() + ".dao";
	}
	
	public String getDAOImplPackageName(Project project, Component component) {
		return project.getPackageName() + "." + component.getName() + ".dao.impl";
	}


}
