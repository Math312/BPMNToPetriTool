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
	 * 将Bpmn图中的结束事件提取出来，并用链表进行存储并返回
	 * @param graphics
	 * @return sequenceflow_nodes
	 */
//	@Override
//	protected LinkedList<String> split(Graphics<BpmnElement> graphics) {
//		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
//		LinkedList<String> endevent_nodes;
//		if (matches(graphics)) {
//			endevent_nodes = bpmn_nodes.get(EndEvent.class.getName());
//		}
//		else {
//			endevent_nodes = null;
//		}
//		return endevent_nodes;
//	}
	
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
				/*   创建petri元素  */
				Transition trans = new Transition("trans" + trans_id++);
				Arc arc = new Arc("arc" + arc_id++);
				Place place = new Place("p" + place_id++);
				
				/*	添加连接 */
				result.addLink(trans.getId(), arc.getId());
				result.addLink(arc.getId(), place.getId());
			}
		}
	}
	
}
