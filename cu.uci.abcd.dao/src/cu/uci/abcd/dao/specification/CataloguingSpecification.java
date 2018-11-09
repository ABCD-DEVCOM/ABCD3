package cu.uci.abcd.dao.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cu.uci.abcd.domain.cataloguing.CataloguingNomenclator;
import cu.uci.abcd.domain.common.LoanObject;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.management.library.Library;
import cu.uci.abcd.domain.management.library.Provider;
import cu.uci.abcd.domain.management.library.Room;

public class CataloguingSpecification {

	/**
	 * Created by Basilio Puentes Rodríguez
	 */

	public static Specification<LoanObject> searchLoanObjectByControlNumber(final String controlNumber){

		return new Specification<LoanObject>() {

			@Override
			public Predicate toPredicate(Root<LoanObject> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<String> ctrNumber = root.get("controlNumber");

				if (controlNumber != null) {
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(ctrNumber, controlNumber)));
				}

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
	
	public static Specification<LoanObject> searchLoanObjectByControlNumber_Library(final String controlNumber,
			final Library library){
		
		return new Specification<LoanObject>() {

			@Override
			public Predicate toPredicate(Root<LoanObject> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<String> ctrNumber = root.get("controlNumber");
				Path<String> libraryRoot = root.get("libraryOwner");

				if (controlNumber != null) {
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(ctrNumber, controlNumber)));
				}
				
				if(library != null){
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(libraryRoot, library)));
				}

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public static Specification<LoanObject> searchLoanObjectByPrecataloguedSituation(){

		return new Specification<LoanObject>() {

			@Override
			public Predicate toPredicate(Root<LoanObject> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<Nomenclator> situation = root.get("situation");
				
				long precataloguingSituation = CataloguingNomenclator.SITUATION_PRECATALOGUING;

				predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(situation.<Long> get("nomenclatorID"), precataloguingSituation)));

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
	
	public static Specification<LoanObject> searchLoanObjectByPrecataloguedSituation_Library(final Library library){

		return new Specification<LoanObject>() {

			@Override
			public Predicate toPredicate(Root<LoanObject> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<Nomenclator> situation = root.get("situation");
				Path<String> libraryRoot = root.get("libraryOwner");
				
				long precataloguingSituation = CataloguingNomenclator.SITUATION_PRECATALOGUING;

				predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(situation.<Long> get("nomenclatorID"), precataloguingSituation)));
				
				if(library != null){
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(libraryRoot, library)));
				}

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
	
	public static Specification<Room> findAllRoom_Library(final Library library){

		return new Specification<Room>() {

			@Override
			public Predicate toPredicate(Root<Room> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<String> libraryRoot = root.get("library");
				
				if(library != null){
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(libraryRoot, library)));
				}

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
	
	public static Specification<Provider> findAllProvider_Library(final Library library){

		return new Specification<Provider>() {

			@Override
			public Predicate toPredicate(Root<Provider> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				Path<String> libraryRoot = root.get("library");
				
				if(library != null){
					predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(libraryRoot, library)));
				}

				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
