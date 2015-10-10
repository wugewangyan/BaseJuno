package com.juno.mapper;

import java.util.List;

import com.juno.model.Shop;

public interface ShopMapper {
	
	public void insertShop(Shop shop);
	
	public void updateShop(Shop shop);
	
	public void deleteShop(String shopId);
	
	public Shop selectShopById(String shopId);
	
	public List<Shop> selectAllShop();
}
