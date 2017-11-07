package Petrinet;

public class Transition {

	public String id;
	public String Y = null;
	public String name;
	public String X;
	
	public Transition(String id,String name,String X,String Y) 
	{
		this.id = id;
		this.name = name;
		this.X = X;
		this.Y = Y;
	}

}
