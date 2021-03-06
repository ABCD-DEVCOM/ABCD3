package cu.uci.abcd.circulation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.circulation.LoanUser;
import cu.uci.abcd.domain.circulation.Transaction;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.management.library.Calendar;
import cu.uci.abcd.domain.management.library.CirculationRule;
import cu.uci.abcd.domain.management.library.Library;
import cu.uci.abcd.domain.management.library.Room;
import cu.uci.abcd.domain.management.library.Schedule;

public interface ITransactionService

{
	/**
	 * RF_CI8 Registrar Préstamo de Objeto
	 * RF_CI8.1 Registrar Préstamo de Objeto a partir de Reservación
	 * RF_CI8.2 Registrar Préstamo de Objeto sin Reservación
	 * RF_CI9 Registrar Devolución de Objeto
	 * RF_CI10 Registrar No Entrega de Objeto
	 * RF_CI11 Registrar Renovación de Préstamo
	 * 
	 * @param transaction
	 * @return
	 */
	
	public Transaction addTransaction(Transaction transaction);

	/**
	 * RF_CI13 Eliminar Transacciones

	 * 
	 * @param idTransaction
	 */
	public void deleteTransaction(Long idTransaction);

	/**
	 * find One Transaction
	 * 
	 * @param idTransaction
	 * @return
	 */
	public Transaction findOneTransaction(Long idTransaction);

	/**
	 * find Circulation Rule
	 * 
	 * @param loanUserType
	 * @param recordType
	 * @param actorID
	 * @return
	 */
	public CirculationRule findCirculationRule(Nomenclator circulationRuleState,Nomenclator loanUserType, Nomenclator recordType, Long actorID);

	/**
	 * RF_CI16_find Calendar
	 * 
	 * @param libraryID
	 * @return
	 */
	public List<Calendar> findCalendar(Long libraryID);

	/**
	 * find Horary by Library
	 * 
	 * @param libraryID
	 * @return
	 */
	public List<Schedule> findHorarybyLibrary(Long libraryID);

	/**
	 * find All transaction
	 * RF_CI16.2 Mostrar cantidad de todas las Transacciones
	 * @return
	 */
	public List<Transaction> findAll();

	/**
	 * RF_CI5.2 Listar Transacciones actuales del Usuario de Préstamo
	 * RF_CI5.2.1 Ordenar listado de Transacciones actuales del Usuario de Préstamo
	 * RF_CI5.2.2 Mostrar cantidad de Transacciones actuales del Usuario de Préstamo
	 * 
	 * search Transactions By LoanUser
	 * @param loanUser
	 * @param stateReturn
	 * @param stateNoDelivered
	 * @return
	 */
	public List<Transaction> searchTransactionsByLoanUser(LoanUser loanUser, Nomenclator stateLoan,Nomenclator stateRenew, Nomenclator stateLate);

	/**
	 * RF_CI5.2 Listar Transacciones actuales del Usuario de Préstamo
	 * RF_CI5.2.1 Ordenar listado de Transacciones actuales del Usuario de Préstamo
	 * RF_CI5.2.2 Mostrar cantidad de Transacciones actuales del Usuario de Préstamo
	 * 
	 * search Transactions Loan By LoanUser
	 * @param loanUser
	 * @param stateLoan
	 * @return
	 */
	public List<Transaction> searchTransactionsLoanByLoanUser(LoanUser loanUser, Nomenclator stateLoan);

	/**
	 * 
	 * @param loanUser
	 * @param stateLoan
	 * @return
	 */
	public List<Transaction> searchTransactionsNotReturnLoanByLoanUser(LoanUser loanUser, Nomenclator stateLoan);

	/**
	 * Find Transaction By State, LoanUser and RecordType
	 * @param loanState
	 * @param loanUser
	 * @param recordType
	 * @return
	 */
	public List<Transaction> findTransactionByStateAndLoanUserAndLoanObject_RecordType(Nomenclator loanState, LoanUser loanUser, Nomenclator recordType);

	/**
	 * find All Transaction by search criteria
	 * RF_CI12 Visualizar datos de la Transacción
	 * RF_CI16.3 Filtrar listado de Transacciones (Préstamo)
	 * RF_CI16.4 Exportar listado de Transacciones (Préstamo) a Hoja de Cálculo
	 * RF_CI16.5 Exportar listado de Transacciones (Préstamo) a PDF
	 * @param control_number
	 * @param title
	 * @param record_type_id
	 * @param loan_user_type_id
	 * @param loan_user_code
	 * @param first_Name
	 * @param second_Name
	 * @param first_Surname
	 * @param second_Surname
	 * @param loan_type
	 * @param transaction_state
	 * @param dateRegister
	 * @param endDateRegister
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	//FIXME EXCESO DE PARAMETROS
	public Page<Transaction> findAllTransactionConsult(String inventory_number,
			String title, Nomenclator record_type_id,
			Nomenclator loan_user_type_id, String loan_user_code,
			String first_Name, String second_Name, String first_Surname,
			String second_Surname, Nomenclator loan_type,
			Nomenclator transaction_state, Date dateRegister,
			Date endDateRegister, Room loan_object_rooms,Library library,
			int page, int size, int direction,
			String orderByString);

	/**
	 * find All Transaction By LoanUser Return
	 * @param loanUserID
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Transaction> findAllTransactionByLoanUserReturn(Long loanUserID, int page, int size, int direction, String orderByString);

	/**
	 * find All Transaction By LoanUser Current
	 * @param loanUserID
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Transaction> findAllTransactionByLoanUserCurrent(Long loanUserID, int page, int size, int direction, String orderByString);

	/**
	 * RF_CI19.2 Listar Transacciones históricas del Objeto de Préstamo
	 * RF_CI19.2.2 Mostrar cantidad de Transacciones históricas del Objeto de Préstamo
	 * find All Transaction By LoanUser History
	 * @param loanUserID
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Transaction> findAllTransactionByLoanUserHistory(Long loanUserID, int page, int size, int direction, String orderByString);

	/**
	 * find All Transaction By LoanObject
	 * @param loanObjectID
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Transaction> findAllTransactionByLoanObject(Long loanObjectID, int page, int size, int direction, String orderByString);
	
	/**
	 * 
	 * @param inventoryNumber
	 * @param loanUserCode
	 * @param identificationPerson
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Transaction> findAllTransactionByRegisterReturn(String params,Library library,List<Room> listRoomWorker,
			int page, int size, int direction, String orderByString);

	/**
	 * Scheduled task to register Penalty by delay
	 */
	public void registerPenaltyAutomatic();
}
