package ynu.edu.module.rule.BPMNtoPetri;
/**
 * Define the exclusivegateway to petri.
 * @author Hao
 */

import java.util.*;
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
				/*	创建petri元素并保存其与bpmn的联系	 */
				ExclusiveGateway exclusive_gateway = (ExclusiveGateway)graphics.getNodeData(node);
				String id = exclusive_gateway.getId();
				String name = exclusive_gateway.getName();
				ArrayList<PetriElement> petris = new ArrayList<PetriElement>();
				Place place = new Place("p" + place_id++, id);
				petris.add(place);
				BpmnAndPetri e = new BpmnAndPetri(exclusive_gateway, petris);
				nodes.add(e);
				
				/* 添加结点 */
				result.addNode(place);
			}
		}
		
		
	}
}
