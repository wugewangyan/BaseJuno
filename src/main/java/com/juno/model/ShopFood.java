package com.juno.model;

import java.math.BigDecimal;


public class ShopFood {
	
	private String foodId;
    private String foodName;
    private BigDecimal foodPrice;
    private String foodSesc;
    private String foodPic;
    private String shopId;
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public BigDecimal getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(BigDecimal foodPrice) {
		this.foodPrice = foodPrice;
	}
	public String getFoodSesc() {
		return foodSesc;
	}
	public void setFoodSesc(String foodSesc) {
		this.foodSesc = foodSesc;
	}
	public String getFoodPic() {
		return foodPic;
	}
	public void setFoodPic(String foodPic) {
		this.foodPic = foodPic;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
    
    
}
