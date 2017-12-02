/*	IntermediateEventRule.java  */

/**
 * Define the rule of IntermediateEvent to petri element.
 * @author Hao
 */


package ynu.edu.module.rule.BPMNtoPetri;

import java.util.*;

import ynu.edu.data.Graphics;
import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;

public class IntermediateEventRule extends AbstractRule {
	/**
	 * 判断Bpmn图中是否存在中间事件
	 * 若存在，则返回true,若不出在，则返回false
	 * @param graphics
	 * @return boolean
	 */
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(IntermediateThrowEvent.class.getName());		
	}
	
	/**
	 * 将中间事件转换为petri网中的元素
	 * @param graphics, result
	 * 
	 */
	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> InterEvent_nodes;
		if (matches(graphics)) {		//	如果存在中间事件，则返回含有所有序列流id的链表
			InterEvent_nodes = bpmn_nodes.get(StartEvent.class.getName());
		}
		else {							//  如果不存在, 则返回null
			InterEvent_nodes = null;
		}
		if (InterEvent_nodes == null) {	// 如果不存在中间事件，则直接跳出方法
			return ;
		}
		else {
			for (String node : InterEvent_nodes) {
				/*	创建petri元素并保存其与bpmn的联系	 */
				IntermediateThrowEvent inter = (IntermediateThrowEvent) graphics.getNodeData(node);
				String name = inter.getName();
				String id = inter.getId();
				ArrayList<PetriElement> petris = new ArrayList<>();
				Transition transition = new Transition(id, name);
				petris.add(transition);
				BpmnAndPetri e = new BpmnAndPetri(inter, petris);
				nodes.add(e);
				
				/* 添加结点 */ 
				result.addNode(transition);
			}
		}
	}
	
}
