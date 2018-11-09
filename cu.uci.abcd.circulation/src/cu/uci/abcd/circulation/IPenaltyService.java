package cu.uci.abcd.circulation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.circulation.LoanUser;
import cu.uci.abcd.domain.circulation.Penalty;
import cu.uci.abcd.domain.common.LoanObject;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.management.library.FineEquation;
import cu.uci.abcd.domain.management.library.Library;

public interface IPenaltyService {

	/**
	 * RF_CI20 Registrar Sanciones
	 * RF_CI10 Registrar No Entrega de Objeto
	 * RF_CI22 Editar una Sanción
	 * RF_CI24 Registrar pago de una Sanción de tipo “Multa”
	 * 
	 * @param penalty
	 * @return
	 */
	public Penalty addPenalty(Penalty penalty);

	/**
	 * RF_CI23_delete Penalty
	 * 
	 * @param idPenalty
	 */
	public void deletePenalty(Long idPenalty);

	/**
	 * Search one Penalty
	 * 
	 * @param idPenalty
	 * @return
	 */
	public Penalty findOnePenalty(Long idPenalty);

	/**
	 * RF_CI21 Visualizar Sanciones
	 * RF_CI26_Find All Penalty
	 * RF_CI26.2 Mostrar cantidad de Sanciones
	 * RF_CI26.3 Filtrar listado de Sanciones
	 * RF_CI26.4 Exportar listado de Sanciones a Hoja de Cálculo
	 * RF_CI26.5 Exportar listado de Sanciones a PDF
	 * 
	 * @param specification
	 * @param pageable
	 * @return
	 */
	
	//FIXME EXCESO DE PARAMETROS
	//FIXME SEMANTICA DE METODO
	public Page<Penalty> findAllPenaltyConsult(Nomenclator penalty_type, Nomenclator penalty_state, 
			Nomenclator loan_user_type_id, String loan_user_code,String firstName, String secondName, 
			String firstLast, String secondLast,Date fromDate, Date toDate,String title,
			String author,String control_number,Library library, int page, int size, int direction, String orderByString);

	/**
	 * RF_CI6.3_Find All Penalty by Loan User Current
	 * RF_CI5.3 Listar Sanciones actuales del Usuario de Préstamo
	 * 
	 * @param specification
	 * @param pageable
	 * @return
	 */
	public Page<Penalty> findAllPenaltyByLoanUserCurrent(Long loanUserID, int page, int size, int direction, String orderByString);

	/**
	 * Find All Penalty by Loan User History
	 * 
	 * @param aux
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Penalty> findAllPenaltyByLoanUserHistory(Long loanUserID, int page, int size, int direction, String orderByString);

	/**
	 * RF_CI19.3_Find All Penalty by Loan Object
	 * 
	 * @param specification
	 * @param pageable
	 * @return
	 */
	public Page<Penalty> findAllPenaltyByLoanObject(Long loanObjectID, int page, int size, int direction, String orderByString);

	/**
	 * Find FineEquation by Library
	 * 
	 * @param idLibrary
	 * @return
	 */
	public FineEquation findFineEquationByLibrary(Long idLibrary);
	
	/**
	 * RF_CI6.3 Listar Sanciones históricas del Usuario de Préstamo
	 * RF_CI6.3.2 Mostrar cantidad de Sanciones históricas del Usuario de Préstamo
	 * Find Penalty By LoanUser Id, Penalty Type and Penalty State
	 * @param loanUserID
	 * @param penaltyType
	 * @param penaltyState
	 * @return
	 */
	public List<Penalty> findPenaltyByLoanUserIdAndPenaltyTypeAndPenaltyState(Long loanUserID,Nomenclator penaltyType, Nomenclator penaltyState);

	/**
	 * search Penalty By ID LoanUser
	 * @param idLoanUser
	 * @return
	 */
	public List<Penalty> searchPenaltyByIDLoanUser(Long idLoanUser);

	/**
	 * RF_CI19.3 Listar Sanciones históricas del Objeto de Préstamo
	 * RF_CI19.3.2 Mostrar cantidad de Sanciones históricas del Objeto de Préstamo
	 * @param penaltyState
	 * @param loanUser
	 * @param loanObject
	 * @return
	 */
	public Penalty searchPenaltyByLoanUserAndLoanObject(Nomenclator penaltyState, LoanUser loanUser, LoanObject loanObject);

	/**
	 * RF_CI25 Actualizar el estado de Sanción automáticamente
	 * Scheduled task to update state Penalty Type Suspension 
	 */
	public void updateStatePenaltyTypeSuspension();
}
