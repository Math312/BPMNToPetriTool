package ynu.edu;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.dom4j.DocumentException;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.rule.BPMNtoBPMN.Preprocessing;
import ynu.edu.module.rule.BPMNtoPetri.TransferByRule1;
import ynu.edu.module.rule.BPMNtoPetri.TransferInterface;
import ynu.edu.util.analyze.AnalyzeTool;
import ynu.edu.util.filechange.AddXY;
import ynu.edu.util.filechange.FileUtils;

public class Main {

	private TransferInterface transfer;
	
	public Main(TransferInterface transferInterface) 
	{
		this.transfer = transferInterface;
	}
	
	private AnalyzeTool analyzeTool = new AnalyzeTool();
	private FileUtils fileUtil = new FileUtils();
	
	public void transfer(String inputFilePath,String outputFilePath) 
	{
		try {
			Graphics<BpmnElement> graphics =  analyzeTool.AnalyzeBpmn(inputFilePath);
			graphics = Preprocessing.preprocessing(graphics);
			Graphics<PetriElement> result =transfer.transfer(graphics);
			Hashtable<String, LinkedList<String>> data = result.getIds();
			for(Entry<String,LinkedList<String>> e : data.entrySet()) 
			{
				System.out.println();
				for(String s : e.getValue()) 
				{
					System.out.print(s+"   " );
				}
			}
			FileUtils.BPMNtoEasyXml(result, outputFilePath);
			AddXY.addXY(outputFilePath, outputFilePath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String []  args) 
	{
		Main main = new Main(new TransferByRule1());
		main.transfer("TestBpmn\\case 2.bpmn","TestXml\\case 2.xml");
	}
}
