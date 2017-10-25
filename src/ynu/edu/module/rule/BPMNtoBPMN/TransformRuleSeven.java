package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRuleSeven extends AbstractRule{
	
	Hashtable<String,LinkedList<String>> allID;
	String [] allParallelGatewayID;
	String [] allExclusiveGatewayID;
	String [][] IDbyNode ;
	boolean flag1;
	boolean flag2;
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		allID = graphics.getIds();
		if(allID.get(ParallelGateway.class.getName()) != null)
		{
			allParallelGatewayID = allID.get(ParallelGateway.class.getName()).toArray(new String[allID.get(ParallelGateway.class.getName()).size()]);
		// TODO Auto-generated method stub
		
			for (int i = 0 ; i < allParallelGatewayID.length; i++)
			{
				
				IDbyNode = graphics.getIDbyNode(allParallelGatewayID[i]);
				for(int j = 0;j < allParallelGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
						if(graphics.getIDbyNode(IDbyNode[0][0])[0].length != 0)
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
								return true;
				}
				if(allID.get(ExclusiveGateway.class.getName()) != null)
				{
					allExclusiveGatewayID = allID.get(ExclusiveGateway.class.getName()).toArray(new String[allID.get(ExclusiveGateway.class.getName()).size()]);
					for(int j = 0;j<allExclusiveGatewayID.length; j++)
					{
						if(IDbyNode[0].length!= 0)
							if( graphics.getIDbyNode(IDbyNode[0][0])[0].length != 0)
								if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
									return true;
					}
				}
			}
		}
		if(allID.get(ExclusiveGateway.class.getName()) != null)
		{
			allExclusiveGatewayID = allID.get(ExclusiveGateway.class.getName()).toArray(new String[allID.get(ExclusiveGateway.class.getName()).size()]);
			for (int i = 0 ; i < allExclusiveGatewayID.length; i++)
			{
				IDbyNode = graphics.getIDbyNode(allExclusiveGatewayID[i]);
				if(allID.get(ParallelGateway.class.getName()) != null)
				{
					allParallelGatewayID = allID.get(ParallelGateway.class.getName()).toArray(new String[allID.get(ParallelGateway.class.getName()).size()]);
					
					for(int j = 0;j <allParallelGatewayID.length; j++)
					{
						if(IDbyNode[0].length != 0 )
							if( graphics.getIDbyNode(IDbyNode[0][0])[0].length !=0)
								if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
									return true;
					}
				}
				for(int j = 0;j <allExclusiveGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
						if( graphics.getIDbyNode(IDbyNode[0][0])[0].length != 0)
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
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
		// 假设第一个网关是排他网关
		//满足规则七后删除中间的序列流及联系
		if (allID.get(ParallelGateway.class.getName()) != null)
		{
			allParallelGatewayID = allID.get(ParallelGateway.class.getName()).toArray(new String[allID.get(ParallelGateway.class.getName()).size()]);
			// 假设第一个网关是平行网关
			//满足规则七后删除中间的序列流及联系
			for (int i = 0 ; i < allParallelGatewayID.length; i++)
			{
				
				IDbyNode = graphics.getIDbyNode(allParallelGatewayID[i]);
				for(int j = 0;j<allParallelGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
					{
						if(graphics.getIDbyNode(IDbyNode[0][0])[0].length !=0)
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
								flag1=true;
					}
					else
					{
						flag1 = false;
					}
				}
				for(int j = 0;j<allExclusiveGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
					{
						if( graphics.getIDbyNode(IDbyNode[0][0])[0].length !=0)	
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
								flag2=true;
					}
					else
					{						
						flag2 = false;
					}
				}
				if(flag1||flag2)
				{
					String tempID = graphics.getIDbyNode(IDbyNode[0][0])[0][0];
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
					flag1 = false;
					flag2 = false;
					
				}			
			}
		}
		// 假设第一个网关是排他网关
		//满足规则七后删除中间的序列流及联系
		if(allID.get(ExclusiveGateway.class.getName())!=null)
		{
			allExclusiveGatewayID = allID.get(ExclusiveGateway.class.getName()).toArray(new String[allID.get(ExclusiveGateway.class.getName()).size()]);
			for (int i = 0 ; i < allExclusiveGatewayID.length; i++)
			{
				
				IDbyNode = graphics.getIDbyNode(allExclusiveGatewayID[i]);
				for(int j = 0;j<allParallelGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
						if(graphics.getIDbyNode(IDbyNode[0][0])[0].length !=0)
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
								flag1=true;
					else
					{
						flag1 = false;
					}
					
				}
				for(int j = 0;j<allExclusiveGatewayID.length; j++)
				{
					if(IDbyNode[0].length != 0)
						if(graphics.getIDbyNode(IDbyNode[0][0])[0].length != 0)
							if(graphics.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
								flag2=true;
					else
					{						
						flag2 = false;
					}
				}
				if(flag1||flag2)
				{
					String tempID = graphics.getIDbyNode(IDbyNode[0][0])[0][0];
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
		}
		return null;
	}

}
