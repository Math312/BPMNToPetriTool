package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.*;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Transition;

public class EventBasedGatewayRule extends AbstractRule {

	
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(EventBasedGateway.class.getName());
	}

	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> eventBasedGateway_nodes;
		if (matches(graphics)) {
			eventBasedGateway_nodes = bpmn_nodes.get(EventBasedGateway.class.getName());
		} else {
			eventBasedGateway_nodes = null;
		}
		if (eventBasedGateway_nodes == null) {		// 如果不存在EventBasedGateway，则直接跳出方法
			return ;
		} else {
			for (String node : eventBasedGateway_nodes) {
				EventBasedGateway eventbased_gateway = (EventBasedGateway)graphics.getNodeData(node);
				String id = eventbased_gateway.getId();
				String name = eventbased_gateway.getName();
				Transition trans = new Transition(id, name);
				trans_id++;
				result.addNode(trans);
			}
		}
	}
	
}	
		
