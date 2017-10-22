package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.GNode;
import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;
import ynu.edu.module.bpmn.StartEvent;

public class TransformRuleOne extends AbstractRule{
	public TransformRuleOne(Graphics g){
		this.graphics = g;
	}
	
	Graphics graphics;
	String startId;//开始事件的ID
	String idByStart[] ;//存储开始事件之后结点的ID
	ParallelGateway parallelGateway ;//new一个并行网关
	Hashtable<String,LinkedList<String>> allIds = graphics.getIds();
	//SequenceFlow sequenceFlow = new SequenceFlow("");
	StartEvent startEvent; 
	
	//删除开始事件，并新增一个开始事件、平行网关、序列流
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		startId = allIds.get("StartEvent").getFirst();//To Do得到开始事件的ID
		idByStart = graphics.getIDbyNode(startId)[0];//得到开始事件之后的id
		if(idByStart.length > 1)
		{
			graphics.removeNode(startId);

			startEvent = new StartEvent("startId");  //新增一个开始事件
			graphics.addNode(startEvent);
			
			parallelGateway = new ParallelGateway(Flag.getID());
			graphics.addNode(parallelGateway);  //增加一个平行网关
			
			SequenceFlow flow = new SequenceFlow(Flag.getID(),startId,parallelGateway.getId());
			graphics.addNode(flow);
			
			graphics.addLink(startId, flow.getId());
		}
		
		
		
		return null;
	}
	
	
	
	//让新增的结点之间建立联系
	public boolean matches(Graphics<BpmnElement> graphics) {
		for (int i = 0; i < idByStart.length; i++){
			if (graphics.addLink(parallelGateway.getId(), idByStart[i]) == false){
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
