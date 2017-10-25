/***********************************************************************
 * Module:  Place.java
 * Author:  邵闫利
 * Purpose: Defines the Class Place
 ***********************************************************************/

package ynu.edu.module.petri;

import java.util.*;

/** @pdOid e2406bd6-e759-4e23-bfcd-26f90be22aff */
public class Place extends PetriElement {
   /** @param id
    * @pdOid c36f86b9-08f0-4dcb-9b4a-d9392b6c72e3 */
	private String name;
   public Place(String id,String name) {
   	super.id = id;
   	this.name = name;
   }
   
   /** @pdOid 6c1f6f54-61b3-48bb-a1fe-dfccfdc9839a */
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