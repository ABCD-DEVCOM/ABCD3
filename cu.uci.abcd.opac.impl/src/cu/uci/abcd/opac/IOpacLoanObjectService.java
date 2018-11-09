package cu.uci.abcd.opac;

import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.common.LoanObject;

/**
 * RF_SW6 Listar las adquisiciones recientes, RF_SW6.2 Mostrar la cantidad de Registros de Adquisición recientes

 * @author dailien
 *
 */
public interface IOpacLoanObjectService {
	     
	public List<LoanObject> findRecentLoanObject(Long libraryId);

	public List<LoanObject> findAllAvailableLoanObjectByControlNumberAndLibrary(String controlNumber, String databaseName, Long libraryId);	
	
	public List<LoanObject> findAllLoanObjectByDate();   
	
	public List<LoanObject> findLoanObjectsByControlNumberAndLibrary(String controlNumber, Long idLibrary);
	
	public int findLoanObjectByControlNumberAndLibrary(String controlNumber, Long libraryId);  
	   
	public Page<LoanObject> findAllCoppysByLoanObjectAndLibrary(String controlNumber, Long idLibrary, int page, int size, int direction, String orderByString);
	
}
    