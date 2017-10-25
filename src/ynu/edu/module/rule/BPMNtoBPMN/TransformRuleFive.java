package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;

public class TransformRuleFive extends AbstractRule {
	Hashtable<String,LinkedList<String>> allID ;
	String [] choreographyID;
	String [] intermediateThrowEventID;
	String [][] IDbyNode ;
	String [] Participants;
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = graphics.getIds();
//		if(allID.get(Choreography.class.getName())!=null)
//		{
//			choreographyID = allID.get(Choreography.class.getName()).toArray(new String[allID.get(Choreography.class.getName()).size()]);
//			for (int i=0 ; i< choreographyID.length ; i++ )
//			{
//				if(choreographyID[i].)
//				
//				
//				
//				
//				IDbyNode = graphics.getIDbyNode(choreographyID[i]);
//				if(IDbyNode[1].length==1 && IDbyNode[0].length>1)
//				{
//					return true;
//				}
//			}
//		}
		
		
		return false;
	}
	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
}
