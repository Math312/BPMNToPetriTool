package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.IntermediateThrowEvent;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleFour extends AbstractRule {
	
	Graphics g;
	Hashtable<String,LinkedList<String>> allID = g.getIds();
	String [] choreographyID;
	String [] intermediateThrowEventID;
	String [][] IDbyNode ;
	public  TransformRuleFour(Graphics g) {
		// TODO Auto-generated constructor stub
		this.g = g;
	}
	@Override
	//=================================================================================//
		//遍历这个图，得到图中所有编排任务的id
		//根据每个编排任务的id，判断是否满足第三规则
		//规则为编排任务前面的结点书等于1，后面的结点数大于1
		//满足则进一步处理
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		choreographyID = allID.get("choreography").toArray(new String[allID.get("choreography").size()]);
		for (int i=0 ; i< choreographyID.length ; i++ )
		{
			IDbyNode = graphics.getIDbyNode(choreographyID[i]);
			if(IDbyNode[1].length==1 && IDbyNode[0].length>1)
			{
				graphics.removeNode(choreographyID[i]);
				
				ExclusiveGateway exclusiveGateway = new ExclusiveGateway(Flag.getID());
				Choreography  choreography = new Choreography(Flag.getID(), "choreography", null, null);
				SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(),choreography.getId(), exclusiveGateway.getId());
				graphics.addNode(exclusiveGateway);
				graphics.addNode(choreography);
				graphics.addNode(sequenceFlow);
				graphics.addLink(IDbyNode[1][0],choreography.getId());
				
				for(int j = 0 ; j < IDbyNode[0].length; j++)
				{
					graphics.addLink( exclusiveGateway.getId(),IDbyNode[0][j] );
				}
				
			}
		}
		
		//=================================================================================//
				//遍历这个图，得到图中所有编排任务的id
				//根据每个中间事件的id，判断是否满足第三规则
				//规则为中间事件前面的结点书等于1，后面的结点数大于1
				//满足则进一步处理
		intermediateThrowEventID = allID.get("intermediateThrowEvent").toArray(new String[allID.get("intermediateThrowEvent").size()]);
		for (int i=0 ; i< intermediateThrowEventID.length ; i++ )
		{
			IDbyNode = graphics.getIDbyNode(intermediateThrowEventID[i]);
			if(IDbyNode[1].length==1&&IDbyNode[0].length>1)
			{
				graphics.removeNode(intermediateThrowEventID[i]);
				
				ExclusiveGateway exclusiveGateway = new ExclusiveGateway(Flag.getID());
				IntermediateThrowEvent intermediateThrowEvent = new IntermediateThrowEvent(Flag.getID(), "intermediateThrowEv");
				SequenceFlow sequenceFlow = new SequenceFlow(Flag.getID(), intermediateThrowEvent.getId(),exclusiveGateway.getId());
				graphics.addNode(exclusiveGateway);
				graphics.addNode(intermediateThrowEvent);
				graphics.addNode(sequenceFlow);
				
				graphics.addLink(IDbyNode[1][0],intermediateThrowEvent.getId());
				
				for(int j = 0 ; j < IDbyNode[1].length; j++)
				{
					graphics.addLink(exclusiveGateway.getId(), IDbyNode[0][j] );
				}
				
			}
		}
		return null;
	}
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return null;
	}
}
