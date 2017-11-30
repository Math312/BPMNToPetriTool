package ynu.edu.module.rule.BPMNtoPetri;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;

public interface TransferInterface {

	public Graphics<PetriElement> transfer(Graphics<BpmnElement> graphics);
	
}
