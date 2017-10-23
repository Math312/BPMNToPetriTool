package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import ynu.edu.data.Graphics;
import ynu.edu.data.BPMN.BPMNData;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.StartEvent;
import ynu.edu.util.analyze.AnalyzeTool;

//===============================================//
//测试类，使用test 1

public class TestOne {
	
	public static void main(String[] args) throws DocumentException {
		Hashtable<String,LinkedList<String>> allIds;
		String startId;
		
		
		// TODO Auto-generated method stub
		Document doc = new SAXReader().read("TestBpmn\\test 1.bpmn");
		// System.out.println(doc.getRootElement().getName());
		BPMNData<BpmnElement> data = new BPMNData();
		new AnalyzeTool().getNodes(doc.getRootElement(), data);
		Graphics<BpmnElement> graphics = new Graphics<>(data);
	//	String[][] h =  graphics.getIDbyNode("_2_CT");
		
		TransformRuleOne tro = new TransformRuleOne();
		boolean flag = tro.matches(graphics);
		System.out.println(flag);
		
		if(flag)
		{
			tro.transfer(graphics);
		}
		System.out.println("");
		
		
		
		allIds = graphics.getIds();
		startId = allIds.get(StartEvent.class.getName()).getFirst();
		System.out.println(startId);
		
		System.out.println(graphics.getIDbyNode(startId)[0][0]);
		System.out.println(graphics.getIDbyNode("2")[1][0]);
		
		
		
		
		
		
		//System.out.println(data);
		
		
		
	}

}
