package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.IntermediateThrowEvent;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleFour extends AbstractRule {
	
	Graphics graphics;
	Hashtable<String,LinkedList<String>> allID ;
	String [] choreographyID;
	String [] intermediateThrowEventID;
	String [][] IDbyNode ;
	
	@Override
	//=================================================================================//
		//遍历这个图，得到图中所有编排任务的id
		//根据每个编排任务的id，判断是否满足第三规则
		//规则为编排任务前面的结点书等于1，后面的结点数大于1
		//满足则进一步处理
	
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = graphics.getIds();
		if(allID.get(Choreography.class.getName())!=null)
		{
			choreographyID = allID.get(Choreography.class.getName()).toArray(new String[allID.get(Choreography.class.getName()).size()]);
			for (int i=0 ; i< choreographyID.length ; i++ )
			{
				IDbyNode = graphics.getIDbyNode(choreographyID[i]);
				if(IDbyNode[1].length==1 && IDbyNode[0].length>1)
				{
					return true;
				}
			}
		}
		if(allID.get(IntermediateThrowEvent.class.getName())!=null)
		{
			intermediateThrowEventID = allID.get(IntermediateThrowEvent.class.getName()).toArray(new String[allID.get(IntermediateThrowEvent.class.getName()).size()]);
			for (int i=0 ; i< intermediateThrowEventID.length ; i++ )
			{
				IDbyNode = graphics.getIDbyNode(intermediateThrowEventID[i]);
				if(IDbyNode[1].length==1&&IDbyNode[0].length>1)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = graphics.getIds();
		if(allID.get(Choreography.class.getName())!=null)
		{
			choreographyID = allID.get(Choreography.class.getName()).toArray(new String[allID.get(Choreography.class.getName()).size()]);
			for (int i=0 ; i< choreographyID.length ; i++ )
			{
				IDbyNode = graphics.getIDbyNode(choreographyID[i]);
				if(IDbyNode[1].length==1 && IDbyNode[0].length>1)
				{
					graphics.removeNode(choreographyID[i]);
					
					ParallelGateway parallelGateway = new ParallelGateway(Flag.getID());
					graphics.addNode(parallelGateway);
					Choreography  choreography = new Choreography(Flag.getID(), "choreography", null, null);
					graphics.addNode(choreography);
					SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(),choreography.getId(), parallelGateway.getId());
					graphics.addNode(sequenceFlow);
					
					graphics.addLink(choreography.getId(),sequenceFlow.getId());
					graphics.addLink(sequenceFlow.getId(),parallelGateway.getId());
					graphics.addLink(IDbyNode[1][0],choreography.getId());
					
					for(int j = 0 ; j < IDbyNode[0].length; j++)
					{
						graphics.addLink( parallelGateway.getId(),IDbyNode[0][j] );
					}
					
				}
			}
		}
		//=================================================================================//
				//遍历这个图，得到图中所有编排任务的id
				//根据每个中间事件的id，判断是否满足第三规则
				//规则为中间事件前面的结点书等于1，后面的结点数大于1
				//满足则进一步处理
		allID = graphics.getIds();
		if(allID.get(IntermediateThrowEvent.class.getName())!=null)
		{
			intermediateThrowEventID = allID.get(IntermediateThrowEvent.class.getName()).toArray(new String[allID.get(IntermediateThrowEvent.class.getName()).size()]);
			for (int i=0 ; i< intermediateThrowEventID.length ; i++ )
			{
				IDbyNode = graphics.getIDbyNode(intermediateThrowEventID[i]);
				if(IDbyNode[1].length==1&&IDbyNode[0].length>1)
				{
					graphics.removeNode(intermediateThrowEventID[i]);
					
					ParallelGateway parallelGateway = new ParallelGateway(Flag.getID());
					graphics.addNode(parallelGateway);
					IntermediateThrowEvent intermediateThrowEvent = new IntermediateThrowEvent(Flag.getID(), "intermediateThrowEv");
					graphics.addNode(intermediateThrowEvent);
					SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(), intermediateThrowEvent.getId(),parallelGateway.getId());
					graphics.addNode(sequenceFlow);
					
					graphics.addLink(intermediateThrowEvent.getId(),sequenceFlow.getId());
					graphics.addLink(sequenceFlow.getId(),parallelGateway.getId());
					graphics.addLink(IDbyNode[1][0],intermediateThrowEvent.getId());
					
					for(int j = 0 ; j < IDbyNode[1].length; j++)
					{
						graphics.addLink(parallelGateway.getId(), IDbyNode[0][j] );
					}
					
				}
			}
		}
		
		return null;
	}
}
