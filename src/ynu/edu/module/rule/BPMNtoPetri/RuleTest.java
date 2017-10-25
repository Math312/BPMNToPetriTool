package ynu.edu.module.rule.BPMNtoPetri;

import org.junit.*;
import org.junit.Test;
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
import ynu.edu.module.petri.PetriElement;
import ynu.edu.util.analyze.AnalyzeTool;

public class RuleTest {
	
	@Test
	public void test() throws DocumentException {
		StartEventRule ser_rule = new StartEventRule();
		IntermediateEventRule inter_rule = new IntermediateEventRule();
		EndEventRule end_rule = new EndEventRule();
		ChoreographyRule chore_rule = new ChoreographyRule();
		SequenceFlowRule sf_rule = new SequenceFlowRule();
		Document doc = new SAXReader().read("TestBpmn\\case 1.bpmn");
		BPMNData<BpmnElement> data = new BPMNData();
		new AnalyzeTool().getNodes(doc.getRootElement(), data);
		Graphics<BpmnElement> graphics = new Graphics<>(data);
		String[][] h = graphics.getIDbyNode("_2_CT");
		Graphics<PetriElement> result = new Graphics<PetriElement>();
//		ser_rule.transfer(graphics, result);
//		inter_rule.transfer(graphics, result);
//		end_rule.transfer(graphics, result);
//		chore_rule.transfer(graphics, result);
//		sf_rule.transfer(graphics, result);
	}
	
}
