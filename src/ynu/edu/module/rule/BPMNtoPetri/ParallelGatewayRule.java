package ynu.edu.module.rule.BPMNtoPetri;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.StartEvent;
import ynu.edu.module.petri.*;


public class ParallelGatewayRule extends AbstractRule {

	
	public boolean matches(Graphics<BpmnElement> graphics) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		return bpmn_nodes.containsKey(ParallelGateway.class.getName());
	}

	@Override
	public void transfer(Graphics<BpmnElement> graphics, Graphics<PetriElement> result) {
		Hashtable<String, LinkedList<String>> bpmn_nodes = graphics.getIds();
		LinkedList<String> parallelGateway_nodes;
		if (matches(graphics)) {
			parallelGateway_nodes = bpmn_nodes.get(ParallelGateway.class.getName());
		} else {
			parallelGateway_nodes = null;
		}
		if (parallelGateway_nodes == null) {		// 如果不存在Parallel gateway，则直接跳出方法
			return ;
		} else {
			for (String node : parallelGateway_nodes) {
				ParallelGateway parallel_gateway = (ParallelGateway)graphics.getNodeData(node);
				String id = parallel_gateway.getId();
				String name = parallel_gateway.getName();
				Transition trans = new Transition(id, name);
				trans_id++;
				result.addNode(trans);
			}
		}
		
		
	}
	

}
