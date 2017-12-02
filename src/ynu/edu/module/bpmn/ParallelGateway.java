/***********************************************************************
 * Module:  ParallelGateway.java
 * Author:  邵闫利
 * Purpose: Defines the Class ParallelGateway
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Transition;

/** @pdOid 4e89ccaf-b71a-4a39-ae25-a5c0cc77138e */
public class ParallelGateway extends ArrayElement {
	
   /** @param id
    * @pdOid 26caa8d9-997b-487a-b345-6b26a9cf2ee3 */
   public ParallelGateway(String id) {
    super.id = id;
    super.inComing = new ArrayList<String>();
    super.outGoing = new ArrayList<String>();
   }
   
   /** @pdOid 58211a5e-1d30-4d4b-8429-73f30f3942cc */
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
   
}