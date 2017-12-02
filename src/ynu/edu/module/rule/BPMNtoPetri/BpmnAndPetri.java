package ynu.edu.module.rule.BPMNtoPetri;
/**
 * 用于保存Bpmn元素与其所对应的Petri元素的信息
 * @author Hao
 */

import java.util.*;

import ynu.edu.module.petri.*;
import ynu.edu.module.bpmn.*;

public class BpmnAndPetri {
	private BpmnElement bpmn;
	private ArrayList<PetriElement> petri;		// 用于存储其对应的petri元素
	
	public BpmnAndPetri(BpmnElement bpmn, ArrayList<PetriElement> petri) {
		this.bpmn = bpmn;
		this.petri = petri;
	}
	
	public PetriElement getFirstElem() {
		return petri.get(0);
	}
	
	public PetriElement getLastElem() {
		return petri.get(petri.size()-1);
	}

	public String getBpmnId() {
		return bpmn.getId();
	}
	
	public BpmnElement getBpmnElem() {
		return bpmn;
	}
	
}
