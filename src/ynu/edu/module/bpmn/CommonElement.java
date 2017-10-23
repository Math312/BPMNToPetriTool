/***********************************************************************
 * Module:  CommonElement.java
 * Author:  邵闫利
 * Purpose: Defines the Class CommonElement
 ***********************************************************************/

package ynu.edu.module.bpmn;

import java.util.*;

/** @pdOid c306e47f-a2ed-4c08-81b1-f263dd355f27 */
public abstract class CommonElement extends BpmnElement {
   /** 用于存储一般标签前集的ID
    * 
    * 
    * @pdOid e2fee228-0041-40b2-919c-55d450d65a1e */
   protected String inComing;
   /** 用于存储一般标签后集的ID
    * 
    * 
    * @pdOid e677ea60-9ac6-4114-aaf5-a1e3922b23e8 */
   protected String outGoing;

   /** 当前标签名字
    * 
    * 
    * @pdOid 380cdfd9-8a9d-450c-8639-1f93841bc1e8 */
   protected String name;
   
   /** @pdOid 3067378a-c087-467e-b170-4c62a296d7e1 */
   public String getInComing() {
    return inComing;
   }
   
   /** @pdOid 8ae2d664-9b04-48ef-9a7a-5f65cf61dd32 */
   public String getOutGoing()   {
   return outGoing;
     }
   
   /** @pdOid 50f68cb6-b0d8-479b-a323-6dce1695c894 */
   public String getId()   {
   return super.id;
     }
   
   /** @pdOid dc2b7a8a-7ecd-4523-b993-a557d14594ee */
   public String getName()   {
   return name;
     }

}