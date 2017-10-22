/***********************************************************************
 * Module:  AbstractRule.java
 * Author:  邵闫利
 * Purpose: Defines the Class AbstractRule
 ***********************************************************************/

package ynu.edu.module.rule.BPMNtoBPMN;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;

/** @pdOid 64dc4b0f-ab03-4b68-b49f-8621e553298d */
public abstract class AbstractRule {
  
   
   /** @param graphics
    * @pdOid 60d8efc3-fd12-437e-bca6-9a07504344ad */
   public abstract boolean matches(Graphics<BpmnElement> graphics);
   /** @param graphics
    * @pdOid 1a6f8d0f-0dc0-4b7b-b9da-fb0fd1b6957e */
   public abstract Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics);

}