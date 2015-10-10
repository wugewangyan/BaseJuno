package com.juno.service;

public interface IBookService {

	public void purchase(String isbn, String account);
	
	public void purchaseTxAPI(String isbn, String account);
	
	public void purchaseTxTemplate(final String isbn, final String account);
}
