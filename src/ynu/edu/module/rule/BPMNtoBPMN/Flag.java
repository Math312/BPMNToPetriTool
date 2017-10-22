package ynu.edu.module.rule.BPMNtoBPMN;

public class Flag {
	private static int  flag = 0;
	public static String getID()
	{
		flag = flag +1;
		return ""+flag;
	}
}
