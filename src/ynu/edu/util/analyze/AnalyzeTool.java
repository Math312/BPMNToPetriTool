package ynu.edu.util.analyze;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
		String[][] h =  graphics.getIDbyNode("_2_CT");
		System.out.println(data);

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