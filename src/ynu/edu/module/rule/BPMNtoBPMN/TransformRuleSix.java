package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleSix extends AbstractRule{
	Graphics graphics;
	Hashtable<String,LinkedList<String>> allId;
	String [] parallelGatewayID;
	String [] exclusiveGatewayID;
	String [][] IDbyNode ;
	//=================================================================================================//
	//遍历这个图，得到图中所有并行网关的id
	//根据每个并行网关的id，判断是否满足第六规则
	//规则为编排任务前面的结点数大于1，后面的结点数大于1
	//满足则进一步处理
	
	
	
	

	
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allId = graphics.getIds();
		// 把平行网关的所有id存入数组
		if(allId.get(ParallelGateway.class.getName())!=null)
		{
			parallelGatewayID = allId.get(ParallelGateway.class.getName()).toArray(new String[allId.get(ParallelGateway.class.getName()).size()]);
		// 把平行网关的所有id存入数组
			for (int i = 0 ; i < parallelGatewayID.length; i++)
			{
				IDbyNode = graphics.getIDbyNode(parallelGatewayID[i]);
				if(IDbyNode[0].length>1 && IDbyNode[1].length>1)
				{
					return true;
				}
			}
		}
		if(allId.get(ExclusiveGateway.class.getName())!=null)
		{
			exclusiveGatewayID = allId.get(ExclusiveGateway.class.getName()).toArray(new String[allId.get(ExclusiveGateway.class.getName()).size()]);
			for (int i = 0 ; i < exclusiveGatewayID.length; i++)
			{
				IDbyNode = graphics.getIDbyNode(exclusiveGatewayID[i]);
				if(IDbyNode[0].length>1 && IDbyNode[1].length>1)
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
		allId = graphics.getIds();
		if(allId.get(ParallelGateway.class.getName())!=null)
		{
			parallelGatewayID = allId.get(ParallelGateway.class.getName()).toArray(new String[allId.get(ParallelGateway.class.getName()).size()]);
			// 把平行网关的所有id存入数组
			for (int i = 0 ; i < parallelGatewayID.length; i++)
			{
				IDbyNode = graphics.getIDbyNode(parallelGatewayID[i]);
				if(IDbyNode[0].length<2 || IDbyNode[1].length<2)
				{
					continue; //如果不满足前后各个联系大于1，就跳过
				}
				graphics.removeNode(parallelGatewayID[i]);
				
				ParallelGateway parallelGateway1 = new ParallelGateway(Flag.getID());//new一个并行网关
				ParallelGateway parallelGateway2 = new ParallelGateway(Flag.getID());//new一个并行网关
				
				
				graphics.addNode(parallelGateway1);
				graphics.addNode(parallelGateway2);
				
				for (int j = 0; j < IDbyNode[1].length; j++){
					graphics.addLink(IDbyNode[1][j],parallelGateway1.getId()) ;
				}
				for (int j = 0; j < IDbyNode[0].length; j++){
					graphics.addLink(parallelGateway2.getId(), IDbyNode[0][j]);
				}
				SequenceFlow sequenceFlow =new SequenceFlow (Flag.getID(),parallelGateway1.getId(),parallelGateway2.getId());
				graphics.addNode(sequenceFlow);
				graphics.addLink(parallelGateway1.getId(), sequenceFlow.getId());
				graphics.addLink(sequenceFlow.getId(),parallelGateway2.getId());
				
				
			}
		}
		//=================================================================================================//
		//遍历这个图，得到图中所有排他网关的id
		//根据每个排他网关的id，判断是否满足第六规则
		//规则为编排任务前面的结点数大于1，后面的结点数大于1
		//满足则进一步处理
		if(allId.get(ExclusiveGateway.class.getName())!=null)
		{
			exclusiveGatewayID = allId.get(ExclusiveGateway.class.getName()).toArray(new String[allId.get(ExclusiveGateway.class.getName()).size()]);
			for (int i = 0 ; i < exclusiveGatewayID.length; i++)
			{
				IDbyNode = graphics.getIDbyNode(exclusiveGatewayID[i]);
				
				if(IDbyNode[0].length<2 || IDbyNode[1].length<2)
				{
					continue; //如果不满足前后各个联系大于1，就跳过
				}
				graphics.removeNode(exclusiveGatewayID[i]);
				
				ExclusiveGateway exclusiveGateway1 = new ExclusiveGateway(Flag.getID());//new一个排他网关
				ExclusiveGateway exclusiveGateway2 = new ExclusiveGateway(Flag.getID());//new一个排他网关
				
				
				graphics.addNode(exclusiveGateway1);
				graphics.addNode(exclusiveGateway2);
				
				for (int j = 0; j < IDbyNode[1].length; j++){
					graphics.addLink(IDbyNode[1][j],exclusiveGateway1.getId()) ;
				}
				for (int j = 0; j < IDbyNode[0].length; j++){
					graphics.addLink(exclusiveGateway2.getId(), IDbyNode[0][j]);
				}
				SequenceFlow sequenceFlow =new SequenceFlow (Flag.getID(),exclusiveGateway1.getId(),exclusiveGateway2.getId());
				graphics.addNode(sequenceFlow);
				
				graphics.addLink(exclusiveGateway1.getId(), sequenceFlow.getId());
				graphics.addLink(sequenceFlow.getId(),exclusiveGateway2.getId());
				
			}	
		}
		return null;
	}

}
