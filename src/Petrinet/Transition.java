package Petrinet;

public class Transition {
	public String name;
	public String id;
	
	//模块坐标
	public String X;
	public String Y;
	

	public Transition(String id,String name,String X,String Y) {
		// TODO Auto-generated constructor stub
		this.id=id;this.name=name;
		this.X=X;this.Y=Y;

	}

	public String getid() {
		return id;
	}
	public String getname() {
		return name;
	}
	public String getX() {
		return X;
	}
	public String getY() {
		return Y;
	}

}
