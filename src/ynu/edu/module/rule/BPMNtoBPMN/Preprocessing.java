package ynu.edu.module.rule.BPMNtoBPMN;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;

public class Preprocessing {

	public static Graphics<BpmnElement> preprocessing(Graphics<BpmnElement> graphics) 
	{
		
		TransformRuleOne t1 = new TransformRuleOne();
		TransformRuleTwo t2 = new TransformRuleTwo();
		TransformRuleThree t3 = new TransformRuleThree();
		TransformRuleFour t4 = new TransformRuleFour();
		TransformRuleFive t5 = new TransformRuleFive();
		TransformRuleSix t6 = new TransformRuleSix();
		TransformRuleSeven t7 = new TransformRuleSeven();
		
		while(true) 
		{
			if(t1.matches(graphics)) 
			{
				t1.transfer(graphics);
			}
			else if(t2.matches(graphics)) 
			{
				t2.transfer(graphics);
			}
			else if(t3.matches(graphics)) 
			{
				t3.transfer(graphics);
			}
			else if(t4.matches(graphics)) 
			{
				t4.transfer(graphics);
			}
			else if(t5.matches(graphics))
			{
				t5.transfer(graphics);
			}	
			else if(t6.matches(graphics)) 
			{
				t6.transfer(graphics);
			}
			else if(t7.matches(graphics)) 
			{
				t7.transfer(graphics);
			}
			else 
			{
				break;
			}
		}
		return graphics;
	}
	
}
