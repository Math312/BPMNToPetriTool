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
//				String incoming = chore.getInComing();
//				String outcoming = chore.getOutGoing();
				String participant1 = chore.getParticipants()[0];
				String participant2 = chore.getParticipants()[1];
				String id = chore.getId();
				String name = chore.getName();
				chore.setTransition(new Transition(id, name));
				trans_id++;
				result.addNode(chore.getTransition());

			}
		}
	}
	
}
