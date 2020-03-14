package net.xway.process.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ProcessNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("vm", new ProcessVMBeanDefinitionParser());
		registerBeanDefinitionParser("repository", new RepositoryBeanDefinitionParser());
	}

}
