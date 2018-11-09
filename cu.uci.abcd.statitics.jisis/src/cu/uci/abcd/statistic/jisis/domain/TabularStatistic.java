package cu.uci.abcd.statistic.jisis.domain;

import java.util.ArrayList;
import java.util.List;

public class TabularStatistic {
	private List<String> columnNames;
	private List<String> rowNames;
	private double[][] data;
	private Sumarize rowSumarize;
	private Sumarize columSumarize;
	private double total;

	public Sumarize getRowSumarize() {
		return rowSumarize;
	}

	public Sumarize getColumSumarize() {
		return columSumarize;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<String> getRowNames() {
		return rowNames;
	}

	public void setRowNames(List<String> rowNames) {
		this.rowNames = rowNames;
	}

	public TabularStatistic() {
		super();
		rowNames = new ArrayList<String>();
		columnNames = new ArrayList<String>();
	}

	public double[][] getData() {
		if (data == null) {
			data = new double[rowNames.size()][columnNames.size()];
		}
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public void addComlumName(String value) {
		columnNames.add(value);
	}

	public void addRowName(String value) {
		rowNames.add(value);
	}

	public void setRowSumarize(Sumarize rowSumarize) {
		this.rowSumarize = rowSumarize;
	}

	public void setColumSumarize(Sumarize columSumarize) {
		this.columSumarize = columSumarize;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
