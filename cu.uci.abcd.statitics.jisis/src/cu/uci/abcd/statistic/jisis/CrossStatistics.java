package cu.uci.abcd.statistic.jisis;

import cu.uci.abcd.statistic.jisis.domain.StatisticParam;
import cu.uci.abcd.statistic.jisis.domain.TabularStatistic;

public interface CrossStatistics {
	
	TabularStatistic buildStatistics(StatisticParam statisticParam);
}
