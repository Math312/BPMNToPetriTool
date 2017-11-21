/*	SequenceFlowRule.java */

/**
 * Defines the rule of sequenceflow to petri element.
 * @author 张豪
 */

package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;

public class SequenceFlowRule extends AbstractRule {
	
	/**
	 * 判断Bpmn图中是否存在序列流
	 * 若存在，则返回true,若不存在，则返回false
	 * @param graphics
	 * @return boolean
	 * 
	 */
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(SequenceFlow.class.getName());	// 使用哈希表的contaisKey来判断是否存在序列流
	}
	
	/**
	 * 将序列流转换为petri网中的元素
	 * @param graphics, result
	 * 
	 */
	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> sequenceflow_nodes;
		if (matches(graphics)) {				//	如果存在序列流，则返回含有所有序列流id的链表
			sequenceflow_nodes = bpmn_nodes.get(SequenceFlow.class.getName());
		}
		else {									//  如果不存在, 则返回null
			sequenceflow_nodes = null;
		}
		if (sequenceflow_nodes == null) {		// 如果不存在序列流，则直接跳出方法
			return ;
		}
		else {
			for (String node : sequenceflow_nodes) {
				/*   创建petri元素  */
				SequenceFlow sequence_flow = (SequenceFlow)graphics.getNodeData(node);
				String id = sequence_flow.getId();
				String preNodeID = graphics.getIDbyNode(node)[1][0];
				String nextNodeID = graphics.getIDbyNode(node)[0][0];
				ArrayElement preNode = (ArrayElement)graphics.getNodeData(preNodeID);
				ArrayElement nextNode = (ArrayElement)graphics.getNodeData(nextNodeID);
				
				/* EventBasedGateway 特殊处理 */
//				if (preNode instanceof ExclusiveGateway || nextNode instanceof ExclusiveGateway) {
//					/*	添加结点 */
//					Place place = new Place("p" + place_id++, id);
//					Arc arc = new Arc(preNodeID + "to" + place.getId());
//					result.addNode(arc);
//					
//					/*	添加连接 */
//					result.addLink(preNode.getLastElem().getId(), arc.getId());
//					result.addLink(arc.getId(), nextNode.getFirstElem().getId());
//				} else {
					/*	添加结点 */
					Place place = new Place("p" + place_id++, id);
					Arc arc1 = new Arc(preNodeID + " to " + place.getId());	
					Arc arc2 = new Arc(place.getId() + " to " + nextNodeID);
					result.addNode(arc1);
					result.addNode(place);
					result.addNode(arc2);
					
					/*	添加连接 */
					result.addLink(preNode.getLastElem().getId(), arc1.getId());     // 建立前一个结点与arc1的联系
					result.addLink(arc1.getId(), place.getId());					 // 建立arc1与place的联系
					result.addLink(place.getId(), arc2.getId());					 // 建立place于arc2的联系	
					result.addLink(arc2.getId(), nextNode.getFirstElem().getId());	 // 建立arc2与后面结点的联系
//				}
			}
		}
	}
}
