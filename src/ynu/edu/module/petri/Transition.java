/***********************************************************************
 * Module:  Transition.java
 * Author:  邵闫利
 * Purpose: Defines the Class Transition
 ***********************************************************************/

package ynu.edu.module.petri;

import java.util.*;

/** @pdOid 49034501-1994-4c98-ae4f-d03e0325df99 */
public class Transition extends PetriElement {
   /** @param id
    * @pdOid d0c4edfa-6b00-4305-8971-58f0e92cfb17 */
	private String name;
   public Transition(String id,String name) {
   	super.id = id;
   	this.name = name;
   }
   
   /** @pdOid 89256e1b-fa14-47fb-9830-5cca51d8b5e0 */
   public String toString() {
      // TODO: implement
      return null;
   }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}