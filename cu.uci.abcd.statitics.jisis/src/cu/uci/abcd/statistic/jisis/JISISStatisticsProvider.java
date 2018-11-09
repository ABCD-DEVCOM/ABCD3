package cu.uci.abcd.statistic.jisis;

import org.unesco.jisis.corelib.common.FieldSelectionTable;

import cu.uci.abcd.statistic.jisis.domain.StatisticParam;
import cu.uci.abcd.statistic.jisis.domain.TabularStatistic;

public interface JISISStatisticsProvider {
	
	public FieldSelectionTable getFieldSelectionTable(StatisticParam param) throws Exception;

	public TabularStatistic getValuesFromLucene(StatisticParam statisticParam, TabularStatistic tab) throws Exception;

	public TabularStatistic getValuesFromMfn(StatisticParam statisticParam, TabularStatistic tab) throws Exception;

    public void checkIndexTags(StatisticParam statisticParam) throws Exception;

}
