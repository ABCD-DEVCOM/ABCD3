package cu.uci.abcd.opac;
//FIXME FALTAN COMENTARIOS DE INTERFACE
import java.util.List;

import cu.uci.abcd.domain.opac.Rating;

/**
 * RF_OP8.7 Ponderar material

 * @author dailien
 *
 */
public interface IOpacWeigUpService {
	
	public Rating addRating(Rating rating);
    
	public Rating updateRating(Rating rating);		
	     
	public List<Rating> findAllRatingByRegister();	
	
	public float ratingByRegister(String controlNum, String dataBaseName, Long libraryId);	
	
	public Rating findRatingByControlNumAndUser(String controlNum, String dataBaseName, Long libraryId, long userID);
	
}
