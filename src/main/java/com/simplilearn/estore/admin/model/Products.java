package com.simplilearn.estore.admin.model;

import java.util.Date;

public class Products {

	// create sql table fields
	private int productId;
	private String productTitle;
	private String productDescription;
	private String productCode;
	private int categoryId;
	private String images;
	private int thumbnailImage;
	private int price;
	private Date addedOn;
	private int rating;

	// default constructor
	public Products() {
		super();
	}

	// parameterized constructor
	public Products(int productId, String productTitle, String productDescription, String productCode,
			String images, int thumnailImage, int price, Date addedOn, int rating, int categoryId) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productCode = productCode;
		this.categoryId = categoryId;
		this.images = images;
		this.thumbnailImage = thumnailImage;
		this.price = price;
		this.addedOn = addedOn;
		this.rating = rating;
	}

	// getter & setter methods
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(int thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	// override to-string method
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productTitle=" + productTitle + ", productDescription="
				+ productDescription + ", productCode=" + productCode + ", categoryId=" + categoryId + " images=" + images + ", thumbnailImage="
				+ thumbnailImage + ", price=" + price + ", addedOn=" + addedOn + ", rating=" + rating + "]";
	}
}
