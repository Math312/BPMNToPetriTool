package ynu.edu.util.filechange;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import ynu.edu.data.Graphics;
import ynu.edu.data.BPMN.BPMNData;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Place;
import ynu.edu.module.petri.Transition;

public class FileUtils {
	public static void BPMNtoEasyXml(	Graphics<PetriElement> g,String fileName) throws IOException {

		/**
		 * 用DOM4J创建xml文档		 
		 */

		// 创建文档及设置根元素节点的方式
		Element root = DocumentHelper.createElement("pnml");
		Document document = DocumentHelper.createDocument(root);
		Element net = root.addElement("net").addAttribute("id", "Net-One").addAttribute("type", "P/T net");
		Element token = net.addElement("token").addAttribute("id", "Default").addAttribute("enabled", "true").addAttribute("red", "0").addAttribute("green", "0").addAttribute("blue", "0");
		Hashtable<String, LinkedList<String>> ids = g.getIds();
		for(Entry<String, LinkedList<String>> str:ids.entrySet()){
				//System.out.println(str.getKey());
	
		
		   if("ynu.edu.module.petri.Place".equals(str.getKey())|| "ynu.edu.module.petri.Transition".equals(str.getKey())||"ynu.edu.module.petri.Arc".equals(str.getKey())){
			   for(String value:str.getValue()){
				   
				   String[] strs=str.getKey().split("\\.");
				
				   String node=strs[strs.length-1].toLowerCase();
				   
				   Element element=net.addElement(node);
				   element.addAttribute("id", value);
				   if("place".equals(node)){
					   Element element2 = element.addElement("name");
					   element2.addElement("value").setText(((Place)g.getNodeData(value)).getName());
				   }else if("transition".equals(node)){
					   if(((Transition)g.getNodeData(value)).getName()!=null){
						   Element element2 = element.addElement("name");
					element2.addElement("value").setText(((Transition)g.getNodeData(value)).getName());
					   }
				   }else{
//					   System.out.println(value);
					   String[] strs2 = value.split("to");
					   element.addAttribute("target", strs2[1].trim()).addAttribute("source", strs2[0].trim());
				   }
				   Element element3 = element.addElement("capacity");
					element3.addElement("value").setText(0+"");
							
				}
				
		   }
			
		}
		
		// 把生成的xml文档存放在硬盘上 true代表是否换行
		OutputFormat format = new OutputFormat("    ", true);
		format.setEncoding("UTF-8");// 设置编码格式
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);
		xmlWriter.write(document);
		xmlWriter.close();

	}
}