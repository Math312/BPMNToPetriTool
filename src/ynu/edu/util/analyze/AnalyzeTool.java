package ynu.edu.util.analyze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import ynu.edu.data.Graphics;
import ynu.edu.data.BPMN.BPMNData;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.Choreography;
import ynu.edu.module.bpmn.CommonElement;
import ynu.edu.module.bpmn.EventBasedGateway;
import ynu.edu.module.bpmn.EndEvent;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.IntermediateThrowEvent;
import ynu.edu.module.bpmn.ArrayElement;
import ynu.edu.module.bpmn.Association;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;
import ynu.edu.module.bpmn.StartEvent;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.rule.BPMNtoBPMN.*;
import ynu.edu.module.rule.BPMNtoPetri.*;
import ynu.edu.util.filechange.AddXY;
import ynu.edu.util.filechange.FileUtils;

public class AnalyzeTool {

	public static void main(String[] args) throws DocumentException {
		new AnalyzeTool().AnalyzeBpmn();

	}

	void AnalyzeBpmn() throws DocumentException {
		Document doc = new SAXReader().read("TestBpmn\\case 1.bpmn");
		// System.out.println(doc.getRootElement().getName());
		BPMNData<BpmnElement> data = new BPMNData();
		new AnalyzeTool().getNodes(doc.getRootElement(), data);
		Graphics<BpmnElement> graphics = new Graphics<>(data);

		
//		TransformRuleOne t1 = new TransformRuleOne();
//		TransformRuleTwo t2 = new TransformRuleTwo();
//		TransformRuleThree t3 = new TransformRuleThree();
//		TransformRuleFour t4 = new TransformRuleFour();
//		TransformRuleFive t5 = new TransformRuleFive();
//		TransformRuleSix t6 = new TransformRuleSix();
//		TransformRuleSeven t7 = new TransformRuleSeven();
//		
//		while(true) 
//		{
//			if(t1.matches(graphics)) 
//			{
//				t1.transfer(graphics);
//			}
//			else if(t2.matches(graphics)) 
//			{
//				t2.transfer(graphics);
//			}
//			else if(t3.matches(graphics)) 
//			{
//				t3.transfer(graphics);
//			}
//			else if(t4.matches(graphics)) 
//			{
//				t4.transfer(graphics);
//			}
//			else if(t5.matches(graphics))
//			{
//				t5.transfer(graphics);
//			}	
//			else if(t6.matches(graphics)) 
//			{
//				t6.transfer(graphics);
//			}
//			else if(t7.matches(graphics)) 
//			{
//				t7.transfer(graphics);
//			}
//			else 
//			{
//				break;
//			}
//		}
//		graphics.getIds();
//		boolean label = true;
		ChoreographyRule choreographyRule  = new ChoreographyRule();
		EndEventRule endEventRule = new EndEventRule();
		IntermediateEventRule intermediateEventRule = new IntermediateEventRule();
		SequenceFlowRule sequenceFlowRule = new SequenceFlowRule();
		StartEventRule startEventRule = new StartEventRule();
		Graphics<PetriElement> g =  new Graphics<>();
		ParallelGatewayRule parallelGateway = new ParallelGatewayRule();
		ExclusiveGatewayRule exclusiveGateway = new ExclusiveGatewayRule();
			
			if(exclusiveGateway.matches(graphics)) {
				exclusiveGateway.transfer(graphics, g);
			}
			if(parallelGateway.matches(graphics)) 
			{
				parallelGateway.transfer(graphics, g);
			}
			if(startEventRule.matches(graphics)) 
			{
				startEventRule.transfer(graphics, g);
			}
			if(choreographyRule.matches(graphics)) 
			{
				choreographyRule.transfer(graphics, g);
			}
			
			if(intermediateEventRule.matches(graphics)) 
			{
				intermediateEventRule.transfer(graphics, g);
			}
			if(endEventRule.matches(graphics)) 
			{
				endEventRule.transfer(graphics, g);
			}
			if(sequenceFlowRule.matches(graphics)) 
			{
				sequenceFlowRule.transfer(graphics, g);
			}
			
		Hashtable<String,LinkedList<String>> table = g.getIds();
		for(Entry<String,LinkedList<String>> e:table.entrySet()) 
		{
			LinkedList<String> s = e.getValue();
			System.out.println("\n");
			for(String s1:s) 
			{
				System.out.print(s1+"      ");
			}
		}
		
		try {
			FileUtils.BPMNtoEasyXml(g, "TestXml\\result 1.xml");
			AddXY.addXY("TestXml\\result 1.xml", "TestXml\\result 2.xml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public BPMNData getNodes(final Element node, BPMNData bpmnData) {
		// System.out.println("-------开始新节点-------------");

		// 当前节点的名称、文本内容和属性
		// System.out.println("当前节点名称：" + node.getName());// 当前节点名称
		// 判断属性名
		// 判断事件并存储
		if ("association".equals(node.getName()) || "sequenceFlow".equals(node.getName())) {
			CommonElement commonElement = null;
			String nodename = null;
			String id = null;
			String incoming = null;
			String outgoing = null;

			final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
			for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
				final String name = attr.getName();// 属性名称
				final String value = attr.getValue();// 属性的值
				if (name.equals("id")) {
					id = value;
				} else if (name.equals("name")) {
					nodename = value;
				} else if (name.equals("sourceRef")) {
					incoming = value;
				} else if (name.equals("targetRef")) {
					outgoing = value;
				}
				// System.out.println("属性名称：" + name + "---->属性值：" + value);
			}
			// 由于incoming和outcoming属性是同级node，此处遍历查找

			if ("association".equals(node.getName())) {
				commonElement = new Association(id, incoming, outgoing);
			} else if ("sequenceFlow".equals(node.getName())) {
				commonElement = new SequenceFlow(id, incoming, outgoing);
			}

			bpmnData.addBPMNElement(commonElement);
			// System.out.println(nodename+id+incoming+outgoing+"");
		}
		// 判断网关类型并存储
		else if ("choreographyTask".equals(node.getName()) || "startEvent".equals(node.getName())
				|| "endEvent".equals(node.getName()) || "eventBasedGateway".equals(node.getName())
				|| "exclusiveGateway".equals(node.getName())|| "parallelGateway".equals(node.getName())|| "intermediateThrowEvent".equals(node.getName())) {
			ArrayElement arrayElement = null;
			String nodename = null;
			String id = null;
			ArrayList<String> incoming = new ArrayList<>();
			ArrayList<String> outgoing = new ArrayList<>();
	        String[] participants = new String[2];
	        int count=0;

			final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
			for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
				final String name = attr.getName();// 属性名称
				final String value = attr.getValue();// 属性的值
				if (name.equals("id")) {
					id = value;
				} else if (name.equals("name")) {
					nodename = value;
				}
				// System.out.println("属性名称：" + name + "---->属性值：" + value);
			}
			// 由于incoming和outcoming属性是同级node，此处遍历查找
			List<Element> le = node.elements();
			for (final Element e : le) {// 遍历所有一级子节点
				if (e.getName().equals("incoming")){
					incoming.add(e.getText());
				}else if (e.getName().equals("outgoing")) {
					outgoing.add(e.getText());
				}else if (e.getName().equals("participantRef")) {
					participants[count]=e.getText();
					count++;
				}
			}
			count=0;
			if ("choreographyTask".equals(node.getName())) {
				arrayElement = new Choreography(id, nodename, participants[0], participants[1]);
			} else if ("startEvent".equals(node.getName())) {
				arrayElement = new StartEvent(id);
			} else if ("endEvent".equals(node.getName())) {
				arrayElement = new EndEvent(id);
			}else if ("eventBasedGateway".equals(node.getName())) {
				arrayElement = new EventBasedGateway(id);
			}else if ("exclusiveGateway".equals(node.getName())) {
				arrayElement = new ExclusiveGateway(id);
			}else if ("parallelGateway".equals(node.getName())) {
				arrayElement = new ParallelGateway(id);
			}else if ("intermediateThrowEvent".equals(node.getName())) {
				arrayElement = new IntermediateThrowEvent(id, nodename);
			}

			if(incoming != null && incoming.size()!=0){
				for (String str : incoming) {
					arrayElement.AppendInComing(str);
				}
			}
			if(outgoing != null && outgoing.size()!=0){
			for (String str : outgoing) {
				arrayElement.AppendOutGoing(str);
			}
			}
			bpmnData.addBPMNElement(arrayElement);
			// System.out.println(nodename+id+incoming+outgoing+"");
		}
		// 递归遍历当前节点所有的子节点
		final List<Element> listElement = node.elements();// 所有一级子节点的list
		for (final Element e : listElement) {// 遍历所有一级子节点
			getNodes(e, bpmnData);// 递归
		}

		return bpmnData;

	}
	
}