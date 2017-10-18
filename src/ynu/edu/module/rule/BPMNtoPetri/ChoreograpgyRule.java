package ynu.edu.module.rule.BPMNtoPetri;

import ynu.edu.data.Graphics;
import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;;

public class ChoreograpgyRule extends AbstractRule{

	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Graphics<PetriElement> split(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics<PetriElement> transfer(Graphics<BpmnElement> graphics) {
		Graphics<PetriElement> g_petri = new Graphics<>();
		String incoming = "";
		String outcoming = "";
		String name = "";
		Choreography chore = new Choreography("0", incoming, outcoming, name);
		Transition trans = new Transition(incoming + "trans" + outcoming);
		g_petri.addNode(trans);
		return g_petri;
	}
	
}
