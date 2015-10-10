package com.juno.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.juno.dao.IBookDAO;
import com.juno.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDAO ibookDAO;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	/**
	 * 添加注解@Transactional来管理事务的方法，注意需要配置<tx:annotation-driven transaction-manager="transactionManager"/>
	 * 此注解设置事务的传播属性为Propagation.REQUIRED
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void purchase(String isbn, String account) {
		BigDecimal price = ibookDAO.getPriceByISBN(isbn);
		ibookDAO.updateBookStockByISBN(isbn);
		ibookDAO.updateBalanceByAccount(price, account);
	}
	
	
	/**
	 * 使用Spring 底层API 来管理事务
	 */
	public void purchaseTxAPI(String isbn, String account) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			BigDecimal price = ibookDAO.getPriceByISBN(isbn);
			ibookDAO.updateBookStockByISBN(isbn);
			ibookDAO.updateBalanceByAccount(price, account);
			this.transactionManager.commit(status);
		}catch(DataAccessException e){
			this.transactionManager.rollback(status);
			throw e;
		}
	}
	
	/**
	 * 使用Spring TransactionTemplate 来管理事务
	 * @param isbn
	 * @param account
	 */
	public void purchaseTxTemplate(final String isbn, final String account){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BigDecimal price = ibookDAO.getPriceByISBN(isbn);
				ibookDAO.updateBookStockByISBN(isbn);
				ibookDAO.updateBalanceByAccount(price, account);
			}
		});
	}
}
