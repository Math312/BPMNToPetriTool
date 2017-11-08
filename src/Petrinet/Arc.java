package Petrinet;

public class Arc {
	public String source;
	public String target;
	public String id;
	
	public Arc(String source,String target) {
		// TODO Auto-generated constructor stub

		this.source=source;
		this.target=target;
	}
	public String getsource() {
		return source;
	}
	public String gettarget() {
		return target;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}