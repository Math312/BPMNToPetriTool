package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.ArrayElement;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.petri.*;


public class ExclusiveGatewayRule2 extends AbstractRule {

	@Override
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
				String preNodeID = graphics.getIDbyNode(node)[1][0];
				String nextNodeID = graphics.getIDbyNode(node)[0][0];
				if (exclusive_gateway.getInComingSize() > 1) {
					exclusive_gateway.setTransition1(new Transition(id, name + '1'));
					exclusive_gateway.setTransition2(new Transition(id, name + '2'));
					exclusive_gateway.setPlace(new Place("p" + place_id++, id));
					exclusive_gateway.setArc1(new Arc(exclusive_gateway.getTransition1().getId() + " to " + exclusive_gateway.getPlace().getId()));
					exclusive_gateway.setArc2(new Arc(exclusive_gateway.getTransition2().getId() + " to " + exclusive_gateway.getPlace().getId()));
					
					result.addNode(exclusive_gateway.getTransition1());
					result.addNode(exclusive_gateway.getTransition2());
					result.addNode(exclusive_gateway.getPlace());
					result.addNode(exclusive_gateway.getArc1());
					result.addNode(exclusive_gateway.getArc2());
					
//					result.addLink(exclusive_gateway.getTransition1().getId(), exclusive_gateway.getPlace().getId());
//					result.addLink(exclusive_gateway.getTransition2().getId(), exclusive_gateway.getPlace().getId());
				}
				if (exclusive_gateway.getOutGoingSize() > 1) {
					exclusive_gateway.setTransition1(new Transition(id, name + '1'));
					exclusive_gateway.setTransition2(new Transition(id, name + '2'));
					exclusive_gateway.setPlace(new Place("p" + place_id++, id));
					exclusive_gateway.setArc1(new Arc(exclusive_gateway.getPlace().getId() + " to " + exclusive_gateway.getTransition1().getId()));
					exclusive_gateway.setArc2(new Arc(exclusive_gateway.getPlace().getId() + " to " + exclusive_gateway.getTransition2().getId()));
					
					result.addNode(exclusive_gateway.getTransition1());
					result.addNode(exclusive_gateway.getTransition2());
					result.addNode(exclusive_gateway.getPlace());
					result.addNode(exclusive_gateway.getArc1());
					result.addNode(exclusive_gateway.getArc2());
					
				
					
				}
			}
		}
	}
	
}
