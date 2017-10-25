/***********************************************************************
 * Module:  Graphics.java
 * Author:  邵闫利
 * Purpose: Defines the Class Graphics
 ***********************************************************************/

package ynu.edu.data;

import java.util.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import ynu.edu.data.BPMN.BPMNData;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;
import ynu.edu.module.petri.Place;
import ynu.edu.util.analyze.AnalyzeTool;

/** @pdOid afafb82b-d5d1-4ed1-8022-4380ffb63c7b */
public class Graphics <T extends GNode> {
   /** @pdOid a42c56d0-1673-41a9-9167-0eef9d4901a4 */
	//存储数据所用数据结构
	
   private Hashtable<String, LinkedList<HeadNode<? extends GNode>>> data;
   
   /** @pdOid a430e1f1-4392-4509-bcb0-4d688e01c1e3 */
   
   
   //==============================================================================================//
   //获得所有节点的ID
   
   public Hashtable<String,LinkedList<String>> getIds()
   {
	   Hashtable<String,LinkedList<String>> result = new Hashtable();
	   Set<Entry<String, LinkedList<Graphics<T>.HeadNode<? extends GNode>>>> dataSet = data.entrySet(); 
	   for(Entry<String, LinkedList<Graphics<T>.HeadNode<? extends GNode>>>  e: dataSet) 
	   {
		   LinkedList<HeadNode<? extends GNode>> list = e.getValue();
		   String type = e.getKey();
		   LinkedList<String> temp = new LinkedList<>();
		   for(HeadNode<? extends GNode> node:list) 
		   {
			   temp.add(node.getData().id);
		   }
		   result.put(type, temp);
	   }
	   return result;
	}
   
   //===========================================================================================//
   //获得特定ID的结点的连接结点的ID
   //返回一个二维数组，数组是2Xn形式
   //result[0] 为后集ID
   //result[1] 为前集ID
   
   public String[][] getIDbyNode(String id)
   {
	   ArrayList<String> nextList = new ArrayList<>();
	   ArrayList<String> preList = new ArrayList<>();
	   HeadNode<? extends GNode> node = this.getNode(id);
	   Node next = node.next;
	   Node pre = node.pre;
	   while(next != null) 
	   {
		   nextList.add(next.id);
		   next = next.next;
	   }
	   
	   while(pre != null) 
	   {
		   preList.add(pre.id);
		   pre = pre.next;
	   }
	   
	   String[][] result = new String[2][];
	   result[0] = new String[nextList.size()];
	   result[1] = new String[preList.size()];
	   
	   nextList.toArray(result[0]);
	   preList.toArray(result[1]);
	   return result;
	   
	   
   }
   
  //===============================================================================================//
   //构造器
   
   
   
   //一般构造器
   public <T extends GNode> Graphics() {
   	this.data = new Hashtable();
   }
   
   /** @param sourcedata
    * @pdOid fc99eefe-f34e-4e5b-9316-97e1a3e56c27 */
   
   
   
   //利用BPMNData的构造器
   public <TextendsGNode> Graphics(BPMNData<T> sourcedata) {
   	this.data = new Hashtable();
   	Hashtable<String, LinkedList<T>> source = sourcedata.getData();
   	Set<Entry<String, LinkedList<T>>> sourceSet = source.entrySet();
   	for(Entry<String, LinkedList<T>> e : sourceSet) 
   	{
   		LinkedList<T> list = e.getValue();
   		for(T oneNode:list) 
   		{
   			this.addNode(oneNode);
   		}
   	}
   	
   	for(Entry<String, LinkedList<T>> e : sourceSet) 
   	{
   		LinkedList<T> list = e.getValue();
   		for(T oneNode:list) 
   		{
   			BpmnElement element = (BpmnElement)oneNode;
   			String inComing = element.getInComing();
   			String outGoing = element.getOutGoing();
   			String id = element.id;
   			String[] inComingArray = inComing.split("\\|");
   			String[] outGoingArray = outGoing.split("\\|");
   			for(int i = 0;i < inComingArray.length;i ++) 
   			{
   				addLink(inComingArray[i],id);
   			}
   			
//   			for(int i = 0;i < outGoingArray.length;i ++) 
//   			{
//   				addLink(id,outGoingArray[i]);
//   			}
   		}
   	}
   }
   
