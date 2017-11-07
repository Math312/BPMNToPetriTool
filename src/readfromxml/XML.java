package readfromxml;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XML {
	String path;
	Element root;
	XML(){};
	public String getPath() {
		return path;
	}
	public Element getRoot() {
		return root;
	}
	XML(String path) throws DocumentException{
		this.path=path;
		File file=new File(path);
		SAXReader xReader=new SAXReader();
		Document document=xReader.read(file);
		this.root=document.getRootElement();
	}
}
