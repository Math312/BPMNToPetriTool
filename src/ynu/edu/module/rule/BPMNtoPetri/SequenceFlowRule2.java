/*	SequenceFlowRule.java */

/**
 * Defines the rule of sequenceflow to petri element.
 * Notice: 该规则适用于第二套转换规则.
 * @author Hao
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
				BpmnAndPetri preNode = null;
				BpmnAndPetri nextNode = null;
				for (int i = 0; i < nodes.size(); i++) {
					if (preNodeID == nodes.get(i).getBpmnId()) {
						preNode = nodes.get(i);
					}
					if (nextNodeID == nodes.get(i).getBpmnId()) {
						nextNode = nodes.get(i);
					}
 				}
			
				if (preNode.getBpmnElem() instanceof ExclusiveGateway) {
					ExclusiveGateway preBpmnNode = (ExclusiveGateway)preNode.getBpmnElem();
					
					/* 对应PPT中Decision中的y1, y2的情况 */
					if (preBpmnNode.getOutGoingSize() > 1) {
						/*	创建petri元素 */
						Transition transition = new Transition(id, name);
						Place place = new Place("p" + place_id++, id);
						Arc arc1 = new Arc(preNode.getLastElem().getId() + " to " + transition.getId());
						Arc arc2 = new Arc(transition.getId() + " to " + place.getId());
						Arc arc3 = new Arc(place.getId() + " to " + nextNode.getLastElem().getId());
						
						/* 添加结点 */
						result.addNode(transition);
						result.addNode(place);
						result.addNode(arc1);
						result.addNode(arc2);
						result.addNode(arc3);
						
						/* 建立在Petri图上的连接 */
						result.addLink(preNode.getLastElem().getId(), arc1.getId());
						result.addLink(arc1.getId(), transition.getId());
						result.addLink(transition.getId(), arc2.getId());
						result.addLink(arc2.getId(), place.getId());
						result.addLink(place.getId(), arc3.getId());
						result.addLink(arc3.getId(), nextNode.getFirstElem().getId());
					} 
					/* 对应PPT中Merge中的y的情况 */
					else if (preBpmnNode.getOutGoingSize() == 1) {	
						/* 创建并添加结点 */
						Arc arc = new Arc(preNode.getLastElem().getId() + " to " + nextNode.getFirstElem().getId());
						result.addNode(arc);
						
						/* 建立在Petri图上的连接 */
						result.addLink(preNode.getLastElem().getId(), arc.getId());
						result.addLink(arc.getId(), nextNode.getFirstElem().getId());
					}
				}
				else if(nextNode.getBpmnElem() instanceof ExclusiveGateway) {
					/* 对应PPT中Merge中x1 x2的情况 */
					ExclusiveGateway nextBpmnNode = (ExclusiveGateway)nextNode.getBpmnElem();
					if (nextBpmnNode.getInComingSize() > 1) {						
						Transition transition = new Transition(id, name);
						Place place = new Place("p" + place_id++, id);
						Arc arc1 = new Arc(preNode.getLastElem().getId() + " to " + place.getId());
						Arc arc2 = new Arc(place.getId() + " to " + transition.getId());
						Arc arc3 = new Arc(transition.getId() + " to " + nextNode.getFirstElem().getId());
						result.addNode(transition);
						result.addNode(place);
						result.addNode(arc1);
						result.addNode(arc2);
						result.addNode(arc3);
						
						/* 建立在Petri图上的连接 */
						result.addLink(preNode.getLastElem().getId(), arc1.getId());
						result.addLink(arc1.getId(), transition.getId());
						result.addLink(transition.getId(), arc2.getId());
						result.addLink(arc2.getId(), place.getId());
						result.addLink(place.getId(), arc3.getId());
						result.addLink(arc3.getId(), nextNode.getFirstElem().getId());
					} 
					/* 对应PPT中Decision中的x的情况 */
					else if (nextBpmnNode.getInComingSize() == 1) {

						/* 创建并添加Petri结点 */
						Arc arc = new Arc(preNode.getLastElem().getId() + " to " + nextNode.getFirstElem().getId());
						result.addNode(arc);
						
						/* 建立在Petri图上的连接 */
						result.addLink(preNode.getLastElem().getId(), arc.getId());
						result.addLink(arc.getId(), nextNode.getFirstElem().getId());
					} 
				}
				/* 普通序列流处理 */
				else {
					/* 创建并添加Petri结点 */ 
					Place place = new Place("p" + place_id++, id);
					Arc arc1 = new Arc(preNode.getLastElem().getId() + " to " + place.getId());
					Arc arc2 = new Arc(place.getId() + " to " + nextNode.getFirstElem().getId());
					result.addNode(place);
					result.addNode(arc1);
					result.addNode(arc2);
					
					/* 建立在Petri图上的连接 */ 
					result.addLink(preNode.getLastElem().getId(), arc1.getId());
					result.addLink(arc1.getId(), place.getId());
					result.addLink(place.getId(), arc2.getId());
					result.addLink(arc2.getId(), nextNode.getFirstElem().getId());
				}
			}
		}
	}
}


