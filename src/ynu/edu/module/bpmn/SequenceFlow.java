package ynu.edu.module.bpmn;

import ynu.edu.module.petri.*;
import ynu.edu.module.petri.Arc;

public class SequenceFlow extends CommonElement {
	
	private Arc arc1;
	private Place place;
	private Arc arc2;
	private Arc arc3;
	private Arc arc;
	private Transition transition;
	
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

		public Arc getArc1() {
			return arc1;
		}
	
		public Place getPlace() {
			return place;
		}
	
		public Arc getArc2() {
			return arc2;
		}
	
		public void setArc1(Arc arc1) {
			this.arc1 = arc1;
		}
	
		public void setPlace(Place place2) {
			this.place = place2;
		}
	
		public void setArc2(Arc arc2) {
			this.arc2 = arc2;
		}

		public Arc getArc() {
			return arc;
		}

		public void setArc(Arc arc) {
			this.arc = arc;
		}

		public Arc getArc3() {
			return arc3;
		}

		public void setArc3(Arc arc3) {
			this.arc3 = arc3;
		}

		public Transition getTransition() {
			return transition;
		}

		public void setTransition(Transition transition) {
			this.transition = transition;
		}
	
}
