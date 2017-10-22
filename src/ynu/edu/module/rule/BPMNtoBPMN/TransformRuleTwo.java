package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.EndEvent;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;
import ynu.edu.module.bpmn.StartEvent;

public class TransformRuleTwo extends AbstractRule{
	
	public TransformRuleTwo(Graphics g){
		this.graphics = g;
	}
	
	
	Graphics graphics;
	String endId;//结束事件的ID
	String IDbyEnd[];//存储开始事件之前结点的ID
	ExclusiveGateway exclusiveGateway ;//new一个并行网关
	Hashtable<String,LinkedList<String>> allID ;
	EndEvent endEvent ;
	
	
	//============================================================================//
	//删除结束事件、并新增一个结束事件、并行网关、序列流
	@Override
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		IDbyEnd = graphics.getIDbyNode(endId)[1];	
		allID = graphics.getIds();
		
		
		if(IDbyEnd.length > 1)
		{
			endId = allID.get("EndEvent").getFirst();//To Do
			graphics.removeNode(endId);	
			
			endEvent = new EndEvent(Flag.getID());
			exclusiveGateway = new ExclusiveGateway(Flag.getID());
			graphics.addNode(exclusiveGateway);
			
			SequenceFlow sflow = new SequenceFlow(Flag.getID(), exclusiveGateway.getId(), "EndEvent");
			
			graphics.addNode(endEvent);
			graphics.addLink(sflow.getId(),endId);
			graphics.addNode(sflow);
		}
	
		
		return null;
	}
	
	//===================================================================//
	//建立并行网关和之前的连接结束事件的结点的联系
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		for (int i = 0; i < IDbyEnd.length; i++){
			if (graphics.addLink(IDbyEnd[i], exclusiveGateway.getId()) == false){
				return false;
			}
		}
		return true;
	}

	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
