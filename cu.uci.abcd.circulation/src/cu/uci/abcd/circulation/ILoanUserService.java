package cu.uci.abcd.circulation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.circulation.LoanUser;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.common.User;
import cu.uci.abcd.domain.management.library.Library;
import cu.uci.abcd.domain.management.library.Room;

public interface ILoanUserService {

	/**
	 * RF_CI1_Loan user register
	 * RF_CI1 Registrar Usuario de Préstamo
	 * RF_CI1.1 Registrar Usuario de Préstamo asociado a un LDAP
	 * RF_CI1.2 Registrar Usuario de Préstamo no asociado a un LDAP
	 * RF_CI2 Editar Usuario de Préstamo
	 * RF_CI2.1 Activar Usuario de Préstamo
	 * RF_CI2.2 Desactivar Usuario de Préstamo
	 * @param loanUser
	 * @return
	 */

	public LoanUser addLoanUser(LoanUser loanUser);

	/**
	 * RF_CI4_Loan user delete
	 * RF_CI4 Eliminar Usuario de Préstamo
	 * 
	 * @param idLoanUser
	 */

	public void deleteLoanUser(Long idLoanUser);

	/**
	 * Loan user one
	 * RF_CI3 Visualizar Usuario de Préstamo
	 * RF_CI5 Visualizar estado actual de un Usuario de Préstamo
	 * @return
	 */
	
	public LoanUser findOneLoanUser(Long idLoanUser);

	/**
	 * Loan user type list
	 * 
	 * @return
	 */

	public List<Nomenclator> findByNomenclator(Long idLibrary, Long code);

	/**
	 * Find nomenclators by IDs
	 * @param idLibrary
	 * @param code1
	 * @param code2
	 * @return
	 */
	public List<Nomenclator> findByNomenclators(Long idLibrary, Long code1, Long code2);
	/**
	 * Find nomenclator by ID
	 * 
	 * @param nomenclatorID
	 * @return
	 */
	public Nomenclator findByID(Long nomenclatorID);

	/**
	 * Find User by Person ID
	 * 
	 * @param personID
	 * @return
	 */
	public User findUserByPersonID(Long personID);

	/**
	 * Find LoanUser by Person ID and LoanUser state
	 * @param personID
	 * @param nomenclatorID
	 * @return
	 */
	public LoanUser findLoanUserByPersonIDAndState(Long personID, Long nomenclatorID);

	/**
	 * Find room by Library ID
	 * @param libraryID
	 * @return
	 */
	public List<Room> findRoomByLibrary(Long libraryID);

	/**
	 * Find all LoanUser
	 * RF_CI3 Visualizar Usuario de Préstamo
	 * RF_CI5 Visualizar estado actual de un Usuario de Préstamo
	 * RF_CI7 Listar todos los Usuarios de Préstamo
	 * RF_CI7.2 Mostrar cantidad de todos los Usuarios de Préstamo
	 * @return
	 */
	public List<LoanUser> findAllLoanUserList();

	/**
	 * Find LoanUser by LoanUser code
	 * RF_CI3 Visualizar Usuario de Préstamo
	 * @param code
	 * @return
	 */
	public LoanUser findByLoanUserCode(String code);
	
	/**
	 * RF_CI3 Visualizar Usuario de Préstamo
	 * RF_CI7.3 Filtrar listado de Usuarios de Préstamo
	 * RF_CI7.4 Exportar listado de Usuarios de Préstamo a Hoja de Cálculo
	 * RF_CI7.5 Exportar listado de Usuarios de Préstamo a PDF
	 * RF_CI14.1 Buscar Usuarios de Préstamo por criterios
	 * RF_CI14.2.2 Mostrar cantidad de Usuarios de Préstamo coincidentes
	 * Consult LoanUser by search criteria
	 * @param loanUserCode
	 * @param roomUser
	 * @param loanUserType
	 * @param loanUserState
	 * @param firstName
	 * @param secondName
	 * @param firstLastName
	 * @param secondLastName
	 * @param fromDate
	 * @param toDate
	 * @param DNI
	 * @param page
	 * @param size
	 * @param direction
	 * @param order
	 * @return
	 */
	public Page<LoanUser> findLoanUserConsult(String loanUserCode,
			Room roomUser, Nomenclator loanUserType, Nomenclator loanUserState,
			Nomenclator faculty, Nomenclator career,
			String firstName, String secondName, String firstLastName,
			String secondLastName, Date fromDate, Date toDate, String DNI,Library library,
			int page, int size, int direction, String order);

	/**
	 * RF_CI3 Visualizar Usuario de Préstamo
	 * Find LoanUser by advanced search criteria 
	 * @param firstName
	 * @param DNI
	 * @param loanUserCode
	 * @param page
	 * @param size
	 * @param direction
	 * @param order
	 * @return
	 */
	public Page<LoanUser> findLoanUserFragment(String params,Library library, int page,
			int size, int direction, String order);

	public Page<LoanUser> findLoanUserFragmentInterLibrarian(String params,Library library,
			int page, int size, int direction, String order);

	/**
	 * RF_CI7.3 Filtrar listado de Usuarios de Préstamo
	 * @param params
	 * @param library
	 * @param page
	 * @param size
	 * @param direction
	 * @param order
	 * @return
	 */
	public Page<LoanUser> findLoanUserFragmentOtherType(String params,Library library,
			int page, int size, int direction, String order);

	/**
	 * Scheduled task to update state LoanUser 
	 */
	public void updateStateLoanUser();
}
