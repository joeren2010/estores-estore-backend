package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Products;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class ProductsDao implements dao<Products> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	public List<Products> getAll() {
		List<Products> productsList = new ArrayList<Products>();
		try {
			String sql = "SELECT * FROM products";
			ResultSet prd = db.executeQuery(sql);
			while (prd.next()) {
				// instantiate products object "product"
				Products product = new Products();
				// set and map result-set to object
				product.setProductId(prd.getInt("productId"));
				product.setProductTitle(prd.getString("productTitle"));
				product.setProductDescription(prd.getString("productDescription"));
				product.setProductCode(prd.getString("productCode"));
				product.setCategoryId(prd.getInt("categoryId"));
				product.setImages(prd.getString("images"));
				product.setThumbnailImage(prd.getInt("thumbnailImage"));
				product.setPrice(prd.getInt("price"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(prd.getString("addedOn"));
				product.setAddedOn(addedOn);
				product.setRating(prd.getInt("rating"));
				// add product into productsList
				productsList.add(product);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return productsList;
	}

	public Products getOne(long id) {
		// instantiate products object "product"
		Products product = new Products();
		try {
			String sql = "SELECT * FROM products WHERE productId = " + id;
			ResultSet prd = db.executeQuery(sql);
			if (prd.next()) {
				// set and map result-set to object
				product.setProductId(prd.getInt("productId"));
				product.setProductTitle(prd.getString("productTitle"));
				product.setProductDescription(prd.getString("productDescription"));
				product.setProductCode(prd.getString("productCode"));
				product.setCategoryId(prd.getInt("categoryId"));
				product.setImages(prd.getString("images"));
				product.setThumbnailImage(prd.getInt("thumbnailImage"));
				product.setPrice(prd.getInt("price"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(prd.getString("addedOn"));
				product.setAddedOn(addedOn);
				product.setRating(prd.getInt("rating"));
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return product;
	}

	public void save(Products obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			// sql query to add data into the table
			String sql = String.format(
					"INSERT INTO products VALUES(null, '%s', '%s', '%s', %d, '%s', %d, %d, '%s', %d)",
					obj.getProductTitle(), obj.getProductDescription(), obj.getProductCode(), obj.getCategoryId(),
					obj.getImages(), obj.getThumbnailImage(), obj.getPrice(), addedOn, obj.getRating());
			String message = (db.executeUpdate(sql) > 0) ? "Products data saved successfully"
					: "Unable to save Products data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(Products obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			String sql = "UPDATE products SET productTitle = '" + obj.getProductTitle() + "', productDescription = '"
					+ obj.getProductDescription() + "', productCode = '" + obj.getProductCode() + "', categoryId = "
					+ obj.getCategoryId() + ", images = '" + obj.getImages() + "', thumbnailImage = '"
					+ obj.getThumbnailImage() + "', price = " + obj.getPrice() + ", addedOn = '" + addedOn
					+ "', rating = " + obj.getRating() + " WHERE productId = " + obj.getProductId();
			String message = (db.executeUpdate(sql) > 0) ? "Products Updated successfully"
					: "Unable to update products";
			System.out.println(message);
		} catch (Exception e) {
			throw new RuntimeException("Exception is: " + e);
		}

	}

	public void delete(long id) {
		try {
			String sql = "DELETE FROM products WHERE productId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "Products record deleted successfully"
					: "Unable to delete Products data";
			System.out.println(message);
		} catch (Exception e) {
			throw new RuntimeException("oops! something went wrong:: " + e.getMessage());
		}
	}
}
