package reader;

import java.util.ArrayList;
import java.util.List;

public class CommandSearch {

	List<UCommand> commands;

	public CommandSearch(List<UCommand> commands) {
		this.commands = commands;
	}
	
	public List<UCommand> searchByString(String searchString){
		List<UCommand> results = new ArrayList<UCommand>();
		for(UCommand c: this.commands){
			if(similar(c.name, searchString)){
				results.add(c);
			}
			if(similar(c.description, searchString)){
				results.add(c);
			}
		}
		return results;
	}
	
	boolean similar(String string1, String string2){
		String[] split1 = string1.split("\\s+");
		String[] split2 = string2.split("\\s+");
		float threshold = (float) (split2.length * 0.50);
		int count = 0;
		for(String s1: split1){
			for(String s2: split2){
				if(s1.equals(s2)){
					count++;
				}
			}
		}
		return count >= threshold;
	}

}
