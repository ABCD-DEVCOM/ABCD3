package cu.uci.abcd.circulation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.common.LoanObject;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.management.library.Library;
import cu.uci.abcd.domain.management.library.Room;

public interface ILoanObjectService {

	/**
	 * Add Loan Object
	 * 
	 * @param loanObject
	 * @return
	 */
	public LoanObject addLoanObject(LoanObject loanObject);

	/**
	 * Find one Loan Object
	 * 
	 * @param idLoanObject
	 * @return
	 */
	public LoanObject findOneLoanObject(Long idLoanObject);

	
	public List<String> findAllControlNumbersFromAvailableLoanObjects(List<LoanObject> listInventoryNumber);	
	
	/**
	 * RF_CI17_Find All Loan Object
	 * RF_CI17.2 Mostrar cantidad de Objetos de Préstamos de un usuario.
	 * RF_CI17.3 Filtrar listado de Objetos de Préstamo
	 * RF_CI17.4 Exportar listado de Objeto de Préstamo a Hoja de Cálculo
	 * RF_CI17.5 Exportar listado de Objeto de Préstamo a PDF
	 * 
	 * @param specification
	 * @param pageable
	 * @return
	 */	
	//FIXME EXCESO DE PARAMETROS
	public Page<LoanObject> findAllLoanObject(String title, String author,
			Nomenclator record_type_id, Nomenclator loan_object_state,
			String inventory_number, String control_number, Date fromDate,
			Date toDate, Room room, Library library,int page, int size, int direction,
			String order);

	/**
	 * find All LoanObject By Inventory Number
	 * RF_CI15.1 Buscar Objetos de Préstamo por criterios
	 * RF_CI19.4 Exportar histórico de Objeto de Préstamo a Hoja de Cálculo
	 * RF_CI19.5 Exportar histórico de Objeto de Préstamo a PDF
	 * @param inventory_number
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<LoanObject> findAllLoanObjectByInventoryNumber(
			String inventory_number,Library library,List<Room> listIDRoomWorker, int page, int size, int direction,
			String orderByString);

	public List<LoanObject> findLoanObjectLost(String controlNumber, Nomenclator stateLoanObject);
	
	public List<LoanObject> findControlNumberLoanObject(String controlNumber);
	
	public List<LoanObject> findAvailableControlNumberLoanObject(String controlNumber);


}
