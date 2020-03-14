package net.xway.code.generate;

import java.io.File;

import net.xway.code.config.Config;
import net.xway.code.model.Project;

public class Env {

	public static File getBaseDirectory() {
		return new File(Config.getInstance().getBaseDirectory());
	}
	
	public static File getBaseSourceDirectory() {
		return new File(Config.getInstance().getJavaDirectory());
	}
	
	public static File getBaseWebappDirectory() {
		return new File(Config.getInstance().getWebDirectory());
	}

	public static File getBaseSQLDirectory() {
		return new File(Config.getInstance().getSqlDirectory());
	}
	
	public static File getBaseDocDirectory() {
		return new File(Config.getInstance().getDocDirectory());
	}

	public static File getComponentJavaClassDirectory(String subPackageName) {
		return new File(getBaseSourceDirectory(), subPackageName.replace(".", File.separator));
	}
	
	/**
	 * convert package name to disk directory
	 * @param project
	 * @return
	 */
	public static String getPackageDirectory(Project project) {
		return project.getPackageName().replace(".", java.io.File.separator).toLowerCase();
	}

}
