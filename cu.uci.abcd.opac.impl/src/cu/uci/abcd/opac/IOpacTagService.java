package cu.uci.abcd.opac;

import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.opac.Tag;

/**
 * RF_OP8.1.7 Agregar etiquetas a un registro bibliográfico, RF_OP8.1.8 Visualizar etiquetas, 
 * RF_OP8.1.9 Eliminar etiquetas
 * @author dailien
 *
 */
public interface IOpacTagService {
	
	public Tag addTag(Tag tag);
    
	public Tag updateTag(Tag tag);	
	
	public void deleteTag(Long idTag);
	     
	public List<Tag> findTagsByRegister(String controlNum, String dataBaseName, Long libraryId);	

	public Tag findTag(Long idTag);
	
	public Page<Tag> findAllTagByUserAndLibrary(Long userId, Long libraryId, int page, int size, int direction, String orderByString);
	
	public Page<Tag> searchTagsByLibrary(Long userId, Long libraryId, int page, int size, int direction, String orderByString);

	public boolean checkUniqueTag(Long userID, String tagName, String controlNum, String dataBaseName, Long libraryId);
}
