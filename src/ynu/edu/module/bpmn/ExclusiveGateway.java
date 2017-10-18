/***********************************************************************
 * Module:  ExclusiveGateway.java
 * Author:  邵闫利
 * Purpose: Defines the Class ExclusiveGateway
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

/** @pdOid 4faaa77c-8534-4b62-bf35-3ad9eefd592e */
public class ExclusiveGateway extends ArrayElement {
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

}