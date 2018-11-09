package cu.uci.abcd.statistic.jisis.domain;

public class OptionNOT implements Option {

	private Option option;

	public OptionNOT(Option option) {
		this.option = option;
	}



	@Override
	public String getQuery() {
		if (option.isBeginGroup()&& option.isEndGroup()) {
			return " NOT ( " + option.getQuery()+ " )";	
		}
		if (option.isBeginGroup()&& !option.isEndGroup()) {
			return " NOT ( " + option.getQuery()+ " ";	
		}
		if (!option.isBeginGroup()&& option.isEndGroup()) {
			return " NOT " + option.getQuery()+ " )";	
		}
		
			return " NOT " + option.getQuery()+ " ";
	}
	

	@Override
	public boolean isBeginGroup() {
		return false;
	}

	@Override
	public boolean isEndGroup() {
		return false;
	}
}
