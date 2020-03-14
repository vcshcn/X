package net.xway.process.designer;

public enum NodeType {
	
	ProcessDefinition, 
	
	NoneStartEvent, 
	NoneEndEvnet, 
	
	UserTask, 
	
	ExclusiveGateway, 
	
	SequenceFlow;

	public boolean isStartEvent() {
		return NoneStartEvent.equals(this);
	}
	
	public boolean isEndEvent() {
		return NoneEndEvnet.equals(this);
	}
	
	public NodeAbstractType getAbstractType() {
		switch (this) {
			case NoneStartEvent:
			case NoneEndEvnet: return NodeAbstractType.Event;
			case ExclusiveGateway: return NodeAbstractType.Gateway;
			case SequenceFlow: return NodeAbstractType.Flow;
			default: 
		}
		
		throw new java.lang.UnsupportedOperationException();
	}
}
