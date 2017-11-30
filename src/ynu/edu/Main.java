package ynu.edu;

import java.io.IOException;

import org.dom4j.DocumentException;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.rule.BPMNtoBPMN.Preprocessing;
import ynu.edu.module.rule.BPMNtoPetri.TransferInterface;
import ynu.edu.util.analyze.AnalyzeTool;
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
			Preprocessing.preprocessing(graphics);
			Graphics<PetriElement> result =transfer.transfer(graphics);
			FileUtils.BPMNtoEasyXml(result, outputFilePath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
