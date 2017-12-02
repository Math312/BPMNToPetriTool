/*	EndEventRule.java  */

/**
 * Define the rule of endevent to petri element.
 * @author Hao
 */


package ynu.edu.module.rule.BPMNtoPetri;

import java.util.*;
import ynu.edu.data.Graphics;
import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;

public class EndEventRule extends AbstractRule {
	
	/**
	 * 判断Bpmn图中是否存在结束事件
	 * 若存在，则返回true,若不存在，则返回false
	 * @param graphics
	 * @return boolean
	 * 
	 */
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(EndEvent.class.getName());	
	}
	
	/**
	 * 将结束事件转换为petri网中的元素
	 * @param graphics, result
	 * 
	 */
	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> endevent_nodes;
		if (matches(graphics)) {
			endevent_nodes = bpmn_nodes.get(EndEvent.class.getName());
		}
		else {
			endevent_nodes = null;
		}
		if (endevent_nodes == null) {
			return ;
		}
		else {
			for (String node : endevent_nodes) {
				EndEvent end_event = (EndEvent) graphics.getNodeData(node);
				String id = end_event.getId();
				String name = end_event.getName();
				ArrayList<PetriElement> petris = new ArrayList<>();
				/*   创建petri元素并保存其与bpmn元素之间的联系  */
				Transition transition = new Transition(id, name);
				Place place = new Place("p" + place_id++, id);
				Arc arc = new Arc(transition.getId() + " to " + "p" + (place_id - 1));
				petris.add(transition);
				petris.add(arc);
				petris.add(place);
				BpmnAndPetri e = new BpmnAndPetri(end_event, petris);
				nodes.add(e);
			
				/*	添加结点 */		
				result.addNode(transition);
				result.addNode(arc);
				result.addNode(place);
				
				/*	添加在Petri图上的连接 */
				result.addLink(transition.getId(), arc.getId());
				result.addLink(arc.getId(), place.getId());
			}
		}
	}
	
}
