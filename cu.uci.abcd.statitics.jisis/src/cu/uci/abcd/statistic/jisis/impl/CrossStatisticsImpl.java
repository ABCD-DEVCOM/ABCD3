package cu.uci.abcd.statistic.jisis.impl;

import cu.uci.abcd.statistic.jisis.CrossStatistics;
import cu.uci.abcd.statistic.jisis.JISISStatisticsProvider;
import cu.uci.abcd.statistic.jisis.domain.StatisticParam;
import cu.uci.abcd.statistic.jisis.domain.TabularStatistic;
import cu.uci.abcd.statistic.jisis.utils.SumarizedOperation;

public class CrossStatisticsImpl implements CrossStatistics {

	private JISISStatisticsProvider provider;

	@Override
	public TabularStatistic buildStatistics(StatisticParam statisticParam) {
		try {
			return sumarize(getStructure(statisticParam));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private TabularStatistic sumarize(TabularStatistic tb) {
		SumarizedOperation sumarizedOperation = new SumarizedOperation();
		
		//se llenan los totales de las filas y las columnas con los valores estadisticos.
		tb.setRowSumarize(sumarizedOperation.makeRowSumarize(tb.getData()));
		tb.setColumSumarize(sumarizedOperation.makeColumSumarize(tb.getData()));
		
		//se llena el valor total.
		tb.setTotal(sumarizedOperation.getTotal(tb));
		return tb;
	}

	private TabularStatistic getStructure(StatisticParam statisticParam) throws Exception {
		
		synchronized (provider) {
			TabularStatistic statistic = new TabularStatistic();
			if (statisticParam.getOptions().isEmpty()) {
				return provider.getValuesFromMfn(statisticParam, statistic);
			} else {
				return provider.getValuesFromLucene(statisticParam, statistic);
			}
		}
		
	}

	public JISISStatisticsProvider getProvider() {
		return provider;
	}

	public void setProvider(JISISStatisticsProvider provider) {
		this.provider = provider;
	}

}
