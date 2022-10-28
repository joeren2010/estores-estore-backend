package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Shipments;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class ShipmentsDao implements dao<Shipments> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	public List<Shipments> getAll() {
		List<Shipments> shipmentsList = new ArrayList<Shipments>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM shipments";
			ResultSet shp = db.executeQuery(sql);
			// object "pojo" mapping
			while (shp.next()) {
				// instantiate categories object called "category"
				Shipments shipment = new Shipments();
				// set and map result-set to object
				shipment.setShipmentId(shp.getInt("shipmentId"));
				shipment.setOrderId(shp.getInt("orderId"));
				shipment.setShipmentStatus(shp.getInt("shipmentStatus"));
				shipment.setShipmentTitle(shp.getString("shipmentTitle"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date shipmentDate = format.parse(shp.getString("shipmentDate"));
				shipment.setShipmentDate(shipmentDate);
				Date expectedDeliveryDate = format.parse(shp.getString("expectedDeliveryDate"));
				shipment.setExpectedDeliveryDate(expectedDeliveryDate);
				shipment.setShipmentMethod(shp.getString("shipmentMethod"));
				shipment.setShipmentCompany(shp.getString("shipmentCompany"));
				// add shipment into shipmentsList
				shipmentsList.add(shipment);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return shipmentsList;
	}

	public Shipments getOne(long id) {
		// instantiate categories object called "category"
		Shipments shipment = new Shipments();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM shipments WHERE shipmentId = " + id;
			ResultSet shp = db.executeQuery(sql);
			// object "pojo" mapping
			if (shp.next()) {
				// set and map result-set to object
				shipment.setShipmentId(shp.getInt("shipmentId"));
				shipment.setOrderId(shp.getInt("orderId"));
				shipment.setShipmentStatus(shp.getInt("shipmentStatus"));
				shipment.setShipmentTitle(shp.getString("shipmentTitle"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date shipmentDate = format.parse(shp.getString("shipmentDate"));
				shipment.setShipmentDate(shipmentDate);
				Date expectedDeliveryDate = format.parse(shp.getString("expectedDeliveryDate"));
				shipment.setExpectedDeliveryDate(expectedDeliveryDate);
				shipment.setShipmentMethod(shp.getString("shipmentMethod"));
				shipment.setShipmentCompany(shp.getString("shipmentCompany"));
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return shipment;
	}

	public void save(Shipments obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			String shipmentDate = format.format(obj.getShipmentDate());
			String expectedDeliveryDate = format.format(obj.getExpectedDeliveryDate());
			// sql query to add data into the table
			String sql = String.format("INSERT INTO shipments VALUES (null, %d, '%s', '%s', %d, %d, '%s', '%s')",
			obj.getOrderId(),
			obj.getShipmentStatus(), 
			obj.getShipmentTitle(), 
			shipmentDate, 
			expectedDeliveryDate,
			obj.getShipmentMethod(),
			obj.getShipmentCompany());
			String message = (db.executeUpdate(sql) > 0) ? "Shipments data saved successfully" : "Unable to save Shipments data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(Shipments obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
			String shipmentDate = format.format(obj.getShipmentDate());
			String expectedDeliveryDate = format.format(obj.getExpectedDeliveryDate());
			// sql query to add data into the table
			String sql = String.format("UPDATE shipments SET orderId='%s', shipmentStatus='%s', shipmentTitle='%s', shipmentDate='%s', expectedDeliveryDate='%s', shipmentMethod='%s', shipmentCompany='%s' WHERE shipmentId=%d",
			obj.getOrderId(),
			obj.getShipmentStatus(), 
			obj.getShipmentTitle(), 
			shipmentDate, 
			expectedDeliveryDate,
			obj.getShipmentMethod(),
			obj.getShipmentCompany());
			String message = (db.executeUpdate(sql) > 0) ? "Shipments data updated successfully" : "Unable to update Shipments data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM shipments WHERE shipmentId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "Shipments data deleted successfully" : "Unable to delete Shipments data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}

	}

}
