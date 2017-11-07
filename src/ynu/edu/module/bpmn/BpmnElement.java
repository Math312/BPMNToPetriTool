/***********************************************************************
 * Module:  BpmnElement.java
 * Author:  邵闫利
 * Purpose: Defines the Class BpmnElement
 ***********************************************************************/

package ynu.edu.module.bpmn;

import ynu.edu.data.GNode;

/** @pdOid 6f80fe0a-f0cc-42a1-9021-60df15250d8c */
public abstract class BpmnElement extends ynu.edu.data.GNode {
   /** @pdOid 759827a0-f4b2-4bc5-a148-902259096898 */
   
   public abstract String getInComing();

   public abstract String getOutGoing();

}