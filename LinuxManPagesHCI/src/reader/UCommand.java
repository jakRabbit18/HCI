package reader;

import java.util.ArrayList;
import java.util.List;

public class UCommand {

	String name, synopsis, description;
	ArrayList<UArg> args;
	
	public UCommand(String name, String synopsis, String description, ArrayList<UArg> args) {
		this.name = name;
		this.synopsis = synopsis;
		this.description = description;
		this.args = args;
	}
	
	public UCommand(String name, String synopsis, String description){
		this(name, synopsis, description, new ArrayList<UArg>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<UArg> getArgs() {
		return args;
	}
	
	public UArg getArgByName(String name){
		for(UArg arg: args){
			if(arg.getCall().equals(name)||arg.getLongCall().equals(name)){
				return arg;
			}
		}		
		return null;
	}
	
	public void addArgList(List<UArg> toAdd){
		this.args.addAll(toAdd);
	}
	
	public void addArg(UArg arg){
		this.args.add(arg);
	}
	
	public String toString(){
		String s = "Command Name: " + this.name + "\n";
		s += "Command Synopsis: " + this.synopsis + "\nOptional Args:";
		for(UArg a: this.args){
			s+= " " + a.call;
		}
		return s;
	}

}
