/***********************************************************************
 * Module:  Choreography.java
 * Author:  邵闫利
 * Purpose: Defines the Class Choreography
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Transition;

/** @pdOid c0491f64-a3a0-46e4-bc05-a27e2bc7d0be */
public class Choreography extends ArrayElement {
   /** @param id 
    * @param inComming 
    * @param outGoing 
    * @param name
    * @pdOid 914cb895-ca44-446d-a40c-4687a8323445 */
	private String[] participants = new String[2];

   public Choreography(String id, String name,String participant1,String participant2) {
    super.id = id;
    super.inComing = new ArrayList<String>();
    super.outGoing = new ArrayList<String>();
    super.name = name;
    participants[0] = participant1;
    participants[1] = participant2;
   }
   public String[] getParticipants() {
	   return participants;
   }
   public void setParticipants(String[] participants) {
	   this.participants = participants;
   }
   
}