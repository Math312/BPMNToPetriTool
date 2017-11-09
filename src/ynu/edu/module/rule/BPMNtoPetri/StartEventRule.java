/*	StartEventRule.java  */

/**
 * Defines the rule of startevent to petri element.
 * @author 张豪
 */
 
package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.StartEvent;
import ynu.edu.module.petri.*;


public class StartEventRule extends AbstractRule {
	
	
	/**
	 * 判断Bpmn图中是否存在开始事件
	 * 若存在，则返回true,若不存在，则返回false
	 * @param graphics
	 * @return boolean
	 * 
	 */
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(StartEvent.class.getName());		
	}
	
	
	/**
	 * 将开始事件转换为petri网中的元素
	 * @param graphics, result
	 */
	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> startevent_nodes;
		if (matches(graphics)) {
			startevent_nodes = bpmn_nodes.get(StartEvent.class.getName());
		} 
		else {
			startevent_nodes = null;
		}
		if (startevent_nodes == null) {		// 如果不存在开始事件，则直接跳出方法
			return ;
		}
		else {
			for (String node : startevent_nodes) {
				/*		创建petri元素	 */
				StartEvent start_event = (StartEvent) graphics.getNodeData(node);
				String id = start_event.getId();
				String name = start_event.getName();
				start_event.setPlace(new Place("p" + place_id++, id));
				start_event.setTransition(new Transition(id, name));
				trans_id++;
				start_event.setArc(new Arc(start_event.getPlace().getId() + "to " + start_event.getTransition().getId()));
//				Place place = new Place("p" + place_id++, id);
//				Transition trans = new Transition(id, name);
//				Arc arc = new Arc(place.getId() + " to " + trans.getId());
				
				/*	添加结点 */
				result.addNode(start_event.getPlace());
				result.addNode(start_event.getArc());
				result.addNode(start_event.getTransition());
				
				
				/*	添加连接 */
				result.addLink(start_event.getPlace().getId(), start_event.getArc().getId());		// 建立place与arc的联系
				result.addLink(start_event.getArc().getId(), start_event.getTransition().getId());		// 建立arc与trans的联系
			}
		}
	}

}
