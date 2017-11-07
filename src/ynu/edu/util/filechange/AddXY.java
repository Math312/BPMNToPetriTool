package ynu.edu.util.filechange;

import java.awt.Color;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import Petrinet.Arc;
import Petrinet.Place;
import Petrinet.Transition;
import readfromxml.XMLdatafromArc;
import readfromxml.XMLdatafromPlace;
import readfromxml.XMLdatafromTransition;



public class AddXY {
	public static void main(String args[])throws DocumentException, IOException{
		// input file
		File file = new File("F:/2"); 
        String test[]; 
        //list()�������Զ�ȡ����ǰ�ļ��������ļ� 
        test = file.list(); 
        for (int num = 0; num < test.length; num++) { 
           // System.out.println("E:\\models\\bounded-models\\a2\\"+test[num]); 
        
		ArrayList<Arc> a=new ArrayList<>();
		ArrayList<Place> p=new ArrayList<>();
		ArrayList<Transition> t=new ArrayList<>();
		String S="F:/2/"+test[num];
		
		XMLdatafromPlace P=new XMLdatafromPlace(S);// place sets
		XMLdatafromTransition T=new XMLdatafromTransition(S);// transition sets
		XMLdatafromArc A=new XMLdatafromArc(S); // arc sets
		
		Object[] Pv=new Object[P.getId().size()];  // numbers of places
		Object[] Tv=new Object[T.getId().size()];



		
		// Creates graph with model
				mxGraph graph = new mxGraph();
				Object parent = graph.getDefaultParent();
				
				// Sets the default vertex style
				Map<String, Object> style = graph.getStylesheet().getDefaultVertexStyle();
				style.put(mxConstants.STYLE_GRADIENTCOLOR, "#FFFFFF");
				style.put(mxConstants.STYLE_ROUNDED, true);
				style.put(mxConstants.STYLE_SHADOW, true);

				graph.getModel().beginUpdate();
				try
				{

					for(int i=0;i<P.getId().size();i++){
						Pv[i]=graph.insertVertex(parent, null, P.getId().get(i), 0, 0, 40, 20);
					}
					for(int i=0;i<T.getId().size();i++){
						Tv[i]=graph.insertVertex(parent, null, T.getId().get(i), 0, 0, 40, 20);
					}
					
                    // arc from place to transition
					for(int i=0;i<A.getId().size();i++){
						//System.out.println("A");
						for(int k=0;k<T.getId().size();k++){
							//System.out.println("T");
							if(A.getS().get(i).equals(((mxCell)graph.addCell(Tv[k])).getValue())){
								for(int j=0;j<P.getId().size();j++){
									//System.out.println("P");
									if(A.getT().get(i).equals(((mxCell)graph.addCell(Pv[j])).getValue())){
										graph.insertEdge(parent, null, A.getId().get(i),Tv[k] , Pv[j]);
									}
								}
							}
						}
					}
					// arc from transition to place
					for(int i=0;i<A.getId().size();i++){
						for(int k=0;k<T.getId().size();k++){
							if(A.getT().get(i).equals(((mxCell)graph.addCell(Tv[k])).getValue())){
								for(int j=0;j<P.getId().size();j++){
									if(A.getS().get(i).equals(((mxCell)graph.addCell(Pv[j])).getValue())){
										graph.insertEdge(parent, null, A.getId().get(i),Pv[j] , Tv[k]);
									}
								}
							}
						}
					}
                    // setting position
					mxIGraphLayout layout = new mxHierarchicalLayout(graph);
					layout.execute(parent);
					
					// setting place's position
					for(int i=0;i<P.getId().size();i++){
						p.add(new Place(String.valueOf(((mxCell)graph.addCell(Pv[i])).getValue()),
								String.valueOf(((mxCell)graph.addCell(Pv[i])).getValue()),
								String.valueOf(((mxCell)graph.addCell(Pv[i])).getGeometry().getCenterY()),
								String.valueOf(((mxCell)graph.addCell(Pv[i])).getGeometry().getCenterX())
								));
					}
					
					// setting transition's position
					for(int i=0;i<T.getId().size();i++){
						t.add(new Transition(String.valueOf(((mxCell)graph.addCell(Tv[i])).getValue()),
								String.valueOf(((mxCell)graph.addCell(Tv[i])).getValue()),
								String.valueOf(((mxCell)graph.addCell(Tv[i])).getGeometry().getCenterY()),
								String.valueOf(((mxCell)graph.addCell(Tv[i])).getGeometry().getCenterX())
								));
					}
					

				}
				finally
				{
					graph.getModel().endUpdate();
				}

				// Creates an image than can be saved using ImageIO
//				BufferedImage image = mxCellRenderer.createBufferedImage(graph, null,
//						1, Color.WHITE, true, null);

				// For the sake of this example we display the image in a window
//				JFrame frame = new JFrame("Graph image");
//				frame.getContentPane().add(new JLabel(new ImageIcon(image)));
//				frame.pack();
//				frame.setVisible(true);
				
				
				
				//��ҳ����
		        Element root = DocumentHelper.createElement("pnml");
		        Document document = DocumentHelper.createDocument(root);
		        
		        //�����ڵ���Ӻ��ӽڵ�  
		        Element element1 = root.addElement("net");  
		        element1.addAttribute("id", "Net-One").addAttribute("type", "P/T net");  

		        Element element2=element1.addElement("token");
		        element2.addAttribute("id","Default").addAttribute("enabled","true").addAttribute("red","0").addAttribute("green","0").addAttribute("blue","0");
		        
		        //place
		        for(int i=0;i<p.size();i++){
		        	Element element3=element1.addElement("place");
		            element3.addAttribute("id",p.get(i).name);
		            
		            Element element4=element3.addElement("graphics");
		            element4.addElement("position").addAttribute("x",p.get(i).X).addAttribute("y",p.get(i).Y);
		            
		            Element element5=element3.addElement("name");
		            element5.addElement("value").addText(p.get(i).name);
		            element5.addElement("graphics").addElement("position").addAttribute("x", "0").addAttribute("y","0");
		            
		            Element element6=element3.addElement("initialMarking");
		            element6.addElement("value").addText("Default,0");
		            element6.addElement("graphics").addElement("offset").addAttribute("x", "0.0").addAttribute("y", "0.0");
		            
		            Element element7=element3.addElement("capacity");
		            element7.addElement("value").addText("0");
		        }
		        
		        //transition
		        for(int i=0;i<t.size();i++){
		        	Element element8=element1.addElement("transition");
		            element8.addAttribute("id", t.get(i).name);
		            
		            Element element9=element8.addElement("graphics");
		            element9.addElement("position").addAttribute("x", t.get(i).X).addAttribute("y", t.get(i).Y);
		            
		            Element element10=element8.addElement("name");
		            element10.addElement("value").addText(t.get(i).name);
		            element10.addElement("graphics").addElement("offset").addAttribute("x", "0").addAttribute("y", "0");
		            
		            Element element11=element8.addElement("orientation");
		            element11.addElement("value").addText("0");
		            
		            Element element12=element8.addElement("rate");
		            element12.addElement("value").addText("1.0");
		            
		            Element element13=element8.addElement("timed");
		            element13.addElement("value").addText("false");
		            
		            Element element14=element8.addElement("infiniteServer");
		            element14.addElement("value").addText("false");
		            
		            Element element15=element8.addElement("priority");
		            element15.addElement("value").addText("1");
		        }
		        
		        //Arc
		        for(int i=0;i<A.getId().size();i++){
		        	Element element16=element1.addElement("arc");
		            element16.addAttribute("id", A.getS().get(i)+" to "+A.getT().get(i)).addAttribute("source",A.getS().get(i)).addAttribute("target",A.getT().get(i));
		            
		            element16.addElement("graphics");
		            
		            Element element17=element16.addElement("inscription");
		            element17.addElement("value").addText("Default,1");
		            element17.addElement("graphics");
		            
		            Element element18=element16.addElement("tagged");
		            element18.addElement("value").addText("false");
		            
		          
		            element16.addElement("type").addAttribute("value", "normal");

		        } 
		    
		        //save
		        OutputFormat format = new OutputFormat("    ",true);  
		        format.setEncoding("GBK");//���ñ����ʽ  
		        // output file
		        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("F:/2\\"+test[num]+".xml"),format);  
		      
		        xmlWriter.write(document);
		        xmlWriter.close();
			}
	}
}
