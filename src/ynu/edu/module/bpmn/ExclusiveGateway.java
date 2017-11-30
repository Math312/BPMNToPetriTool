/***********************************************************************
 * Module:  ExclusiveGateway.java
 * Author:  邵闫利
 * Purpose: Defines the Class ExclusiveGateway
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;
import ynu.edu.module.petri.*;



/** @pdOid 4faaa77c-8534-4b62-bf35-3ad9eefd592e */
public class ExclusiveGateway extends ArrayElement {
	
	private Place place;
	private Transition transition1;
	private Transition transition2;
	private Arc arc1;
	private Arc arc2;
	
   /** @param id
    * @pdOid 86674916-7b7a-4e6f-a7c9-ad29e3dc5567 */
   public ExclusiveGateway(String id) {
    super.id = id;
    super.inComing = new ArrayList<String>();
    super.outGoing = new ArrayList<String>();
   }
   
   /** @pdOid 82005b88-0a36-4b23-ade3-7dcd86ca2e9e */
   public String toString() {
    StringBuilder sb = new StringBuilder("Type:"+this.getClass().getName()+"\n id: "+id+"\n inComing: \n"); 
    int i  = 1;
    for(String data:inComing) 
    {
     sb.append("inComing"+i+": "+data+"\n");
     i ++;
    }
    sb.append("outGoing: \n");
    int j = 1;
    for(String data:outGoing) 
    {
     sb.append("outGoing"+j+": "+data+"\n");
     j ++;
    }
    
    return sb.toString();
    
   }

	@Override
	public PetriElement getFirstElem() {
		return place;
	}
	
	@Override
	public PetriElement getLastElem() {
		return place;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Transition getTransition1() {
		return transition1;
	}

	public Transition getTransition2() {
		return transition2;
	}

	public void setTransition1(Transition transition1) {
		this.transition1 = transition1;
	}

	public void setTransition2(Transition transition2) {
		this.transition2 = transition2;
	}

	public Arc getArc1() {
		return arc1;
	}

	public Arc getArc2() {
		return arc2;
	}

	public void setArc1(Arc arc1) {
		this.arc1 = arc1;
	}

	public void setArc2(Arc arc2) {
		this.arc2 = arc2;
	}

}