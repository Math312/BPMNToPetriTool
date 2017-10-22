/*	ChoreographyRule.java  */

/**
 * Defines the rule of Choreography to petri element.
 * @author 张豪
 */


package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;;

public class ChoreographyRule extends AbstractRule{

	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(EndEvent.class.getName());
	}

//	protected LinkedList<String> split(Graphics<BpmnElement> graphics) {
//		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
//		LinkedList<String> Choreography_nodes;
//		if (matches(graphics)) {
//			Choreography_nodes = bpmn_nodes.get(Choreography.class.getName());
//		}
//		else {
//			Choreography_nodes = null;
//		}
//		return Choreography_nodes;
//	}

	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> Choreography_nodes;
		if (matches(graphics)) {
			Choreography_nodes = bpmn_nodes.get(Choreography.class.getName());
		}
		else {
			Choreography_nodes = null;
		}
		if (Choreography_nodes == null) {
			return ;
		}
		else {
			for (String node : Choreography_nodes) {
				Choreography chore = (Choreography) graphics.getNodeData(node);
				String incoming = chore.getInComing();
				String outcoming = chore.getOutGoing();
				String name = chore.getName();
				Transition trans = new Transition(incoming + '_' + outcoming + '_' + name + trans_id++);
				result.addNode(trans);
			}
		}
	}
	
}
