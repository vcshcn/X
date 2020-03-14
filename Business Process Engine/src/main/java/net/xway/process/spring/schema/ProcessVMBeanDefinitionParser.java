package net.xway.process.spring.schema;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ProcessVMBeanDefinitionParser implements  BeanDefinitionParser {

	private Log log = LogFactory.getLog(ProcessVMBeanDefinitionParser.class);
	
	private Class<?> vmimpl ;
	
	public ProcessVMBeanDefinitionParser() {
		try (
			InputStream is = getClass().getResourceAsStream("/META-INF/process.properties");
		)
		{
			Properties p = new Properties();
			p.load(is);
			vmimpl = Class.forName(p.getProperty("ProcessVM.impl"));
		} 
		catch (IOException | ClassNotFoundException e) {
			log.error(e, e);
		}
	
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition def = new RootBeanDefinition();
		def.setBeanClass(vmimpl);
		
		String id = element.getAttribute("id");
		if (id == null || id.length() == 0) {
			id = "ProcessVM";
		}
		BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
		BeanDefinitionReaderUtils.registerBeanDefinition(idHolder,parserContext.getRegistry());
		
		String name = element.getAttribute("name");
		if (name != null && name.length() != 0) {
			def.getConstructorArgumentValues().addGenericArgumentValue(name);
		}
		
		return def;
	}



}
