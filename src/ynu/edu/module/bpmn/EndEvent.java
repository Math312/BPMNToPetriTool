/***********************************************************************
 * Module:  EndEvent.java
 * Author:  邵闫利
 * Purpose: Defines the Class EndEvent
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

import ynu.edu.module.petri.*;


/** @pdOid 6ea4e186-fa57-4ff9-8c3d-1459021bf785 */
public class EndEvent extends ArrayElement {
	
	
   /** outGoing设置为null，name设置为end
    * 
    * 
    * @param id 
    * @param inComing
    * @pdOid 89b59190-2baa-43fe-a33c-af4d7a7b79ba */
   public EndEvent(String id) {
    super.id = id;
    super.name = "end";
    super.inComing = new ArrayList<String>();
    super.outGoing = null;
   }
	
}