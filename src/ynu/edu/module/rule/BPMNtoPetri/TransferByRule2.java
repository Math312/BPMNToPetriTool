package ynu.edu.module.rule.BPMNtoPetri;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;

public class TransferByRule2 implements TransferInterface{

	@Override
	public Graphics<PetriElement> transfer(Graphics<BpmnElement> graphics) 
	{
		ChoreographyRule choreographyRule  = new ChoreographyRule();
		EndEventRule endEventRule = new EndEventRule();
		IntermediateEventRule intermediateEventRule = new IntermediateEventRule();
		SequenceFlowRule2 sequenceFlowRule = new SequenceFlowRule2();
		StartEventRule startEventRule = new StartEventRule();
		Graphics<PetriElement> g =  new Graphics<>();
		ParallelGatewayRule parallelGateway = new ParallelGatewayRule();
		ExclusiveGatewayRule exclusiveGateway = new ExclusiveGatewayRule();
			
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
			if(exclusiveGateway.matches(graphics)) {
				exclusiveGateway.transfer(graphics, g);
			}
			if(parallelGateway.matches(graphics)) 
			{
				parallelGateway.transfer(graphics, g);
			}
			if(endEventRule.matches(graphics)) 
			{
				endEventRule.transfer(graphics, g);
			}
			if(sequenceFlowRule.matches(graphics)) 
			{
				sequenceFlowRule.transfer(graphics, g);
			}
			return g;
			
	}
}
