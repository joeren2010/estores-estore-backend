package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Admins;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class AdminsDao implements dao<Admins> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();

	// validate admin login
	public void login(Admins admin) {
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM admins WHERE email='" + admin.getEmail() + "' AND password ='"
					+ admin.getPassword() + "'";
			ResultSet adm = db.executeQuery(sql);
			// object "pojo" mapping
			if (adm.next()) {
				admin.setAdminId(adm.getInt("adminId"));
				admin.setEmail(adm.getString("email"));
				admin.setPassword(adm.getString("password"));
				admin.setFullName(adm.getString("fullName"));
				admin.setLoginType(adm.getInt("loginType"));
				String date = adm.getString("addedOn");
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(date);
				admin.setAddedOn(addedOn);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public List<Admins> getAll() {
		List<Admins> adminList = new ArrayList<Admins>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM admins";
			ResultSet adm = db.executeQuery(sql);
			// object "pojo" mapping
			while (adm.next()) {
				// instantiate admin object called "admin"
				Admins admin = new Admins();
				admin.setAdminId(adm.getInt("adminId"));
				admin.setEmail(adm.getString("email"));
				admin.setPassword(adm.getString("password"));
				admin.setFullName(adm.getString("fullName"));
				admin.setLoginType(adm.getInt("loginType"));
				String date = adm.getString("addedOn");
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(date);
				admin.setAddedOn(addedOn);
				// add admin into adminsList
				adminList.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return adminList;
	}

	public Admins getOne(long id) {
		// instantiate an admin object "admin"
		Admins admin = new Admins();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM admins WHERE adminId=" + id;
			ResultSet adm = db.executeQuery(sql);
			// object "pojo" mapping
			if (adm.next()) {
				admin.setAdminId(adm.getInt("adminId"));
				admin.setEmail(adm.getString("email"));
				admin.setPassword(adm.getString("password"));
				admin.setFullName(adm.getString("fullName"));
				admin.setLoginType(adm.getInt("loginType"));
				String date = adm.getString("addedOn");
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(date);
				admin.setAddedOn(addedOn);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return admin;
	}
	
	public void save(Admins obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOnDate = format.format(obj.getAddedOn());
			// sql query to add data into the table
			String sql = String.format("INSERT INTO admins VALUES(null, '%s', '%s', '%s', %d, %d)",
			obj.getEmail(), 
			obj.getPassword(), 
			obj.getFullName(), 
			obj.getLoginType(), 
			addedOnDate);
			String message = (db.executeUpdate(sql) > 0) ? "Admins data saved successfully" : "Unable to save Admins data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(Admins obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			// sql query to update data in the table
			String sql = String.format("UPDATE admins SET email='%s', password='%s', fullName='%s', loginType='%s', addedOn='%s' WHERE adminId=%d",
			obj.getEmail(), 
			obj.getPassword(), 
			obj.getFullName(), 
			obj.getLoginType(),
			addedOn,
			obj.getAdminId());
			String message = (db.executeUpdate(sql) > 0) ? "Admins data updated successfully" : "Unable to update Admins data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM admins WHERE adminId = " + id;
			int rowAffected = db.executeUpdate(sql);
			String message = (rowAffected > 0) ? "Admin record deleted successfully" : "Unable to delete Admin data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}

	}
}
