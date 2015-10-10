package com.juno.dao.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.juno.dao.IBookDAO;

@Repository
public class BookDAOImpl implements IBookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public BigDecimal getPriceByISBN(String isbn) {
		String sql = " select b.price from book b where b.isbn = ? ";
		return this.jdbcTemplate.queryForObject(sql, BigDecimal.class, isbn);
	}

	public void updateBookStockByISBN(String isbn) {
		String sql = " update book_stock set stock = stock - 1 where isbn = ? ";
		this.jdbcTemplate.update(sql, isbn);
	}

	public void updateBalanceByAccount(BigDecimal price, String account) {
		String sql = " update account set balance = balance - ? where account = ? ";
		this.jdbcTemplate.update(sql, price, account);
	}
}
