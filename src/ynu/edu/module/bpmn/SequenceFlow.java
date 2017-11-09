package ynu.edu.module.bpmn;

import Petrinet.Place;
import ynu.edu.module.petri.Arc;

public class SequenceFlow extends CommonElement {
	
	private Arc arc1;
	private Place place;
	private Arc arc2;
	
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
	
		public void setPlace(Place place) {
			this.place = place;
		}
	
		public void setArc2(Arc arc2) {
			this.arc2 = arc2;
		}
	
}
