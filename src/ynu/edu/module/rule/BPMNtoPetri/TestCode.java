package ynu.edu.module.rule.BPMNtoPetri;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Place;

public class TestCode {

	public static void main(String[] args) {
		
		Graphics<BpmnElement> g_bpmn = new Graphics<>();
		StartEventRule ser = new StartEventRule();
		Graphics<PetriElement> g_petri = new Graphics<>();
		g_petri = ser.split(g_bpmn);
		for (PetriElement e: g_petri) {
			
		}
		

	}

}
