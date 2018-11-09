package cu.uci.abcd.opac;
import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.common.User;

public interface IOpacUserService {

	/**
	 * RF_SW7 Visualizar resumen personal actual del usuario, RF_SW7.1 Visualizar los datos personales del usuario,
	 * RF_SW8 Visualizar resumen histórico personal del usuario
	 * @author Alberto Alejandro Arias Benitez
	 * @param user
	 * @return
	 */
	public User addUser(User user);
    
	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param User
	 * @return
	 */
	public User updateUser(User User);	
	
	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param idUser
	 */
	public void deleteUser(Long idUser);		

	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param idUser
	 * @return
	 */
	public User findUser(Long idUser);
	
	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param userId
	 * @param libraryId
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<User> findAllUsersByLibrary(Long userId, Long libraryId, int page, int size, int direction, String orderByString);

	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param userId
	 * @param libraryId
	 * @param userName
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderByString
	 * @return
	 */
	public Page<User> findAllUsersByLibraryAndUserName(Long userId, Long libraryId, String userName, int page, int size, int direction, String orderByString);
	
	/**
	 * 
	 * @author Alberto Alejandro Arias Benitez
	 * @param userName
	 * @param libraryId
	 * @return
	 */
	public boolean checkExistingUser(String userName, Long libraryId);
}
