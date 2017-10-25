package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.IntermediateThrowEvent;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleThree extends AbstractRule {
	Graphics graphics;
	Hashtable<String,LinkedList<String>> allID ;
	String [] choreographyID;//编排任务id
	String [] intermediateThrowEventID;//中间事件id
	String [][] IDbyNode ;//得到编排任务周围结点的id

	
	



	@Override
	//=================================================================================//
	//遍历这个图，得到图中所有编排任务的id
	//根据每个编排任务的id，判断是否满足第三规则
	//规则为编排任务前面的结点书大于1，后面的结点数等于1
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
			if(IDbyNode[0].length==1&&IDbyNode[1].length>1)
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
				if(IDbyNode[0].length==1&&IDbyNode[1].length>1)
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
				if(IDbyNode[0].length==1&&IDbyNode[1].length>1)
				{
					System.out.println("1111111111111111111111");
					graphics.removeNode(choreographyID[i]);
					ExclusiveGateway exclusiveGateway = new ExclusiveGateway(Flag.getID());
					graphics.addNode(exclusiveGateway);
					
					Choreography  choreography = new Choreography(Flag.getID(), "choreography", null, null);
					graphics.addNode(choreography);
					SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(), exclusiveGateway.getId(),choreography.getId());	
					graphics.addNode(sequenceFlow);
					
					graphics.addLink(choreography.getId(), IDbyNode[0][0]);
					graphics.addLink(sequenceFlow.getId(), choreography.getId());
					graphics.addLink(exclusiveGateway.getId(), sequenceFlow.getId());
					
					for(int j = 0 ; j < IDbyNode[1].length; j++)
					{
						graphics.addLink(IDbyNode[1][j], exclusiveGateway.getId() );
					}
					
				}
			}
		}
		
		//==========================================================================================//
		//遍历这个图，得到图中所有中间节点的id
		//根据每个编排任务的id，判断是否满足第三规则
		//规则为中间节点前面的结点书大于1，后面的结点数等于1
		//满足则进一步处理
		if(allID.get(IntermediateThrowEvent.class.getName())!=null)
		{
			intermediateThrowEventID = allID.get(IntermediateThrowEvent.class.getName()).toArray(new String[allID.get(IntermediateThrowEvent.class.getName()).size()]);
			for (int i=0 ; i< intermediateThrowEventID.length ; i++ )
			{
				IDbyNode = graphics.getIDbyNode(intermediateThrowEventID[i]);
				if(IDbyNode[0].length==1&&IDbyNode[1].length>1)
				{
					System.out.println("2222222222222222");
					graphics.removeNode(intermediateThrowEventID[i]);
					
					ExclusiveGateway exclusiveGateway = new ExclusiveGateway(Flag.getID());
					graphics.addNode(exclusiveGateway);
					IntermediateThrowEvent intermediateThrowEvent = new IntermediateThrowEvent(Flag.getID(), "intermediateThrowEv");
					graphics.addNode(intermediateThrowEvent);				
					SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(), exclusiveGateway.getId(),intermediateThrowEvent.getId());
					graphics.addNode(sequenceFlow);
					
					graphics.addLink(sequenceFlow.getId(), intermediateThrowEvent.getId());
					graphics.addLink(exclusiveGateway.getId(), sequenceFlow.getId());
					graphics.addLink(intermediateThrowEvent.getId(), IDbyNode[0][0]);
					
					for(int j = 0 ; j < IDbyNode[1].length; j++)
					{
						graphics.addLink(IDbyNode[1][j], exclusiveGateway.getId() );
					}
					
				}
			}
		}
		return null;
	}
}
