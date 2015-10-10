package com.juno.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.interceptor.PageInterceptor;
import com.juno.interceptor.PageInterceptor.Page;
import com.juno.mapper.ShopMapper;
import com.juno.model.Shop;
import com.juno.service.IShopService;

@Service
@Transactional
public class ShopServiceImpl implements IShopService {
	
	@Resource(name = "shopMapper")
	private ShopMapper shopMapper;
	
	public void insert(Shop shop) {
		this.shopMapper.insertShop(shop);
	}
	
	public void update(Shop shop) {
		Shop s = this.shopMapper.selectShopById(shop.getShopId());
		if(s != null){
			this.shopMapper.updateShop(shop);
		}else{
			throw new RuntimeException("要更新的数据不存在");
		}
	}
	
	public void delete(String shopId) {
		Shop s = this.shopMapper.selectShopById(shopId);
		if(s != null){
			this.shopMapper.deleteShop(shopId);
		}else{
			throw new RuntimeException("要删除的数据不存在");
		}
	}
	
	
	public Shop get(String shopId) {
		return this.shopMapper.selectShopById(shopId);
	}
	
	public Page<Shop> listAll(Shop shop) {
		PageInterceptor.startPage(shop.getPageNum(), shop.getPageSize());
		this.shopMapper.selectAllShop();
		return PageInterceptor.endPage();
	}
}
