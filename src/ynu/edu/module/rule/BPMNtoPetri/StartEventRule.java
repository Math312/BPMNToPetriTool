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
	 * 将Bpmn图中的开始事件提取出来，并用链表进行存储并返回
	 * @param graphics
	 * @return startevent_nodes
	 */
//	@Override
//	protected LinkedList<String> split(Graphics<BpmnElement> graphics) {
//		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
//		LinkedList<String> startevent_nodes;
//		if (matches(graphics)) {					// 如果存在开始事件，则返回含有所有开始事件id的链表
//			startevent_nodes = bpmn_nodes.get(StartEvent.class.getName());
//		}
//		else {										// 如果不存在，则返回null
//			startevent_nodes = null;	
//		}
//		return startevent_nodes;	
//	}
	
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
				Place place = new Place("p" + place_id++, id);
				Arc arc = new Arc("arc" + arc_id++);
				Transition trans = new Transition("trans" + trans_id++, name);
				
				/*	添加结点 */
				result.addNode(place);
				result.addNode(arc);
				result.addNode(trans);
				
				/*	添加连接 */
				result.addLink(place.getId(), arc.getId());		// 建立place与arc的联系
				result.addLink(arc.getId(), trans.getId());		// 建立arc与trans的联系
			}
		}
	}

}
