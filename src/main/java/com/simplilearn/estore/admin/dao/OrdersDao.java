package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Orders;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class OrdersDao implements dao<Orders> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	public List<Orders> getAll() {
		List<Orders> ordersList = new ArrayList<Orders>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM orders";
			ResultSet ord = db.executeQuery(sql);
			// object "pojo" mapping
			while (ord.next()) {
				// instantiate orders object called "orders"
				Orders order = new Orders();
				order.setOrderId(ord.getInt("orderId"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date orderDate = format.parse(ord.getString("orderDate"));
				order.setOrderDate(orderDate);
				order.setOrderStatus(ord.getString("orderStatus"));
				order.setTotalItems(ord.getInt("totalItems"));
				order.setItemsSubTotal(ord.getInt("itemsSubTotal"));
				order.setShipmentCharges(ord.getInt("shipmentCharges"));
				order.setTotalAmount(ord.getInt("totalAmount"));
				order.setPaymentStatus(ord.getInt("paymentStatus"));
				order.setPaymentStatusTitle(ord.getString("paymentStatusTitle"));
				order.setPaymentMethod(ord.getInt("paymentMethod"));
				order.setPaymentMethodTitle(ord.getString("paymentMethodTitle"));
				order.setUserId(ord.getInt("userId"));
				ordersList.add(order);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return ordersList;
	}

	public Orders getOne(long id) {
		// instantiate orders object called "orders"
		Orders order = new Orders();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM orders WHERE orderId = " + id;
			ResultSet ord = db.executeQuery(sql);
			// object "pojo" mapping
			if (ord.next()) {
				order.setOrderId(ord.getInt("orderId"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date orderDate = format.parse(ord.getString("orderDate"));
				order.setOrderDate(orderDate);
				order.setOrderStatus(ord.getString("orderStatus"));
				order.setTotalItems(ord.getInt("totalItems"));
				order.setItemsSubTotal(ord.getInt("itemsSubTotal"));
				order.setShipmentCharges(ord.getInt("shipmentCharges"));
				order.setTotalAmount(ord.getInt("totalAmount"));
				order.setPaymentStatus(ord.getInt("paymentStatus"));
				order.setPaymentStatusTitle(ord.getString("paymentStatusTitle"));
				order.setPaymentMethod(ord.getInt("paymentMethod"));
				order.setPaymentMethodTitle(ord.getString("paymentMethodTitle"));
				order.setUserId(ord.getInt("userId"));
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return order;
	}

	public void save(Orders obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			String orderDate = format.format(obj.getOrderDate());
			// sql query to add data into the table
			String sql = String.format("INSERT INTO orders VALUES (null, '%s', '%s', %d, %d, %d, %d, %d, '%s', %d, '%s', %d, '%s', '%s', %d, '%s')",
			orderDate, 
			obj.getOrderStatus(), 
			obj.getTotalItems(), 
			obj.getItemsSubTotal(),
			obj.getShipmentCharges(), 
			obj.getTotalAmount(), 
			obj.getPaymentStatus(), 
			obj.getPaymentStatusTitle(),
			obj.getPaymentMethod(), 
			obj.getPaymentMethodTitle(), 
			obj.getUserId());
			String message = (db.executeUpdate(sql) > 0) ? "Orders data saved successfully" : "Unable to save Orders data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(Orders obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			String orderDate = format.format(obj.getOrderDate());
			// sql query to add data into the table
			String sql = String.format("UPDATE orders SET orderDate='%s', orderStatus='%s', totalItems=%d, itemsSubTotal=%d, shipmentCharges=%d, totalAmount=%d, paymentStatus=%d, paymentStatusTitle='%s', paymentMethod=%d, paymentMethodTitle='%s', userId='%s', name='%s', email='%s', contact=%d, address='%s' WHERE orderId=%d",
			orderDate, 
			obj.getOrderStatus(), 
			obj.getTotalItems(), 
			obj.getItemsSubTotal(),
			obj.getShipmentCharges(), 
			obj.getTotalAmount(), 
			obj.getPaymentStatus(), 
			obj.getPaymentStatusTitle(),
			obj.getPaymentMethod(), 
			obj.getPaymentMethodTitle(), 
			obj.getUserId());
			String message = (db.executeUpdate(sql) > 0) ? "Orders data updated successfully" : "Unable to update Orders data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM orders WHERE orderId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "Orders data deleted successfully" : "Unable to delete Orders data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}
}
