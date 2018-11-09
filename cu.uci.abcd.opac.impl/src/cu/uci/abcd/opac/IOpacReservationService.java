package cu.uci.abcd.opac;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.circulation.Reservation;
import cu.uci.abcd.domain.common.Nomenclator;
import cu.uci.abcd.domain.management.library.Calendar;
import cu.uci.abcd.domain.management.library.CirculationRule;
import cu.uci.abcd.domain.management.library.Schedule;
  /**
   * RF_OP8.5, RF_SW7.2 Visualizar el resumen de reservaciones realizado por un usuario,
   * RF_SW11 Editar Reservas de Objetos de Préstamo, RF_SW12 Eliminar Reserva de Objetos de Préstamo,
   * RF_SW13 Cancelar Reserva de Objetos de Préstamo, RF_SW16 Listar Reservas actuales de Objetos de Préstamo dado un usuario,
   * RF_SW16.2 Mostrar cantidad de Reservas actuales de Objetos de Préstamo del usuario,
   * RF_SW16.3 Filtrar listado de Reservas de Objetos de Préstamo de un usuario
   * @author Alberto Alejandro Arias Benitez
   *
   */
public interface IOpacReservationService{
	
	/**
	 * 
	 * @param reservation
	 * @return Reservation
	 */
	public Reservation addReservation(Reservation reservation);
	
	/**
	 * 
	 * @param idReservation
	 * @return
	 */
	public Reservation updateReservation(Long idReservation);
	    
	/**
	 * 
	 * @param idReservation
	 */
	public void deleteReservation(Long idReservation);	
	
	/**
	 * 
	 * @param idReservation
	 * @return
	 */
	public Reservation findResertion(Long idReservation);
	
	/**
	 * 
	 * @param idLoanUser
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<Reservation> findCurrentReservation(Long idLoanUser, int page, int size, int direction, String orderByString);	
	
	public Page<Reservation> findHistoricalReservationByUser(Long idLoanUser, int page, int size, int direction, String orderByString);	

	public Page<Reservation> findHistoricalReservationByParameters(Long idLoanUser, String titleBook, String autorBook, Long state, int page, int size, int direction, String orderByString);
	             
	public int CountReservationPendingByUser(Long idLoanUser);
	
	public CirculationRule findCirculationRule(Nomenclator circulationRuleState, Nomenclator loanUserType, Nomenclator recordType, Long actorID);

	public List<Calendar> findCalendar(Long libraryID);
	
	public List<Schedule> findHorarybyLibrary(Long libraryID);
	
	public boolean isAvailableByDate(Date startDate, Date endDate, Long loanObjectId);	
	
	public boolean isAvailableByDate(Date startDate, Date endDate, Long loanObjectId, Long loanUserId);	
	
	/**
	 * Scheduled task to update state Penalty Type Suspension 
	 */	
	public void updateStateReservationEnd();

	
}