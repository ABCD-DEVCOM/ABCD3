package cu.uci.abcd.opac;

import java.util.List;

import org.springframework.data.domain.Page;

import cu.uci.abcd.domain.circulation.Transaction;

/**
 * RF_SW8.1 Visualizar los préstamos que ha realizado el usuario
 * @author dailien
 *
 */
public interface IOpacTransactionService {			    
	
	public List<Transaction> findAllTransaction();
	
	public Transaction findTransaction(Long idTransaction);
	
	public Page<Transaction> findAll(Long loanUserId, int page, int size, int direction, String orderByString);

}
