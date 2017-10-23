package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleSeven extends AbstractRule{
	
	Graphics graphics;
	Hashtable<String,LinkedList<String>> allID;
	String [] allParallelGatewayID;
	String [] allExclusiveGatewayID;
	String [][] IDbyNode ;
	boolean flag1;
	boolean flag2;
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < allParallelGatewayID.length; i++)
		{
			
			IDbyNode = graphics.getIDbyNode(allParallelGatewayID[i]);
			for(int j = 0;i<allParallelGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
					return true;
			}
			for(int j = 0;i<allExclusiveGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
					return true;
			}
		}
		
		for (int i = 0 ; i < allExclusiveGatewayID.length; i++)
		{
			
			IDbyNode = graphics.getIDbyNode(allExclusiveGatewayID[i]);
			String tempID = graphics.getIDbyNode(IDbyNode[0][0])[0][0];
			for(int j = 0;i<allParallelGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
					return true;
			}
			for(int j = 0;i<allExclusiveGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
					return true;
			}
		}
		
		return false;
	}

	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = graphics.getIds();
		allParallelGatewayID = allID.get("parallelGateway").toArray(new String[allID.get("parallelGateway").size()]);
		allExclusiveGatewayID = allID.get("exclusiveGateway").toArray(new String[allID.get("exclusiveGateway").size()]);
		
		// 假设第一个网关是平行网关
		//满足规则七后删除中间的序列流及联系
		for (int i = 0 ; i < allParallelGatewayID.length; i++)
		{
			
			IDbyNode = graphics.getIDbyNode(allParallelGatewayID[i]);
			String tempID = graphics.getIDbyNode(IDbyNode[0][0])[0][0];
			for(int j = 0;j<allParallelGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
					flag1=true;
			}
			for(int j = 0;j<allExclusiveGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
					flag2=true;
			}
			if(flag1||flag2)
			{
				graphics.removeNode(IDbyNode[0][0]); //删除序列流
				
				
				Choreography choreography = new Choreography(Flag.getID(), "choreography", null, null);
				graphics.addNode(choreography);
				SequenceFlow sequenceFlow1 =new SequenceFlow(Flag.getID(), allParallelGatewayID[i], choreography.getId());
				graphics.addNode(sequenceFlow1);
				SequenceFlow sequenceFlow2 =new SequenceFlow(Flag.getID(), choreography.getId(), tempID);
				graphics.addNode(sequenceFlow2);
				
				graphics.addLink(allParallelGatewayID[i],sequenceFlow1.getId());
				graphics.addLink(sequenceFlow1.getId(),choreography.getId());
				graphics.addLink(choreography.getId(),sequenceFlow2.getId());
				graphics.addLink(sequenceFlow2.getId(),tempID);
				
			}			
		}
		// 假设第一个网关是排他网关
		//满足规则七后删除中间的序列流及联系
		for (int i = 0 ; i < allExclusiveGatewayID.length; i++)
		{
			
			IDbyNode = graphics.getIDbyNode(allExclusiveGatewayID[i]);
			String tempID = graphics.getIDbyNode(IDbyNode[0][0])[0][0];
			for(int j = 0;j<allParallelGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
					flag1=true;
			}
			for(int j = 0;j<allExclusiveGatewayID.length; j++)
			{
				if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
					flag2=true;
			}
			if(flag1||flag2)
			{
				graphics.removeNode(IDbyNode[0][0]); //删除序列流
				
				
				Choreography choreography = new Choreography(Flag.getID(), "choreography", null, null);
				graphics.addNode(choreography);
				SequenceFlow sequenceFlow1 =new SequenceFlow(Flag.getID(), allExclusiveGatewayID[i], choreography.getId());
				graphics.addNode(sequenceFlow1);
				SequenceFlow sequenceFlow2 =new SequenceFlow(Flag.getID(), choreography.getId(), tempID);
				graphics.addNode(sequenceFlow2);
				
				
				graphics.addLink(allExclusiveGatewayID[i],sequenceFlow1.getId());
				graphics.addLink(sequenceFlow1.getId(),choreography.getId());
				graphics.addLink(choreography.getId(),sequenceFlow2.getId());
				graphics.addLink(sequenceFlow2.getId(),tempID);
				
			}			
		}
		
		return null;
	}

}
