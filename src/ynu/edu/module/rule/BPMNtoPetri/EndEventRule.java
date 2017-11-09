/*	EndEventRule.java  */

/**
 * Defines the rule of endevent to petri element.
 * @author 张豪
 */


package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;
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
				/*   创建petri元素  */
//				Transition trans = new Transition(id, name);
//				trans_id++;
//				Place place = new Place("p" + place_id++, id);
//				Arc arc = new Arc(trans.getId() + " to " + place.getId());
				
				end_event.setTransition(new Transition(id, name));
				trans_id++;
				end_event.setPlace(new Place("p" + place_id++, id));
				end_event.setArc(new Arc(end_event.getId() + "to" + end_event.getPlace().getId()));
				
				
				/*	添加结点 */
//				result.addNode(trans);
//				result.addNode(arc);
//				result.addNode(place);
				
				result.addNode(end_event.getTransition());
				result.addNode(end_event.getArc());
				result.addNode(end_event.getPlace());
				
				/*	添加连接 */
//				result.addLink(trans.getId(), arc.getId());
//				result.addLink(arc.getId(), place.getId());
				
				result.addLink(end_event.getTransition().getId(), end_event.getArc().getId());
				result.addLink(end_event.getArc().getId(), end_event.getPlace().getId());
			}
		}
	}
	
}
