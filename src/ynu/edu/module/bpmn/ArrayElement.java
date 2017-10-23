/***********************************************************************
 * Module:  GateElement.java
 * Author:  邵闫利
 * Purpose: Defines the Class GateElement
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

/** @pdOid 1faed1f4-9db2-4b12-87f6-dcb8b0d248f6 */
public abstract class ArrayElement extends BpmnElement {
	
	protected String name;
	
   /** 存储前集的ID
    * 
    * 
    * 
    * @pdOid 7b71b738-7dd9-44a2-977f-6f157c969e8e */
   protected ArrayList<String> inComing;
   /** 存储后集ID
    * 
    * 
    * 
    * @pdOid e7928875-028c-47a9-9a93-118cd594f8bd */
   protected ArrayList<String> outGoing;
   /** 
    * 存储本身ID
    * 
    * @pdOid 2b146967-119e-4e5c-851e-927e4e8a99ec */
   
   /** 用于向存储前集ID的集合中添加信息
    * 
    * 
    * @param inComingValue 要添加的ID值
    * @pdOid a21273d7-843a-4068-b087-5de5d21d7f5d */
   public boolean AppendInComing(String inComingValue) {
   	if (inComingValue != null) {
   		inComing.add(inComingValue);
   		return true;
   	} else {
   		return false;
   	}
   }
   
   /** 用于向存储后集ID的集合中添加信息
    * 
    * 
    * @param outGoingValue 要添加的ID
    * @pdOid eaa9ab78-597a-4050-966b-05b7a0bb698e */
   public boolean AppendOutGoing(String outGoingValue) {
   	if (outGoingValue != null) {
   		outGoing.add(outGoingValue);
   		return true;
   	} else {
   		return false;
   	}
   
   }
   
   /** @pdOid a27d4dfd-53b2-4e37-a802-8945e406e97b */
   public String getInComing() {
   	if(inComing == null) 
   	{
   		return "";
   	}
   	else 
   	{
   		StringBuilder sb = new StringBuilder();
   	   	for(String s:this.inComing) 
   	   	{
   	   		sb.append(s+"|");
   	   	}
   	   	
   	   	return sb.toString().substring(0,sb.length()-1);
   	}
   }
   
   /** @pdOid b27d14c8-7735-4136-af6d-2d5b31f81ac1 */
   public String getOutGoing() {
   	if(outGoing == null) 
   	{
   		return "";
   	}
   	else 
   	{
   		StringBuilder sb = new StringBuilder();
   	   	for(String s:this.outGoing) 
   	   	{
   	   		sb.append(s+"|");
   	   	}
   	   	
   	   	return sb.toString().substring(0,sb.length()-1);
   	}
   }
   
   /** @pdOid be68d7e5-7d6c-4f76-b61c-9d0b8c964dfd */
   public String getId() {
   	return id;
   }
   @Override
   public String toString() {
	    StringBuilder sb = new StringBuilder("Type:"+this.getClass().getName()+"\n id: "+id+"\n inComing: \n"); 
	    if(inComing != null && inComing.size()!=0){
	    int i  = 1;
	    for(String data:inComing) 
	    {
	     sb.append("inComing"+i+": "+data+"\n");
	     i ++;
	    }
	    }
	    
	    sb.append("outGoing: \n");
	    if(outGoing != null &&outGoing.size()!=0){
	    int j = 1;
	    for(String data:outGoing) 
	    {
	     sb.append("outGoing"+j+": "+data+"\n");
	     j ++;
	    }
	    }
	    return sb.toString();
	    
	   }

}