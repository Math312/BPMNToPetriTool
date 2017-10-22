package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleSix extends AbstractRule{
	Graphics g;
	Hashtable<String,LinkedList<String>> h = g.getIds();
	String [] allID;
	String [][] IDbyNode ;
	
	public  TransformRuleSix(Graphics g) {
		// TODO Auto-generated constructor stub
		this.g = g;
	}
	
	
	
	
	//=================================================================================================//
	//遍历这个图，得到图中所有并行网关的id
	//根据每个并行网关的id，判断是否满足第六规则
	//规则为编排任务前面的结点数大于1，后面的结点数大于1
	//满足则进一步处理
	
	
	
	@Override
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = h.get("parallelGateway").toArray(new String[h.get("parallelGateway").size()]);
		// 把平行网关的所有id存入数组
		for (int i = 0 ; i < allID.length; i++)
		{
			IDbyNode = g.getIDbyNode(allID[i]);
			

			ParallelGateway parallelGateway1 = new ParallelGateway(Flag.getID());//new一个并行网关
			ParallelGateway parallelGateway2 = new ParallelGateway(Flag.getID());//new一个并行网关
			
			if(IDbyNode[0].length<2 || IDbyNode[1].length<2)
			{
				continue; //如果不满足前后各个联系大于1，就跳过
			}
			g.removeNode(allID[i]);
			
			graphics.addNode(parallelGateway1);
			graphics.addNode(parallelGateway2);
			
			for (int j = 0; j < IDbyNode[1].length; j++){
				g.addLink(IDbyNode[1][j],parallelGateway1.getId()) ;
			}
			for (int j = 0; j < IDbyNode[0].length; j++){
				g.addLink(parallelGateway2.getId(), IDbyNode[0][j]);
			}
			SequenceFlow sequenceFlow =new SequenceFlow (Flag.getID(),parallelGateway1.getId(),parallelGateway2.getId());
			graphics.addNode(sequenceFlow);
		}
		
		//=================================================================================================//
		//遍历这个图，得到图中所有排他网关的id
		//根据每个排他网关的id，判断是否满足第六规则
		//规则为编排任务前面的结点数大于1，后面的结点数大于1
		//满足则进一步处理
		
		allID = h.get("exclusiveGateway").toArray(new String[h.get("xclusiveGateway").size()]);
		for (int i = 0 ; i < allID.length; i++)
		{
			IDbyNode = g.getIDbyNode(allID[i]);
			
			ExclusiveGateway exclusiveGateway1 = new ExclusiveGateway(Flag.getID());//new一个排他网关
			ExclusiveGateway exclusiveGateway2 = new ExclusiveGateway(Flag.getID());//new一个排他网关
			
			if(IDbyNode[0].length<2 || IDbyNode[1].length<2)
			{
				continue; //如果不满足前后各个联系大于1，就跳过
			}
			g.removeNode(allID[i]);
			
			graphics.addNode(exclusiveGateway1);
			graphics.addNode(exclusiveGateway2);
			
			for (int j = 0; j < IDbyNode[1].length; j++){
				g.addLink(IDbyNode[1][j],exclusiveGateway1.getId()) ;
			}
			for (int j = 0; j < IDbyNode[0].length; j++){
				g.addLink(exclusiveGateway2.getId(), IDbyNode[0][j]);
			}
			SequenceFlow sequenceFlow =new SequenceFlow (Flag.getID(),exclusiveGateway1.getId(),exclusiveGateway2.getId());
			graphics.addNode(sequenceFlow);
			
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
