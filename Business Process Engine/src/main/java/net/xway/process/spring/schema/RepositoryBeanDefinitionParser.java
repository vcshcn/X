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

public class RepositoryBeanDefinitionParser implements  BeanDefinitionParser {

	private Log log = LogFactory.getLog(RepositoryBeanDefinitionParser.class);
	private Class<?> impl ;
	
	public RepositoryBeanDefinitionParser() {
		try (
			InputStream is = getClass().getResourceAsStream("/META-INF/process.properties");
		)
		{
			Properties p = new Properties();
			p.load(is);
			impl = Class.forName(p.getProperty("Repository.impl"));
		} 
		catch (IOException | ClassNotFoundException e) {
			log.error(e, e);
		}
	
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition def = new RootBeanDefinition();
		def.setBeanClass(impl);
		
		String id = element.getAttribute("id");
		if (id == null || id.length() == 0) {
			id = "Repository";
		}
		BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
		BeanDefinitionReaderUtils.registerBeanDefinition(idHolder,parserContext.getRegistry());
		
		return def;
	}



}