   //===========================================================================================//
   
   //添加结点
   
   /** @param node
    * @pdOid 7eec3987-5c22-4733-bcc5-cdb1d6e27a39 */
   public <T extends GNode> boolean addNode(T node) {
   	String key = node.getClass().getName();
   	HeadNode<T> head = new HeadNode(node);
   	if(data.containsKey(key)) 
   	{
   		LinkedList<HeadNode<? extends GNode>> list = this.data.get(key);
   		list.add(head);
   	}
   	else 
   	{
   		LinkedList<HeadNode<? extends GNode>> list = new LinkedList();
   		list.add(head);
   		this.data.put(node.getClass().getName(), list);
   	}
   	return true;
   }
   
   
   //==========================================================================================//
   //添加节点间的连接关系，通过ID传递，若无对应目标返回false,成功建立连接返回true
   
   
   
   /** @param from 
    * @param to
    * @pdOid bc6fbf8d-cb3e-46d2-9bb8-5e86ed5de985 */
   public boolean addLink(String from, String to) {
   	HeadNode<? extends GNode> fromNode = getNode(from); 
   	HeadNode<? extends GNode> toNode = getNode(to);
   	if(fromNode == null || toNode == null) 
   	{
   		return false;
   	}
   	if(fromNode.next == null) 
   	{
   		Node next = new Node(toNode);
   		fromNode.next = next;
   	}
   	else 
   	{
   		Node nextNode = fromNode.next;
   		while(nextNode.next != null) 
   		{
   			nextNode = nextNode.next;
   		}
   		nextNode.next = new Node(toNode);
   	}
   	
   	if(toNode.pre == null) 
   	{
   		Node pre = new Node(fromNode);
   		toNode.pre = pre;
   	}
   	else 
   	{
   		Node preNode = toNode.pre;
   		
   		while(preNode.next != null) 
   		{
   			preNode = preNode.next;
   		}
   		preNode.next = new Node(fromNode);
   	}
   	return true;
   }
   
   
   //=============================================================================================//
   
   //消除节点以及与其有关的关联关系
   
   /** @param id
    * @pdOid b6e267fe-8d2f-4d56-8961-315485b9bd58 */
   public boolean removeNode(String id) {
   	HeadNode<? extends GNode> node = getNode(id);
   	deleteNodeCheckLinkedList(node);
   	
   	if(node == null) 
   	{
   		return false;
   	}
   	Node next = node.next;
   	Node pre = node.pre;
   	
   	while(next != null) 
   	{
   		Node temp = next.next;
   		next.next = null;
   		next = temp;
   	} 
   	node.next = null;
   	
   	while(pre != null) 
   	{
   		Node temp = pre.next;
   		pre.next = null;
   		pre = temp;
   	}
   	node.pre = null;
   	
   	this.data.get(node.type).remove(node);
   	
   	
   	
   	return true;
   }
  
   //=================================================================================================//
   //获取节点信息
   
   
   public <T extends GNode> GNode getNodeData(String id) {
	   	for(Entry<String, LinkedList<HeadNode<? extends GNode>>> e:this.data.entrySet()) 
	   	{
	   		for(HeadNode<? extends GNode> headNode : e.getValue()) 
	   		{
	   			if(headNode.getData().id.equals(id)) 
	   			{
	   				return headNode.data;
	   			}
	   		}
	   	}
	   	return null;
	   	
   }
   
   
   
   
   
   //===============================================================================================//
   //获取节点
   /** @param id
    * @pdOid 23394ad3-e7d6-421b-93f3-7e8d882213ca */
  
   
   
   private <T extends GNode> HeadNode<? extends GNode> getNode(String id) {
   	for(Entry<String, LinkedList<HeadNode<? extends GNode>>> e:this.data.entrySet()) 
   	{
   		for(HeadNode<? extends GNode> headNode : e.getValue()) 
   		{
   			if(headNode.getData().id.equals(id)) 
   			{
   				return headNode;
   			}
   		}
   	}
   	
   	return null;
   }
   
