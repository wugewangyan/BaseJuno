package com.juno.model;

import java.math.BigDecimal;
import java.util.Date;

public class WugeCustomInfo {
	private String customId;
	private String customType;
	private BigDecimal customAmount;
	private Date customTime;
	private String customDesc;
	private String customCust01;
	private String customCust02;
	private String customCust03;
	private Date startDate;
	private Date endDate;
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public BigDecimal getCustomAmount() {
		return customAmount;
	}
	public void setCustomAmount(BigDecimal customAmount) {
		this.customAmount = customAmount;
	}
	public Date getCustomTime() {
		return customTime;
	}
	public void setCustomTime(Date customTime) {
		this.customTime = customTime;
	}
	public String getCustomDesc() {
		return customDesc;
	}
	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}
	public String getCustomCust01() {
		return customCust01;
	}
	public void setCustomCust01(String customCust01) {
		this.customCust01 = customCust01;
	}
	public String getCustomCust02() {
		return customCust02;
	}
	public void setCustomCust02(String customCust02) {
		this.customCust02 = customCust02;
	}
	public String getCustomCust03() {
		return customCust03;
	}
	public void setCustomCust03(String customCust03) {
		this.customCust03 = customCust03;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
