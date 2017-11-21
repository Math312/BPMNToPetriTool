package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Place;
import ynu.edu.module.petri.Transition;

public class ExclusiveGatewayRule extends AbstractRule {
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(ExclusiveGateway.class.getName());
	}

	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> exclusivegateway_nodes;
		if (matches(graphics)) {
			exclusivegateway_nodes = bpmn_nodes.get(ExclusiveGateway.class.getName());
		} else {
			exclusivegateway_nodes = null;
		}
		if (exclusivegateway_nodes == null) {		// 如果不存在Exclusive gateway，则直接跳出方法
			return ;
		} else {
			for (String node : exclusivegateway_nodes) {
				ExclusiveGateway exclusive_gateway = (ExclusiveGateway)graphics.getNodeData(node);
				String id = exclusive_gateway.getId();
				String name = exclusive_gateway.getName();
//				Transition trans = new Transition(id, name);
				exclusive_gateway.setPlace(new Place("p" + place_id++, id));
				result.addNode(exclusive_gateway.getPlace());
			}
		}
		
		
	}
}
