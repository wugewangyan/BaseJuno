package com.juno.dao;

import java.math.BigDecimal;

public interface IBookDAO {
	
	public BigDecimal getPriceByISBN(String isbn);
	
	public void updateBookStockByISBN(String isbn);
	
	public void updateBalanceByAccount(BigDecimal price, String account);
}
