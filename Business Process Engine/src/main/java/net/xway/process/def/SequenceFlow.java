package net.xway.process.def;

import net.xway.process.IProcessInstance;
import net.xway.process.IProcessVM;
import net.xway.process.ProcessInstanceContext;

public class SequenceFlow extends AbstractFlow {

	@Override
	public boolean pass(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context) {
		return true;
	}


}
