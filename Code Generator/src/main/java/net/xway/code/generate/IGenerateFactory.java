package net.xway.code.generate;

import net.xway.code.model.Group;

public interface IGenerateFactory {

	public String getID() ;
	
	public void generate(Group group) throws Exception;
}
