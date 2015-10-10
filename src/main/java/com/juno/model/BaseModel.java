package com.juno.model;

public class BaseModel {
	private int pageNum = 1;
	private int pageSize = 10;
	private int startRow;  
    private int endRow; 
    
    public BaseModel(){
    	this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;  
        this.endRow = pageNum * pageSize;  
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
}
