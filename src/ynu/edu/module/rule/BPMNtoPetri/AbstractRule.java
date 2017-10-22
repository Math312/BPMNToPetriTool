package ynu.edu.module.rule.BPMNtoPetri;

import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;

public abstract class AbstractRule {
	
	protected static int place_id = 0;
	protected static int arc_id = 0;
	protected static int trans_id = 0;
	
	public abstract boolean matches(Graphics<BpmnElement> graphics);
	
	
	public abstract void transfer(Graphics<BpmnElement> graphics,Graphics<PetriElement> result);
	
}