   //=============================================================================================//
   private boolean deleteNodeInHeadNode(HeadNode<? extends GNode> head,String id) 
   {
	   Node<? extends GNode> nextNode = head.next;
	   if(nextNode.id.equals(id)) 
	   {
		   head.next = nextNode.next;
		   nextNode.next = null;
	   }
	   else 
	   {
		   Node nextPreNode = nextNode;
		   nextNode = nextNode.next;
		   while(nextNode != null) 
		   {
			   if(nextNode.id.equals(id)) 
			   {
				   nextPreNode.next = nextNode.next;
				   nextNode.next = null;
				   break;
			   }
			   else 
			   {
				   nextNode = nextNode.next;
				   nextPreNode = nextPreNode.next;
			   }
		   }
	   }
	   
	   nextNode = head.pre;
	   if(nextNode.id.equals(id)) 
	   {
		   head.pre = nextNode.next;
		   nextNode.next = null;
	   }
	   else 
	   {
		   Node nextPreNode = nextNode;
		   nextNode = nextNode.next;
		   while(nextNode != null) 
		   {
			   if(nextNode.id.equals(id)) 
			   {
				   nextPreNode.next = nextNode.next;
				   nextNode.next = null;
				   break;
			   }
			   else 
			   {
				   nextNode = nextNode.next;
				   nextPreNode = nextPreNode.next;
			   }
		   }
	   }
	   
	   return true;
	   
   }
   
   private boolean deleteNodeCheckLinkedList(HeadNode<? extends GNode> head) 
   {
	   Node nextNode = head.next;
	   while(nextNode != null) 
	   {
		   String id = nextNode.id;
		   String type = nextNode.type;
		   LinkedList<HeadNode<? extends GNode>> list = data.get(type);
		   for(HeadNode<? extends GNode> headNode: list) 
		   {
			   if(headNode.data.id.equals(id)) 
			   {
				   this.deleteNodeInHeadNode(getNode(id), head.data.id);
			   }
		   }
		   nextNode = nextNode.next;
	   
	   }
	   
	   nextNode = head.pre;
	   while(nextNode != null) 
	   {
		   String id = nextNode.id;
		   String type = nextNode.type;
		   LinkedList<HeadNode<? extends GNode>> list = data.get(type);
		   for(HeadNode<? extends GNode> headNode: list) 
		   {
			   if(headNode.data.id.equals(id)) 
			   {
				   this.deleteNodeInHeadNode(getNode(id), head.data.id);
			   }
		   }
		   nextNode = nextNode.next;
	   }
	   
	   return true;
   }
   
   /** @param args
    * @exception DocumentException
    * @pdOid d345a62e-2da2-48b9-a8a7-a628f169cc75 */
   public static void main(String[] args) throws DocumentException {
   	Document doc = new SAXReader().read("TestBpmn\\case 1.bpmn");
   	//System.out.println(doc.getRootElement().getName());
   	BPMNData<BpmnElement> data = new BPMNData();
   	new AnalyzeTool().getNodes(doc.getRootElement(),data);
   	Graphics<PetriElement> g =new Graphics();
   	
   	System.out.println("123");
   }

   /** @pdOid 437600f1-5c00-4ac8-a738-1fe600054b9d */
   private class HeadNode <T extends GNode> {
      /** @pdOid 326a945a-47b2-4137-94e8-b9d55a792eb8 */
      private T data;
      /** @pdOid e0966c11-29e5-45aa-9233-6b9468dfb503 */
      private String type;
      
      private Node pre;
      private Node next;
      
      /** @param data
       * @pdOid d2726c9d-3c08-4751-966a-ddbec3f3e997 */
      public HeadNode(T data) {
      	this.data = data;
      	this.type = data.getClass().getName();
      	this.pre = null;
      	this.next = null;	
      }
      
      /** @pdOid 31890bdc-51b5-4dd6-99fd-9801080ff8d9 */
      public T getData() {
      	return this.data;
      }
   
   }
   
   /** @pdOid 54512493-efa9-4414-857c-4de7eac0888c */
   private class Node<T extends GNode> {
      /** @pdOid d64e8954-8a63-4c9d-8ccc-c768c675e890 */
      private String type;
      /** @pdOid f15af256-b424-4b31-bbd8-28304d3a4f2e */
      private String id;
      
      private Node next;
      
      /** @param node
       * @pdOid 3cb9c32e-c7c3-4296-8b96-d0a410cb86d3 */
      public Node(HeadNode<? extends GNode> node) {
      	type = node.data.getClass().getName();
      	id = node.getData().id;
      	next = null;
      }
   
   }

}