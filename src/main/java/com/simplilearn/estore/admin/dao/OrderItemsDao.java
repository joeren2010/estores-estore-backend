package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.estore.admin.model.OrderItems;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class OrderItemsDao implements dao<OrderItems> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	public List<OrderItems> getAll() {
		List<OrderItems> ordersList = new ArrayList<OrderItems>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM orderItems";
			ResultSet itm = db.executeQuery(sql);
			// object "pojo" mapping
			while (itm.next()) {
				//instantiate orderitems object called "orderitem"
				OrderItems orderItem = new OrderItems();
				orderItem.setOrderItemId(itm.getInt("orderItemId"));
				orderItem.setOrderId(itm.getInt("orderId"));
				orderItem.setProductId(itm.getInt("productId"));
				orderItem.setProductCode(itm.getString("productCode"));
				orderItem.setProductImg(itm.getString("productImg"));
				orderItem.setProductTitle(itm.getString("productTitle"));
				orderItem.setProductDescription(itm.getString("productDescription"));
				orderItem.setProductCategory(itm.getString("productCategory"));
				orderItem.setPrice(itm.getInt("price"));
				orderItem.setQuantity(itm.getInt("quantity"));
				orderItem.setTotalPrice(itm.getInt("totalPrice"));
				//add orders into ordersList
				ordersList.add(orderItem);
			}
		} 
		catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return ordersList;
	}

	public OrderItems getOne(long id) {
		//instantiate orderitems object called "orderitem"
		OrderItems orderItem = new OrderItems();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM orderItems WHERE orderItemId = " + id;
			ResultSet itm = db.executeQuery(sql);
			// object "pojo" mapping
			if (itm.next()) {
				orderItem.setOrderItemId(itm.getInt("orderItemId"));
				orderItem.setOrderId(itm.getInt("orderId"));
				orderItem.setProductId(itm.getInt("productId"));
				orderItem.setProductCode(itm.getString("productCode"));
				orderItem.setProductImg(itm.getString("productImg"));
				orderItem.setProductTitle(itm.getString("productTitle"));
				orderItem.setProductDescription(itm.getString("productDescription"));
				orderItem.setProductCategory(itm.getString("productCategory"));
				orderItem.setPrice(itm.getInt("price"));
				orderItem.setQuantity(itm.getInt("quantity"));
				orderItem.setTotalPrice(itm.getInt("totalPrice"));
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return orderItem;
	}

	public void save(OrderItems obj) {
		try {
			// sql query to add data into the table
			String sql = String.format("INSERT INTO orderItems VALUES (null, %d, %d, '%s', '%s', '%s', '%s', '%s', %d, %d, %d)",
			obj.getOrderId(), 
			obj.getProductId(), 
			obj.getProductCode(), 
			obj.getProductImg(),
			obj.getProductTitle(), 
			obj.getProductDescription(), 
			obj.getProductCategory(), 
			obj.getPrice(),
			obj.getQuantity(), 
			obj.getTotalPrice());
			String message = (db.executeUpdate(sql) > 0) ? "OrderItems data saved successfully" : "Unable to save OrderItems data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(OrderItems obj) {
		try {
			// sql query to update data in the table
			String sql = String.format("UPDATE orderItems SET orderId=%d, productId=%d, productCode='%s', productImg='%s', productTitle='%s', productDescription='%s', productCategory='%s', price=%d, quantity=%d, totalPrice=%d WHERE orderItemId=%d",
			obj.getOrderId(), 
			obj.getProductId(), 
			obj.getProductCode(), 
			obj.getProductImg(),
			obj.getProductTitle(), 
			obj.getProductDescription(), 
			obj.getProductCategory(), 
			obj.getPrice(),
			obj.getQuantity(), 
			obj.getTotalPrice());
			String message = (db.executeUpdate(sql) > 0) ? "OrderItems data updated successfully" : "Unable to update OrderItems data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}

	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM orderItems WHERE orderItemId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "OrderItems data deleted successfully" : "Unable to delete OrderItems data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}

	}
}
