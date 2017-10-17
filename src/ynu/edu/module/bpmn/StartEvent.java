/***********************************************************************
 * Module:  StartEvent.java
 * Author:  邵闫利
 * Purpose: Defines the Class StartEvent
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

/** @pdOid a4f9d16f-649c-4846-a3fd-9dfcc1209797 */
public class StartEvent extends ArrayElement {
   /** 设置name为start，inComing参数为null
    * 
    * @param id 
    * @param outGoing
    * @pdOid 3ebfd12a-6802-43da-af54-ba505bd9fe45 */
   public StartEvent(String id, String outGoing) {
    super.name = "start";
    super.inComing = null;
    super.id = id;
    super.outGoing = new ArrayList<String>();
   }
   
   /** @pdOid a894a427-464b-43fe-b81b-377cd35b5661 */
   public String toString() {
    return "Type:"+this.getClass().getName()+"\n id: "+id+"\n inComing: "+inComing+"\n outGoing: "+outGoing+"\n name:"+name;
   }

}