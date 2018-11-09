package cu.uci.abcd.cataloguing.util;

import java.util.ArrayList;
import java.util.Arrays;

public class AvailableDatabases {
	
private ArrayList<String> dataBases;
	
	public AvailableDatabases(){
		this.dataBases = new ArrayList<String>(Arrays.asList(
				"marc21",
				"Autoridades"
				));
	}
	
	public ArrayList<String> getDatabases() {
		return this.dataBases;
	}

}
