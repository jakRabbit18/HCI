package reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public Main(){
		
	}
	
	public static void main(String argv[]) {
		File folder = new File("./XML_Files");
		File[] listOfFiles = folder.listFiles();
		List<UCommand> commands = new ArrayList<UCommand>();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	System.out.println(file.getName());
		    	System.out.println(XMLReader.readXMLToCommand(file));
		    	commands.add(XMLReader.readXMLToCommand(file));
		    }
		}
	}

}
