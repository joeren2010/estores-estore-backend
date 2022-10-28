package com.simplilearn.estore.admin.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simplilearn.estore.admin.model.Users;
import com.simplilearn.estore.dao.dao;
import com.simplilearn.estore.utility.DbUtility;

public class UsersDao implements dao<Users> {
	// instantiate db-utility using a get method
	DbUtility db = DbUtility.getDbUtility();
	
	// validate user login
	public void login(Users user) {
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM users WHERE email='" + user.getEmail() + "' AND password ='"
					+ user.getPassword() + "'";
			ResultSet usr = db.executeQuery(sql);
			// object "pojo" mapping
			if (usr.next()) {
				user.setUserId(usr.getInt("userId"));
				user.setEmail(usr.getString("email"));
				user.setPassword(usr.getString("password"));
				user.setFullName(usr.getString("fullName"));
				//user.setLoginType(usr.getInt("loginType"));
				String date = usr.getString("addedOn");
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(date);
				user.setAddedOn(addedOn);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public List<Users> getAll() {
		List<Users> userList = new ArrayList<>();
		try {
			// sql query to select all data from the table
			String sql = "SELECT * FROM users";
			ResultSet usr = db.executeQuery(sql);
			while (usr.next()) {
				// instantiate users object called "user"
				Users user = new Users();
				user.setUserId(usr.getInt("userId"));
				user.setEmail(usr.getString("email"));
				user.setPassword(usr.getString("password"));
				user.setFullName(usr.getString("fullName"));
				user.setStreet(usr.getString("street"));
				user.setCity(usr.getString("city"));
				user.setState(usr.getString("state"));
				user.setCountry(usr.getString("country"));
				user.setPincode(usr.getInt("pincode"));
				user.setImage(usr.getString("image"));
				user.setContact(usr.getLong("contact"));
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOnDate = format.parse(usr.getString("addedOn"));
				user.setAddedOn(addedOnDate);
				// add user into usersList
				userList.add(user);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return userList;
	}

	public Users getOne(long id) {
		// instantiate users object called "user"
		Users user = new Users();
		try {
			// sql query to select data from the table based on conditions
			String sql = "SELECT * FROM users WHERE userId = " + id;
			ResultSet usr = db.executeQuery(sql);
			if (usr.next()) {
				user.setUserId(usr.getInt("userId"));
				user.setEmail(usr.getString("email"));
				user.setPassword(usr.getString("password"));
				user.setFullName(usr.getString("fullName"));
				user.setStreet(usr.getString("street"));
				user.setCity(usr.getString("city"));
				user.setState(usr.getString("state"));
				user.setCountry(usr.getString("country"));
				user.setPincode(usr.getInt("pincode"));
				user.setImage(usr.getString("image"));
				user.setContact(usr.getLong("contact"));
				String date = usr.getString("addedOn");
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
				Date addedOn = format.parse(date);
				user.setAddedOn(addedOn);
			}
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
		return user;
	}

	public void save(Users obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOnDate = format.format(obj.getAddedOn());
			// sql query to add data into the table
			String sql = String.format("INSERT INTO users VALUES(null, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, '%s', %d, '%s')",
			obj.getEmail(), 
			obj.getPassword(), 
			obj.getFullName(), 
			obj.getStreet(), 
			obj.getCity(),
			obj.getState(), 
			obj.getCountry(), 
			obj.getPincode(), 
			obj.getImage(), 
			obj.getContact(), 
			addedOnDate);
			String message = (db.executeUpdate(sql) > 0) ? "Users data saved successfully" : "Unable to save Users data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void update(Users obj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = format.format(obj.getAddedOn());
			// sql query to update data in the table
			String sql = String.format("UPDATE users SET email='%s', password='%s', fullName='%s', street='%s', city='%s', state='%s', country='%s', pincode='%s', image='%s', contact='%d', addedOn='%s' WHERE userId=%d",
			obj.getEmail(), 
			obj.getPassword(), 
			obj.getFullName(), 
			obj.getStreet(), 
			obj.getCity(),
			obj.getState(), 
			obj.getCountry(), 
			obj.getPincode(), 
			obj.getImage(), 
			obj.getContact(), 
			addedOn,
			obj.getUserId());
			String message = (db.executeUpdate(sql) > 0) ? "Users data updated successfully" : "Unable to update Users data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}

	public void delete(long id) {
		try {
			// sql query to delete data from the table based on conditions
			String sql = "DELETE FROM users WHERE userId = " + id;
			String message = (db.executeUpdate(sql) > 0) ? "Users data deleted successfully" : "Unable to delete Users data";
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("oops! something went wrong:: " + e.getMessage());
		}
	}
}
