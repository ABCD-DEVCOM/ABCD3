package cu.uci.abcd.statistic.jisis.domain;

public class LuceneOption implements Option {
	protected String field;
	protected String term;
	protected boolean begin = false;
	protected boolean end = false;

	public LuceneOption(String field, String term) {
		super();
		this.field = field;
		this.term = term;
		
	}

	public LuceneOption(String field, String term, boolean begin) {
		super();
		this.field = field;
		this.term = term;
		this.begin = begin;
	}


	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTerm() {
		return term;
	}

	public void setBegin(boolean begin) {
		this.begin = begin;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	@Override
	public String getQuery(){
			return "_" + field +  ":\"" + term.toUpperCase()+"\"";
	}

	@Override
	public boolean isBeginGroup() {
		return begin;
	}

	@Override
	public boolean isEndGroup() {
		return end;
	}
	


}
