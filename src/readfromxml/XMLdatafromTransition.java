package readfromxml;


import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;
import org.dom4j.Element;
public class XMLdatafromTransition extends XML{



	ArrayList<String> id=new ArrayList<>();
	ArrayList<String> x1=new ArrayList<>();
	ArrayList<String> y1=new ArrayList<>();
	

//	public void setXY(){
//		Element pass=root.element("BPMNDiagram").element("BPMNPlane");
//		List elements=pass.elements();
//		for(int i=0;i<elements.size();i++){
//			for(int j=0;j<id.size();j++){
//				if(((Element)elements.get(i)).attributeValue("bpmnElement").equals(id.get(j))){
//					this.x1.add(((Element)elements.get(i)).element("Bounds").attributeValue("x"));
//					this.y1.add(((Element)elements.get(i)).element("Bounds").attributeValue("y"));
//				}
//			}
//		}
//	}
	
	public XMLdatafromTransition(String path) throws DocumentException {
		super(path);
		this.setId_Name();
//		this.setXY();
		
		/*this.setXY();*/
		// TODO Auto-generated constructor stub
	}
	
	
	public void setId_Name(){
		for(int i=0;i<this.getRoot().element("net").elements("transition").size();i++){					
			this.id.add(((Element)(this.getRoot().element("net").elements("transition").get(i))).attributeValue("id"));
			this.x1.add("0");
			this.y1.add("0");
		}
	}
	

	
	public ArrayList<String> getId() {
		return id;
	}
	public ArrayList<String> getX() {
		return x1;
	}
	public ArrayList<String> getY() {
		return y1;
	}

}


