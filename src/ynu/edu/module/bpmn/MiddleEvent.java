package ynu.edu.module.bpmn;

import java.util.ArrayList;

public class MiddleEvent extends GateElement
{
	
	
	/** outGoing设置为null，name设置为end
	    * 
	    * 
	    * @param id 
	    * @param inComing
	    * @pdOid 89b59190-2baa-43fe-a33c-af4d7a7b79ba */
		
		
	   public MiddleEvent(String id,String name) {
	    super.id = id;
	    super.inComing = new ArrayList<>();
	    super.outGoing = new ArrayList<>();
	    super.name = name;
	   }
	   

}
