package cu.uci.abcd.opac;
//FIXME FALTAN COMENTARIOS DE INTERFACE
import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.opac.SelectionList;

/**
 * RF_OP8.4 Exportar listado de resultados a hoja de cálculo, 
 * RF_OP8.8 Agregar material a Lista de Selección, RF_OP10 Registrar Lista de Selección,
 * RF_OP11 Listar Mis Listas de Selección, RF_OP11.1 Editar Lista de Selección, 
 * RF_OP11.2 Eliminar Lista de Selección, RF_OP11.4 Mostrar la cantidad de Listas de Selección registradas por el usuario,
 * RF_OP11.6.1 Eliminar material de la Lista de Selección, RF_OP12 Listar Listas de Selección públicas,
 * RF_OP12.1 Mostrar la cantidad de Listas de Selección registradas
 * @author dailien
 *
 */
public interface IOpacSelectionListService {

	public SelectionList addSelectionList(SelectionList selectionList);

	public SelectionList updateSelectionList(Long idSelectionList);

	public void deleteSelectionList(Long idSelectionList);

	public int findAllPublicSelectionList();    
	
	public List<SelectionList> findAllSelectionListByUser(Long userId, Long libraryId);
	
	public boolean findAllSelectionListByName(String nameSelectionList, Long userId);
	
	public boolean findAllSelectionListByName(String nameSelectionList, Long userId, Long selectionListId);

	public SelectionList findSelectionList(Long idSelectionList);
	
	public Page<SelectionList> findAllPublicSelectionListPage(int page, int size, int direction, String orderByString);
	
	public Page<SelectionList> findAllSelectionListPageByUser(Integer userId, Long libraryId, int page, int size, int direction, String orderByString);
	
	public List<SelectionList> findAllSelectionList();

}
