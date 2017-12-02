/*	StartEventRule.java  */

/**
 * Define the rule of startevent to petri element.
 * @author Hao
 */
 
package ynu.edu.module.rule.BPMNtoPetri;

import java.util.*;
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
				/*	创建petri元素并保存其与bpmn的联系	 */
				StartEvent start_event = (StartEvent) graphics.getNodeData(node);
				String id = start_event.getId();
				String name = start_event.getName();
				ArrayList<PetriElement> petris = new ArrayList<>();
				Place place = new Place("p" + place_id++, id);		// Place元素用"p" + place_id作为id, bpmn的id作为name
				Transition transition = new Transition(id, name);	// Transition直接使用bpmn元素的id和name作为其id和name 
				Arc arc = new Arc(place.getId() + " to " + id);		// Arc使用前一个Petri元素的id " to " 后一个Petri元素的id作为其id
				petris.add(place);
				petris.add(arc);
				petris.add(transition);
				BpmnAndPetri e = new BpmnAndPetri(start_event, petris);
				nodes.add(e);
				
				/*	添加结点 */
				result.addNode(place);
				result.addNode(arc);
				result.addNode(transition);
				
				/*	建立在Petri图上的连接 */
				result.addLink(place.getId(), arc.getId());
				result.addLink(arc.getId(), transition.getId());
			}
		}
	}

}
