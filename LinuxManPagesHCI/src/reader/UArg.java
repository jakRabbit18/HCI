package reader;

public class UArg { 
	String call, longCall, description;
	
	public UArg(String c, String lc, String d) {
		this.call = c;
		this.longCall = lc;
		this.description = d;
	}
	
	public UArg(String c, String d){
		this(c, "", d);
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getLongCall() {
		return longCall;
	}

	public void setLongCall(String longCall) {
		this.longCall = longCall;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
