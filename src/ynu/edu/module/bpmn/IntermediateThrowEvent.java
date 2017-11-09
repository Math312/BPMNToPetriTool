package ynu.edu.module.bpmn;

import java.util.ArrayList;

import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Transition;

public class IntermediateThrowEvent extends ArrayElement 
{
	//中间事件
	
	private Transition transition;
	
	public IntermediateThrowEvent(String id,String name) 
	{
		super.name = name;
		super.id = id;
		super.inComing = new ArrayList<String>();
		super.outGoing = new ArrayList<String>();
	}

	@Override
	public PetriElement getFirstElem() {
		return transition;
	}

	@Override
	public PetriElement getLastElem() {
		return transition;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

}
