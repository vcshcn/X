package net.xway.process.def;

import net.xway.process.IProcessDefinition;

public class ProcessDefinition extends AbstractNode implements IProcessDefinition {

	private Integer processDefinitionID;
	private int version;
	private AbstractStartEvent startEvent;
	
	public void setStartEvent(AbstractStartEvent startEvent) {
		this.startEvent = startEvent;
	}

	public AbstractStartEvent getStartEvent() {
		return startEvent;
	}

	public Integer getProcessDefinitionID() {
		return processDefinitionID;
	}

	public void setProcessDefinitionID(Integer processDefinitionID) {
		this.processDefinitionID = processDefinitionID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ProcessDefinition [processDefinitionID=" + processDefinitionID + ", key=" + getKey() + ", name=" + getName() + ", description=" + getDescription() + ", version=" + version
				+ ", startEvent=" + startEvent + "]";
	}

}
