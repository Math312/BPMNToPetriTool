/***********************************************************************
 * Module:  StartEvent.java
 * Author:  邵闫利
 * Purpose: Defines the Class StartEvent
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

import ynu.edu.module.petri.Place;
import ynu.edu.module.petri.Arc;
import ynu.edu.module.petri.Transition;




/** @pdOid a4f9d16f-649c-4846-a3fd-9dfcc1209797 */
public class StartEvent extends ArrayElement {
	
	private Place place;
	private Arc arc;
	private Transition transition;
	
	
	
	
	/** 设置name为start，inComing参数为null
    * 
    * @param id 
    * @param outGoing
    * @pdOid 3ebfd12a-6802-43da-af54-ba505bd9fe45 */
   public StartEvent(String id) {
    super.name = "start";
    super.inComing = null;
    super.id = id;
    super.outGoing = new ArrayList<String>();
   }
   
   /** @pdOid a894a427-464b-43fe-b81b-377cd35b5661 */
   public String toString() {
    return "Type:"+this.getClass().getName()+"\n id: "+id+"\n inComing: "+inComing+"\n outGoing: "+outGoing+"\n name:"+name;
   }

	public Place getPlace() {
		return place;
	}
	
	public Arc getArc() {
		return arc;
	}
	
	public Transition getTransition() {
		return transition;
	}
	
	public Transition getLastElem() {
		return transition;
	}
	
	public Place getFirstElem() {
		return place;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public void setArc(Arc arc) {
		this.arc = arc;
	}
	
	public void setTransition(Transition trans) {
		this.transition = trans;
	}

}