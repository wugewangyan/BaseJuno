package com.juno.service;

import com.juno.interceptor.PageInterceptor.Page;
import com.juno.model.Shop;

public interface IShopService {

	
	public void insert(Shop shop);
	
	public void update(Shop shop);
	
	public void delete(String shopId);
	
	public Shop get(String shopId);
	
	public Page<Shop> listAll(Shop shop);
}
