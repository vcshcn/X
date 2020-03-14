package net.xway.code.generate.impl.java.model;

import java.util.HashSet;
import java.util.Set;

import net.xway.code.generate.impl.AbstractFile;
import net.xway.code.model.Project;

public abstract class AbstractJavaClass extends AbstractFile {

	protected final String humanName;
	protected final String packageName;
	protected final String className;
	protected final AbstractJavaClass extendsClass;
	protected final AbstractJavaClass interfaceClass;
	protected final Project project;
	protected final Set<AbstractJavaClass> imports = new HashSet<>();

	public AbstractJavaClass(String template, Project project, String humanName, String packageName, String className, AbstractJavaClass extendsClass, AbstractJavaClass interfaceClass) {
		super(template);
		this.humanName = humanName;
		this.packageName =  packageName;
		this.className = className;
		this.extendsClass = extendsClass;
		this.interfaceClass = interfaceClass;
		this.project = project;
	}
	
	@Override
	public String getFileName() {
		return className + ".java";
	}

	public String getPackageName() {
		return packageName;
	}

	public String getFullClassName() {
		return packageName + "." + className;
	}

	public String getClassName() {
		return className;
	}
	
	public String getHumanName() {
		return humanName;
	}
	
	public String getPropertyName() {
		return humanName.substring(0, 1).toLowerCase() + humanName.substring(1);
	}
	
	public String getUppercaseName() {
		return humanName.toUpperCase();
	}

	public String getLowerCaseName() {
		return humanName.toLowerCase();
	}

	public AbstractJavaClass getExtends() {
		return extendsClass;
	}

	public AbstractJavaClass getInterface() {
		return interfaceClass;
	}

	public boolean addImport(AbstractJavaClass klass) {
		return imports.add(klass);
	}
	
	public Set<AbstractJavaClass> getImports() {
		return imports;
	}
	
	public static AbstractJavaClass StringJavaClass = new AbstractJavaClass(null, null, "String", "java.lang", "String", null, null) {};
	public static AbstractJavaClass BooleanJavaClass = new AbstractJavaClass(null, null, "Boolean", "java.lang", "Boolean", null, null) {};
	public static AbstractJavaClass booleanJavaClass = new AbstractJavaClass(null, null, "boolean", null, "boolean", null, null) {};
	public static AbstractJavaClass DateJavaClass = new AbstractJavaClass(null, null, "Date", "java.util", "Date", null, null) {};
	public static AbstractJavaClass IntegerJavaClass = new AbstractJavaClass(null, null, "Integer", "java.lang", "Integer", null, null) {};
	public static AbstractJavaClass intJavaClass = new AbstractJavaClass(null, null, "int", null, "int", null, null) {};
	public static AbstractJavaClass ObjectJavaClass = new AbstractJavaClass(null, null, "Object", "java.lang", "Object", null, null) {};
}
