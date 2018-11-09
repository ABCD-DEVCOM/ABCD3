package cu.uci.abcd.statistic.jisis.domain;

import java.util.ArrayList;
import java.util.List;

public class StatisticParam {
	private String rowTag;  
	private String columTag;
	private int begin;
	private int end;
	private String database;
	private String home;	
	private List<Option> options;

	public StatisticParam(String rowTag, String columTag, int begin, int end, String database, String home) {
		super();
		this.rowTag = rowTag;
		this.columTag = columTag;
		this.begin = begin;
		this.end = end;
		this.database = database;
		this.home = home;
		options= new ArrayList<Option>(2);
	}

	public StatisticParam(List<Option> options, String rowTag, String columTag, String database, String home) {
		super();
		this.rowTag = rowTag;
		this.columTag = columTag;
		this.database = database;
		this.home = home;
		this.end = Integer.MAX_VALUE;
		this.begin= 0;
		this.options = options;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getRowTag() {
		return rowTag;
	}

	public void setRowTag(String rowTag) {
		this.rowTag = rowTag;
	}

	public String getColumTag() {
		return columTag;
	}

	public void setColumTag(String columTag) {
		this.columTag = columTag;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

}
