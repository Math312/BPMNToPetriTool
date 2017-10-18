/***********************************************************************
 * Module:  ComplrxGateway.java
 * Author:  邵闫利
 * Purpose: Defines the Class ComplrxGateway
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

/** @pdOid ad46f155-adf2-4aba-821d-ba4837f3066d */
public class EventBasedGateway extends ArrayElement {
   /** @param id
    * @pdOid 07e08c98-1d16-4fc2-a31a-d985a4c6491b */
   public EventBasedGateway(String id) {
    super.id = id;
    super.inComing = new ArrayList<String>();
    super.outGoing = new ArrayList<String>();
   }
   
   /** @pdOid a4f51db7-e238-4e83-be6f-f30991ea4743 */
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