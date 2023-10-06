package com.javaex.vo;

public class CategoryVo {

	private int cateRowNum;
	private int cateNo;
	private int postCount;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	
	
	public CategoryVo() {
		
	}

	public CategoryVo(int cateRowNum, int cateNo, int postCount, String id, String cateName, String description,
			String regDate) {
		super();
		this.cateRowNum = cateRowNum;
		this.cateNo = cateNo;
		this.postCount = postCount;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	
	public int getCateRowNum() {
		return cateRowNum;
	}

	public void setCateRowNum(int cateRowNum) {
		this.cateRowNum = cateRowNum;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVo [cateRowNum=" + cateRowNum + ", cateNo=" + cateNo + ", postCount=" + postCount + ", id=" + id
				+ ", cateName=" + cateName + ", description=" + description + ", regDate=" + regDate + "]";
	}
	
}


