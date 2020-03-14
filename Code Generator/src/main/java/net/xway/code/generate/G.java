package net.xway.code.generate;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.xway.code.generate.impl.java.JavaGenerateFactory;
import net.xway.code.generate.impl.sql.SQLGenerateFactory;
import net.xway.code.generate.impl.webapp.WebappGenerateFactory;
import net.xway.code.model.Group;

public class G {

	private static HashMap<String, IGenerateFactory> factorys = new HashMap<>(); 
	static {
		registerFactory(new JavaGenerateFactory());
		registerFactory(new WebappGenerateFactory());
		registerFactory(new SQLGenerateFactory());
	}
	
	public static IGenerateFactory registerFactory(IGenerateFactory factory) {
		return factorys.put(factory.getID(), factory);
	}
	
	public static IGenerateFactory getGenerateFactory(String id) {
		return factorys.get(id);
	}
	
	public static void g(String factoryid, Group group, PrintStream out)  {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		PrintStream bOut = System.out;
		PrintStream bErr = System.err;
		
		
		try {
			System.setOut(out);
			System.setErr(out);
			
			System.out.println("-- start on " + sdf.format(new Date()));

			IGenerateFactory g = getGenerateFactory(factoryid);
			if (g == null) {
				throw new java.lang.UnsupportedOperationException("unregister factory of '" + factoryid + "'");
			}
			
			g.generate(group);
			
			System.out.println();
			System.out.println("-- success finish on " + sdf.format(new Date()));
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		finally {
			System.setOut(bOut);
			System.setErr(bErr);
		}
	}
}
