package ynu.edu.module.rule.BPMNtoPetri;
/**
 * Define the rule of EventBasedGateway to petri.
 * @author Hao
 */

import java.util.*;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.*;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Place;
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
				/*	创建petri元素并保存其与bpmn的联系	 */
				EventBasedGateway eventbased_gateway = (EventBasedGateway)graphics.getNodeData(node);
				String id = eventbased_gateway.getId();
				String name = eventbased_gateway.getName();
				ArrayList<PetriElement> petris = new ArrayList<>();
				Place place = new Place("p" + place_id++, id);
				petris.add(place);
				BpmnAndPetri e = new BpmnAndPetri(eventbased_gateway, petris);
				nodes.add(e);
				
				/* 添加结点 */
				result.addNode(place);
			}
		}
	}
	
}	
		
