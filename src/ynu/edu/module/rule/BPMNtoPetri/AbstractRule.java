package ynu.edu.module.rule.BPMNtoPetri;

/**
 * 所有规则所继承的一个抽象类
 * @author Hao
 */

import java.util.*;
import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;

public abstract class AbstractRule {
	
	protected static int place_id = 0;	
	protected static int arc_id = 0;
	protected static int trans_id = 0;
	protected static ArrayList<BpmnAndPetri> nodes = new ArrayList<>();		// 用于存储所有的bpmn与其petri元素的对应信息
	
	public abstract boolean matches(Graphics<BpmnElement> graphics);		// 用于匹配Bpmn图中是否存在相应的Bpmn元素
	
	public abstract void transfer(Graphics<BpmnElement> graphics,Graphics<PetriElement> result);	// Bpmn元素转换为Petri元素
	
}
