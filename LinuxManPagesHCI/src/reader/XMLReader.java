package reader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** re-make this whole class so that it reads the xml into a 
 * object that matchs the layout of the xml but is usable in java
 * e.g. read each node into its own field of a "UnixCommand" object
 * @author Rett
 *
 */
public class XMLReader {

	private File file;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	
	public XMLReader(String xmlFileName) {
		// TODO Auto-generated constructor stub
		try{
			file = new File(xmlFileName);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
		}catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			System.out.println("invalid filename");
			e.printStackTrace();
		}
	}
	
	public static UCommand readXMLToCommand(File file){
		return readXMLToCommand(file.getPath());
	}

	public static UCommand readXMLToCommand(String xmlFileName){
		XMLReader reader = new XMLReader(xmlFileName);
		String name, syn, des, cat;
		
		NodeList nList = reader.doc.getElementsByTagName("name");
		name = nList.item(0).getTextContent();
		
		nList = reader.doc.getElementsByTagName("synopsis");
		syn = nList.item(0).getTextContent();
		
		nList = reader.doc.getElementsByTagName("description");
		des = nList.item(0).getTextContent();
		
		nList = reader.doc.getElementsByTagName("category");
		cat = nList.item(0).getTextContent();
		
		ArrayList<UArg> args = reader.getOptionalArguments();
		UCommand com = new UCommand(name, syn, des, cat, args);
		return com;
	}

	/**
	 * reads the given attribute from the given file
	 * @param attribute : the name of the attribute you would like to read
	 * @return ArrayList of all the text elements
	 */
//	public ArrayList<String> readElement(String xmlFileName, String )
	public ArrayList<String> getWholeAttribute(String attribute){
		ArrayList<String> attributeVals = new ArrayList<String>();
			// list of nodes to be read
			NodeList nList = doc.getElementsByTagName(attribute);

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NamedNodeMap nnm = eElement.getAttributes();
					for(int i = 0; i < nnm.getLength(); i++){
						attributeVals.add(nnm.item(i).getTextContent());
					}
				}

				attributeVals.add(nNode.getTextContent()+ " ");
			}
		return attributeVals;
	}
	
	/**
	 * returns a list of all the optional arguments and their descriptions
	 * @return
	 */
	public ArrayList<UArg> getOptionalArguments(){
		ArrayList<UArg> args = new ArrayList<UArg>();
		NodeList nList = doc.getElementsByTagName("arg");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element e = (Element) nNode;
			String c, lc, d;
			c = e.getAttribute("call");
			lc = e.getAttribute("longCall");
			d = e.getTextContent();
			UArg arg;
			if(lc.equals("")){
				arg = new UArg(c, d);
			} else {
				arg = new UArg(c, lc, d);
			}
			
			args.add(arg);			
		}
		return args;
	}

//	public static void main(String argv[]){
//		System.out.println(XMLReader.readXMLToCommand("./XML_Files/cd.xml"));
//
//	}

	//	
	//	public static void main(String argv[]) {
	//		try {
	//
	//			File fXmlFile = new File("C:/Users/Rett/workspace/LinuxManPagesHCI/XML_Files/ls.xml");
	//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	//			Document doc = dBuilder.parse(fXmlFile);
	//			doc.getDocumentElement().normalize();
	//
	//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	//			NodeList nList = doc.getElementsByTagName("command");
	//			System.out.println("----------------------------");
	//
	//			for (int temp = 0; temp < nList.getLength(); temp++) {
	//				Node nNode = nList.item(temp);
	//				System.out.print("\nCurrent Element: " + nNode.getNodeName() + "  ");
	//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	//					Element eElement = (Element) nNode;
	//					System.out.println(eElement.getAttribute("call"));
	//				}
	//				
	//				System.out.println(nNode.getTextContent()+ " ");
	//
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

}
