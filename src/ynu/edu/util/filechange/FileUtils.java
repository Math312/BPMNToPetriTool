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

public class FileUtils {
	void BPMNtoEasyXml(	Graphics<PetriElement> g) throws IOException {

		/**
		 * 用DOM4J创建xml文档		 
		 */

		// 创建文档及设置根元素节点的方式
		Element root = DocumentHelper.createElement("pnml");
		Document document = DocumentHelper.createDocument(root);
		Hashtable<String, LinkedList<String>> ids = g.getIds();
		for(Entry<String, LinkedList<String>> str:ids.entrySet()){
		   if("place".equals(str.getKey())|| "transition".equals(str.getKey())||"arc".equals(str.getKey())){
			   Element element=document.addElement(str.getKey());
			   for(String value:str.getValue()){
					System.out.println(value);
					if(value.equals("id")){
						element.addAttribute("id", value);
					}else if(value.equals("name")){
						Element element2 = element.addElement("name");
						element2.addElement("value").setText(value);
						
					}
					if("place".equals(str.getKey())){
						Element element2 = element.addElement("capacity");
						element2.addElement("value").setText(0+"");
					}
							
				}
				
		   }
			
		}
		
		// 把生成的xml文档存放在硬盘上 true代表是否换行
		OutputFormat format = new OutputFormat("    ", true);
		format.setEncoding("GBK");// 设置编码格式
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("TestXML\\case 11.xml"), format);
		xmlWriter.write(document);
		xmlWriter.close();

	}
}
