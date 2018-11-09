package cu.uci.abcd.statistic.jisis.utils;

import java.util.Iterator;

import cu.uci.abcd.statistic.jisis.domain.Sumarize;
import cu.uci.abcd.statistic.jisis.domain.TabularStatistic;

public class SumarizedOperation {
	
	//se fija la fila y se suman las columnas para obtener el total.
	public Sumarize makeRowSumarize(double[][] data) {
		Sumarize rowSumarize = new Sumarize();
		for (int row = 0; row < data.length; row++) {
			double sum = 0;
			for (int column = 0; column < data[row].length; column++) {
				sum += data[row][column];
			}
			
			rowSumarize.getTotals().add(sum);
		}

		return rowSumarize;
	}
	
	//se fija la columna y se suman las filas para obtener el total.
	public Sumarize makeColumSumarize(double[][] data) {
		Sumarize columSumarize = new Sumarize();
		int colums = 0;
		for (int colum = 0; colum < data[colums].length; colum++) {
			double sum = 0;
			for (int row = 0; row < data.length; row++) {
				sum += data[row][colum];
			}
			columSumarize.getTotals().add(sum);
		}
		return columSumarize;
	}

	public double getTotal(TabularStatistic tb) {
		double total = 0;
		
		//se escoge la menor de las listas de totales y se suma para obtener el total general.
		if (tb.getRowSumarize().getTotals().size() <= tb.getColumSumarize().getTotals().size())
			for (Iterator<Double> iterator = tb.getRowSumarize().getTotals().iterator(); iterator.hasNext();) 
				total += iterator.next();
		else
			for (Iterator<Double> iterator = tb.getColumSumarize().getTotals().iterator(); iterator.hasNext();) 
				total += iterator.next();
		return total;
	}

}
