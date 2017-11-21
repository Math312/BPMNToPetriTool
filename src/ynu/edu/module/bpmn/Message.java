package ynu.edu.module.bpmn;

public class Message extends CommonElement{

	//信息流传递流向
	public Message(String id,String inComing,String outGoing) 
	{
		super.id = id;
		super.inComing = inComing;
		super.outGoing = outGoing;
		super.name = null;
	}
	
	
}
