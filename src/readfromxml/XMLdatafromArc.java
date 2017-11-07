package readfromxml;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.DocumentException;
import org.dom4j.Element;
public class XMLdatafromArc extends XML{



	ArrayList<String> id=new ArrayList<>();
	ArrayList<String> source=new ArrayList<>();
	ArrayList<String> target=new ArrayList<>();
	

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
	
	public XMLdatafromArc(String path) throws DocumentException {
		super(path);
		this.setId_Name();
//		this.setXY();
		
		/*this.setXY();*/
		// TODO Auto-generated constructor stub
	}
	
	
	public void setId_Name(){
		for(int i=0;i<this.getRoot().element("net").elements("arc").size();i++){					
			this.id.add(((Element)(this.getRoot().element("net").elements("arc").get(i))).attributeValue("id"));
			this.source.add(((Element)(this.getRoot().element("net").elements("arc").get(i))).attributeValue("source"));
			this.target.add(((Element)(this.getRoot().element("net").elements("arc").get(i))).attributeValue("target"));
		}
	}
	

	
	public ArrayList<String> getId() {
		return id;
	}
	public ArrayList<String> getS() {
		return source;
	}
	public ArrayList<String> getT() {
		return target;
	}

}


