package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Categories;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class CategoriesDao implements dao<Categories> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	public List<Categories> getAll() {
		List<Categories> categoriesList = new ArrayList<Categories>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM categories";
			ResultSet cat = db.executeQuery(sql);
			// object "pojo" mapping
			while (cat.next()) {
				// instantiate categories object called "category"
				Categories category = new Categories();
				// set and map result-set to object
				category.setCategoryId(cat.getInt("categoryId"));
				category.setCategoryName(cat.getString("categoryName"));
				category.setCategoryDescription(cat.getString("categoryDescription"));
				category.setCategoryImageUrl(cat.getString("categoryImageUrl"));
				category.setActive(cat.getInt("active"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date addedOn = format.parse(cat.getString("addedOn"));
				category.setAddedOn(addedOn);
				// add category into categoriesList
				categoriesList.add(category);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return categoriesList;
	}

	public Categories getOne(long id) {
		// instantiate a categories object "category"
		Categories category = new Categories();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM categories WHERE categoryId = " + id;
			ResultSet cat = db.executeQuery(sql);
			if (cat.next()) {
				// set and map result-set to object
				category.setCategoryId(cat.getInt("categoryId"));
				category.setCategoryName(cat.getString("categoryName"));
				category.setCategoryDescription(cat.getString("categoryDescription"));
				category.setCategoryImageUrl(cat.getString("categoryImageUrl"));
				category.setActive(cat.getInt("active"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date addedOn = format.parse(cat.getString("addedOn"));
				category.setAddedOn(addedOn);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return category;
	}

	public void save(Categories obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			// sql query to add data into the table
			String sql = "INSERT INTO categories (categoryName,categoryDescription,categoryImageUrl,active,addedOn) VALUES ('"
			+ obj.getCategoryName() + "', '" + obj.getCategoryDescription() + "', '" + obj.getCategoryImageUrl()
			+ "', " + obj.getActive() + " , '" + addedOn + "')";
			String message = (db.executeUpdate(sql) > 0) ? "Category data saved successfully" : "Unable to save Category data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}

	}

	public void update(Categories obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			// sql query to update data in the table
			String sql = "UPDATE categories SET categoryName = '" + obj.getCategoryName() + "', categoryDescription = '"
			+ obj.getCategoryDescription() + "', categoryImageUrl = '" + obj.getCategoryImageUrl()
			+ "', active = " + obj.getActive() + " , addedOn = '" + addedOn + "' WHERE categoryId = "
			+ obj.getCategoryId();
			String message = (db.executeUpdate(sql) > 0) ? "Category data updated successfully" : "Unable to update Category data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM categories WHERE categoryId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "Category data deleted successfully" : "Unable to delete Category data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}
}
