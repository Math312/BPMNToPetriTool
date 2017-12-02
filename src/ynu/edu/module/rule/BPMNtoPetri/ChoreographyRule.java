/*	ChoreographyRule.java  */

/**
 * Define the rule of Choreography to petri element.
 * @author Hao
 */

package ynu.edu.module.rule.BPMNtoPetri;

import java.util.*;

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
				/*	创建petri元素并保存其与bpmn的联系	 */
				Choreography chore = (Choreography) graphics.getNodeData(node);
				String id = chore.getId();
				String name = chore.getName();
				ArrayList<PetriElement> petris = new ArrayList<>();
				Transition transition = new Transition(id, name);
				trans_id++;
				petris.add(transition);
				BpmnAndPetri e = new BpmnAndPetri(chore, petris);
				nodes.add(e);
				
				/* 添加结点 */
				result.addNode(transition);
			}
		}
	}
	
}
