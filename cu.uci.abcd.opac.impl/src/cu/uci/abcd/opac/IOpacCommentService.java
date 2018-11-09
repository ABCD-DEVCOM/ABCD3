package cu.uci.abcd.opac;

import java.util.List;
import cu.uci.abcd.domain.opac.Comment;

/**
 * RF_OP8.1.3 Registrar comentarios a un material, RF_OP8.1.4 Visualizar comentarios de un material, 
 * RF_OP8.1.5 Editar  Comentario, RF_OP8.1.6 Eliminar Comentario
 * @author Alberto Alejandro Arias Benitez
 *
 */
public interface IOpacCommentService {
	/**
	 * 
	 * @param comment
	 * @return
	 */
	public Comment addComment(Comment comment);
    
	/**
	 * 
	 * @param comment
	 * @return
	 */
	public Comment updateComment(Comment comment);	
	
	/**
	 * 
	 * @param idComment
	 */
	public void deleteComment(Long idComment);	
	
	public List<Comment> findAllCommentsPendingByUser();	

	public Comment findComment(Long idComment);
	
	public List<Comment> findAllCommentsByMaterial(String idMaterial, String databaseName, String def_home);      
	
}     
      