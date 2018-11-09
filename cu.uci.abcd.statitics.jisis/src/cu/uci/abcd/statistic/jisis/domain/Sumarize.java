package cu.uci.abcd.statistic.jisis.domain;

import java.util.ArrayList;
import java.util.List;

public class Sumarize {
	private String name;   				
	private List<String> values = new ArrayList<String>();	
	private List<Double> totals = new ArrayList<Double>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<Double> getTotals() {
		return totals;
	}

	public void setTotals(List<Double> totals) {
		this.totals = totals;
	}


}
