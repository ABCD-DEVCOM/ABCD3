package cu.uci.abcd.statistic.jisis.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cu.uci.abcd.statistic.jisis.domain.LuceneOption;
import cu.uci.abcd.statistic.jisis.domain.Option;
import cu.uci.abcd.statistic.jisis.domain.OptionAND;
import cu.uci.abcd.statistic.jisis.domain.OptionNOT;
import cu.uci.abcd.statistic.jisis.domain.OptionOR;

public class OptionUtils {
   public static Option Option(String field, String term, boolean begin, boolean end){
	   LuceneOption option = new LuceneOption(field, term, begin);
	   option.setEnd(end);
	   return option;
   }
   
   public static Option Option(String field, String term){
	   return new LuceneOption(field, term);
	  
   }
   
   public static Option NOT(Option option){
	   return new OptionNOT(option);
   }
   
   public static Option AND(Option left, Option rigth){
	   return new OptionAND(left,rigth);
   }
   
   public static Option AND(Option left, Option rigth, boolean group){
	   return  new OptionAND(left,rigth,group);
   }
   
   public static Option OR(Option left, Option rigth){
	   return new OptionOR(left,rigth);
   }
   
   public static Option OR(Option left, Option rigth, boolean group){
	   return  new OptionOR(left,rigth,group);
   }
   
   public static List<Option> buildOptions(String options) {
		List<Option> optionsCriterias = new ArrayList<>(2);
		String[] tokends = options.split("\\ ");
		AtomicInteger i = new AtomicInteger(0);

		for (; i.get() < tokends.length;) {
			optionsCriterias.add(nextOption(i, tokends,optionsCriterias));
		}
		return optionsCriterias;
	}

	private static Option nextOption(AtomicInteger i, String[] tokends,List<Option> criterias) {
		Option op = null;
		if (i.get()<tokends.length && tokends[i.get()].equalsIgnoreCase("AND")) {
			i.getAndIncrement();
			if (tokends[i.get()].equalsIgnoreCase("NOT")) {
				i.getAndIncrement();
				op = createNot(i, tokends, criterias);
			} else {
				op = createAND(i, tokends,criterias);
			}

		} else {
			if (i.get()< tokends.length && tokends[i.get()].equalsIgnoreCase("OR")) {
				i.getAndIncrement();
				if (tokends[i.get()].equalsIgnoreCase("NOT")) {
					i.getAndIncrement();
					op = createNot(i, tokends,criterias);
				} else {
					op = createOR(i, tokends,criterias);
				}
			} else {
				if (i.get()<tokends.length && tokends[i.get()].equalsIgnoreCase("NOT")) {
					op = createNot(i, tokends,criterias);
				} else {
					if (i.get()<tokends.length) {
						op = createOption(i, tokends);
					}
					
				}
			}
		}

		return op;
	}

	private static Option createOR(AtomicInteger i, String[] tokends,List<Option> criterias) {
		Option letftOption =criterias.get(criterias.size()-1);
		criterias.remove(criterias.size()-1);
		return OptionUtils.OR(letftOption, nextOption(i, tokends,criterias));
	
	}

	private static Option createOption(AtomicInteger i, String[] tokends) {
		boolean begin = false;
		boolean end =false;
		if (i.get()<(tokends.length) && tokends[i.get()].equals("(")) {
			begin = true;
			i.getAndIncrement();
		}
	
		String field =tokends[i.get()];
		String term = tokends[i.get() + 1];
			i.getAndIncrement();
			i.getAndIncrement();
		
		
		if (i.get() < tokends.length && tokends[i.get()].equals(")")) {
			end=true;
			i.getAndIncrement();
		}
		return OptionUtils.Option(field, term, begin, end);
	}

	private static Option createNot(AtomicInteger i, String[] tokends,List<Option> criterias) {
		return OptionUtils.NOT((nextOption(i, tokends, criterias)));
	}

	private static Option createAND(AtomicInteger i, String[] tokends,List<Option> criterias) {
		Option letftOption =criterias.get(criterias.size()-1);
		criterias.remove(criterias.size()-1);
		return OptionUtils.AND(letftOption, nextOption(i, tokends,criterias));
		
	}
   
}
