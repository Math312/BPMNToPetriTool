package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.GNode;
import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.Association;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.SequenceFlow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransformRuleFive extends AbstractRule{
	Hashtable<String,LinkedList<String>> allID;
	String[] choreographyID;//存储信息流的ID
	String [][] IDbyNode ;//得到编排任务周围结点的id
	String information[];//存储第信息

	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		allID = graphics.getIds();
		if (allID.get(Choreography.class.getName())!=null){
			choreographyID = allID.get(Choreography.class.getName()).toArray(new String[allID.get(Choreography.class.getName()).size()]);
			for(int i = 0; i < choreographyID.length; i++){
				GNode g = graphics.getNodeData(choreographyID[i]);
				Choreography c = (Choreography)g;
				if (c.getParticipants()[0] != null && c.getParticipants()[1] != null){
					return true;
				}
				
				
			}
		}
		return false;
	}
	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		allID = graphics.getIds();
		if (allID.get(Choreography.class.getName())!=null){
			choreographyID = allID.get(Choreography.class.getName()).toArray(new String[allID.get(Choreography.class.getName()).size()]);
			for (int i = 0; i < choreographyID.length; i++){
				GNode g = graphics.getNodeData(choreographyID[i]);
				Choreography c = (Choreography)g;
				if (c.getParticipants()[0] != null && c.getParticipants()[1] != null){
					information = c.getParticipants();//存储编排任务的信息之后在移除结点
					IDbyNode = graphics.getIDbyNode(c.getId());
					graphics.removeNode(c.getId());
					
					
					Choreography  choreographyOne = new Choreography(Flag.getID(), "choreography", information[0], null);
					graphics.addNode(choreographyOne);
					Choreography  choreographyTwo = new Choreography(Flag.getID(), "choreography", null,information[1]);
					graphics.addNode(choreographyTwo);
					SequenceFlow sequenceflow = new SequenceFlow(Flag.getID(), choreographyOne.getId(), choreographyTwo.getId());
					graphics.addNode(sequenceflow);
					//添加链接
					graphics.addLink(choreographyOne.getId(), sequenceflow.getId());
					graphics.addLink(sequenceflow.getId(),choreographyTwo.getId());
					for(int j = 0 ; j < IDbyNode[1].length; j++)
					{
						graphics.addLink(IDbyNode[1][j], choreographyOne.getId() );
					}
					for(int j = 0 ; j < IDbyNode[1].length; j++)
					{
						graphics.addLink(choreographyTwo.getId(),IDbyNode[0][j]);
					}
					
				}
			}
		}
		return null;
	}

}
