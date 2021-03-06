package cu.uci.abcd.opac;


import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.acquisition.Suggestion;
 
/**
 * RF_SW1 Registrar Sugerencia,RF_SW3 Editar Sugerencia, RF_SW4 Eliminar Sugerencia,
 * RF_SW5 Listar mis Sugerencias Pendientes,RF_SW5.2 Mostrar la cantidad de mis Sugerencias listadas,
 * 
 * @author dailien
 *
 */
public interface IOpacSuggestionService {

	public Suggestion addSuggestion(Suggestion suggestion);
    
	public Suggestion updateSuggestion(Suggestion suggestion);	
	
	public void deleteSuggestion(Long idSuggestion);
	     
	public List<Suggestion> findAllSuggestions();	
	
	public List<Suggestion> findAllSuggestionsPending();
	
	public boolean findAllSuggestionByName(String titleSuggestion,Long userId);	
	
	public boolean findAllSuggestionByName(Long suggestionId, String titleSuggestion,Long userId);	

	public Suggestion findSuggestion(Long idSuggestion);	

	public int countSuggestionByUserAndLibrary(Long userId, Long libraryId);
	
	public int countSuggestionByUserAndLibraryAndState(Long userId, Long libraryId);	
	
	public Page<Suggestion> findAllSuggestionByUserAndLibrary(Long userId, Long libraryId, int page, int size, int direction, String orderByString);
	
	public Page<Suggestion> findAllSuggestionPendingByUserAndLibrary(Long userId, Long libraryId, int page, int size, int direction, String orderByString);

}
                