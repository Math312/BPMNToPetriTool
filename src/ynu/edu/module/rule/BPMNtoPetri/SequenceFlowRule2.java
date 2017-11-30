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

public class SequenceFlowRule2 extends AbstractRule {
	
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
				String name = sequence_flow.getName();
				String preNodeID = graphics.getIDbyNode(node)[1][0];
				String nextNodeID = graphics.getIDbyNode(node)[0][0];
				ArrayElement preNode = (ArrayElement)graphics.getNodeData(preNodeID);
				ArrayElement nextNode = (ArrayElement)graphics.getNodeData(nextNodeID);
				
				if (preNode instanceof ExclusiveGateway) {
					preNode = (ExclusiveGateway)preNode;
					
					/* 对应PPT中Decision中的y1, y2的情况 */
					if (preNode.getOutGoingSize() > 1) {
						/* 添加结点  */
						sequence_flow.setTransition(new Transition(id, name));
						trans_id++;
						sequence_flow.setPlace(new Place("p" + place_id++, id));
						sequence_flow.setArc1(new Arc(preNode.getLastElem().getId() + " to " + sequence_flow.getTransition().getId()));
						sequence_flow.setArc2(new Arc(sequence_flow.getTransition().getId() + " to " + sequence_flow.getPlace().getId()));
						sequence_flow.setArc3(new Arc(sequence_flow.getPlace().getId() + " to " + nextNode.getFirstElem().getId()));
						result.addNode(sequence_flow.getTransition());
						result.addNode(sequence_flow.getPlace());
						result.addNode(sequence_flow.getArc1());
						result.addNode(sequence_flow.getArc2());
						result.addNode(sequence_flow.getArc3());
						
						/* 添加连接 */
						result.addLink(preNode.getLastElem().getId(), sequence_flow.getArc1().getId());
						result.addLink(sequence_flow.getArc1().getId(), sequence_flow.getTransition().getId());
						result.addLink(sequence_flow.getTransition().getId(), sequence_flow.getArc2().getId());
						result.addLink(sequence_flow.getArc2().getId(), sequence_flow.getPlace().getId());
						result.addLink(sequence_flow.getPlace().getId(), sequence_flow.getArc3().getId());
						result.addLink(sequence_flow.getArc3().getId(), nextNode.getFirstElem().getId());
					} 
					/* 对应PPT中Merge中的y的情况 */
					else if (preNode.getOutGoingSize() == 1) {	
						/* 添加结点 */
						sequence_flow.setArc(new Arc(preNode.getLastElem().getId() + " to " + nextNode.getFirstElem().getId()));
						result.addNode(sequence_flow.getArc());
						
						result.addLink(preNode.getLastElem().getId(), sequence_flow.getArc().getId());
						result.addLink(sequence_flow.getArc().getId(), nextNode.getFirstElem().getId());
					}
				}
				else if(nextNode instanceof ExclusiveGateway) {
					/* 对应PPT中Merge中x1 x2的情况 */
					if (nextNode.getInComingSize() > 1) {
						sequence_flow.setTransition(new Transition(id, name));
						trans_id++;
						sequence_flow.setPlace(new Place("p" + place_id++, id));
						sequence_flow.setArc1(new Arc(preNode.getLastElem().getId() + " to " + sequence_flow.getPlace().getId()));
						sequence_flow.setArc2(new Arc(sequence_flow.getPlace().getId() + " to " + sequence_flow.getTransition().getId()));
						sequence_flow.setArc3(new Arc(sequence_flow.getTransition().getId() + " to " + nextNode.getFirstElem().getId()));
						result.addNode(sequence_flow.getTransition());
						result.addNode(sequence_flow.getPlace());
						result.addNode(sequence_flow.getArc1());
						result.addNode(sequence_flow.getArc2());
						result.addNode(sequence_flow.getArc3());
						
						/* 添加连接 */
						result.addLink(preNode.getLastElem().getId(), sequence_flow.getArc1().getId());
						result.addLink(sequence_flow.getArc1().getId(), sequence_flow.getTransition().getId());
						result.addLink(sequence_flow.getTransition().getId(), sequence_flow.getArc2().getId());
						result.addLink(sequence_flow.getArc2().getId(), sequence_flow.getPlace().getId());
						result.addLink(sequence_flow.getPlace().getId(), sequence_flow.getArc3().getId());
						result.addLink(sequence_flow.getArc3().getId(), nextNode.getFirstElem().getId());		
					} 
					/* 对应PPT中Decision中的x的情况 */
					else if (nextNode.getInComingSize() == 1) {
						sequence_flow.setArc(new Arc(preNode.getLastElem().getId() + " to " + nextNode.getFirstElem().getId()));
						result.addNode(sequence_flow.getArc());
						result.addLink(preNode.getLastElem().getId(), sequence_flow.getArc().getId());
						result.addLink(sequence_flow.getArc().getId(), nextNode.getFirstElem().getId());
					} 
				} else {
					sequence_flow.setPlace(new Place("p" + place_id++, id));
					sequence_flow.setArc1(new Arc(preNodeID + " to " + sequence_flow.getPlace().getId()));
					sequence_flow.setArc2(new Arc(sequence_flow.getPlace().getId() + " to " + nextNodeID));
					result.addNode(sequence_flow.getArc1());
					result.addNode(sequence_flow.getPlace());
					result.addNode(sequence_flow.getArc2());
					result.addLink(preNode.getLastElem().getId(), sequence_flow.getArc1().getId());
					result.addLink(sequence_flow.getArc1().getId(), sequence_flow.getPlace().getId());
					result.addLink(sequence_flow.getPlace().getId(), sequence_flow.getArc2().getId());
					result.addLink(sequence_flow.getArc2().getId(), nextNode.getFirstElem().getId());
				}
			}
		}
	}
}


