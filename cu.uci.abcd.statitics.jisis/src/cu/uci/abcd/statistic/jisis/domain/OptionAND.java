package cu.uci.abcd.statistic.jisis.domain;

public class OptionAND implements Option {

	private Option leftOption;
	private Option rigthOption;
	private boolean group;

	public OptionAND(Option leftOption, Option rigthOption) {
		this.leftOption = leftOption;
		this.rigthOption = rigthOption;
		group=false;
	}

	public OptionAND(Option leftOption, Option rigthOption, boolean group) {
		this.leftOption = leftOption;
		this.rigthOption = rigthOption;
		this.group = group;
	}

	public String getQueryInternal() {
		// TODOS
		if (leftOption.isBeginGroup() && leftOption.isEndGroup() && rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " ) AND ( " + rigthOption.getQuery() + " ) ";
		}
		// MENOs UTIMO
		if (leftOption.isBeginGroup() && leftOption.isEndGroup() && rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " AND ( " + rigthOption.getQuery() + " ";
		}
		// MENOs PeUTIMO
		if (leftOption.isBeginGroup() && leftOption.isEndGroup() && !rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " ) AND " + rigthOption.getQuery() + " ) ";
		}
		// MENOs AntepUTIMO
		if (leftOption.isBeginGroup() && !leftOption.isEndGroup() && rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " AND ( " + rigthOption.getQuery() + " ) ";
		}
		// MENOs primero
		if (!leftOption.isBeginGroup() && leftOption.isEndGroup() && rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " ) AND ( " + rigthOption.getQuery() + " ) ";
		}

		// los 2 primeros
		if (leftOption.isBeginGroup() && leftOption.isEndGroup() && !rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " ) AND " + rigthOption.getQuery() + " ";
		}
		// los 2 ultimo
		if (!leftOption.isBeginGroup() && !leftOption.isEndGroup() && rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " AND ( " + rigthOption.getQuery() + " ) ";
		}
		// los 1reo y UTIMO
		if (leftOption.isBeginGroup() && !leftOption.isEndGroup() && !rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " AND " + rigthOption.getQuery() + " ) ";
		}
		// los 2 medios
		if (!leftOption.isBeginGroup() && leftOption.isEndGroup() && rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " ) AND ( " + rigthOption.getQuery() + " ";
		}

		// el 1 3
		if (leftOption.isBeginGroup() && !leftOption.isEndGroup() && rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " AND ( " + rigthOption.getQuery() + " ";
		}

		// el 2 4
		if (!leftOption.isBeginGroup() && leftOption.isEndGroup() && !rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " ) AND " + rigthOption.getQuery() + " ) ";
		}

		// El 4
		if (!leftOption.isBeginGroup() && !leftOption.isEndGroup() && !rigthOption.isBeginGroup() && rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " AND " + rigthOption.getQuery() + " ) ";
		}

		// El 3
		if (!leftOption.isBeginGroup() && !leftOption.isEndGroup() && rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " AND ( " + rigthOption.getQuery() + " ";
		}

		// El 2
		if (!leftOption.isBeginGroup() && leftOption.isEndGroup() && !rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return leftOption.getQuery() + " ) AND  " + rigthOption.getQuery() + " ";
		}

		// el 1
		if (leftOption.isBeginGroup() && !leftOption.isEndGroup() && !rigthOption.isBeginGroup() && !rigthOption.isEndGroup()) {
			return " ( " + leftOption.getQuery() + " AND " + rigthOption.getQuery() + " ";
		}
		// Menos Todos
		return leftOption.getQuery() + "  AND  " + rigthOption.getQuery() + " ";
	}

	@Override
	public boolean isBeginGroup() {
		return false;
	}

	@Override
	public boolean isEndGroup() {
		return false;
	}

	@Override
	public String getQuery() {
		if (group) {
			return " ( " + getQueryInternal() + " ) ";
		} else {
			return getQueryInternal();
		}
	}
}
