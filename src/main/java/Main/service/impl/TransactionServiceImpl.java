package Main.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.TransactionDAO;
import Main.entity.Translation;
import Main.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	TransactionDAO transactionDAO;
	
	@Override
	public Translation create(Translation translation) {
		return transactionDAO.save(translation);
	}

}
