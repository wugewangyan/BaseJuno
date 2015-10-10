package com.juno.model;


public class Shop extends BaseModel{
	
	private String shopId;
    private String shopName;
    private String shopAddress;
    private String shopTelphone;
    private String shopDesc;
    
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopTelphone() {
		return shopTelphone;
	}
	public void setShopTelphone(String shopTelphone) {
		this.shopTelphone = shopTelphone;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
}
