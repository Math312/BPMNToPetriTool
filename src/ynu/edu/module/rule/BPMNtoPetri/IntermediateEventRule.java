/*	MiddleEventRule.java  */

/**
 * Defines the rule of Middleevent to petri element.
 * @author 张豪
 */


package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;
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
	 * 将Bpmn图中的中间事件提取出来，并用链表进行存储并返回
	 * @param graphics
	 * @return sequenceflow_nodes
	 */
//	@Override
//	protected LinkedList<String> split(Graphics<BpmnElement> graphics) {
//		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
//		LinkedList<String> middleevent_nodes;
//		if (matches(graphics)) {		//	如果存在中间事件，则返回含有所有序列流id的链表
//			middleevent_nodes = bpmn_nodes.get(StartEvent.class.getName());
//		}
//		else {							//  如果不存在, 则返回null
//			middleevent_nodes = null;
//		}
//		return middleevent_nodes;	
//	}
	
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
				/*	获取中间事件	*/
				IntermediateThrowEvent inter = (IntermediateThrowEvent) graphics.getNodeData(node);
				
				/*	根据中间事件的name创建trans并添加 */
				String name = inter.getName();
				Transition trans = new Transition("trans " + name + trans_id++, name);
				result.addNode(trans);
			}
		}
	}
	
}
