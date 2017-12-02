package ynu.edu.module.bpmn;

import ynu.edu.module.petri.*;
import ynu.edu.module.petri.Arc;

public class SequenceFlow extends CommonElement {
	
	public SequenceFlow(String id,String inComing,String outGoing) 
	{
		super.id = id;
		super.inComing = inComing;
		super.outGoing = outGoing;
		super.name = null;
	}
	
	public String toString() 
	{
	    return "Type:"+this.getClass().getName()+"\n id: "+id+"\n inComing: "+inComing+"\n outGoing: "+outGoing+"\n name:"+name;
	}
	
	
	public String getName() {
		return name;
	}
	
}
