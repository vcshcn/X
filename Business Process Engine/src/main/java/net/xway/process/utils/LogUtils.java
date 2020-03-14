package net.xway.process.utils;

import net.xway.process.INode;
import net.xway.process.IProcessInstance;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.def.NoneEndEvent;
import net.xway.process.def.NoneStartEvent;
import net.xway.process.def.SequenceFlow;
import net.xway.process.def.UserTask;

public class LogUtils {

	public static String formatLog(IProcessInstance instance, ProcessInstanceContext context, INode node) {
		String action = null;
		String pos = null;
		if (node instanceof NoneStartEvent) {
			action = "trigger";
			pos = "NoneStartEvent";
		}
		else if (node instanceof NoneEndEvent) {
			action = "trigger";
			pos = "NoneStartEvent";
		}
		else if (node instanceof UserTask) {
			action = "execute";
			pos = "UserTask";
		}
		else if (node instanceof SequenceFlow) {
			action = "pass";
			pos = "SequenceFlow";
		}
		return ">>>" + instance.getProcessDefinition().getName() + " " + action + " " + pos + " " + node.getKey();
	}
}
