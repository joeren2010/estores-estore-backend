package com.simplilearn.estore.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtility {
	// schema variable for your db driver connection
	private final String dbSchema = "ecommerce_db";

	// info required for your database connection
	private final String dbUrl = "jdbc:mysql://localhost:3306/" + dbSchema;
	private final String dbUsername = "root";
	private final String dbPassword = "root";
	private Connection connection = null;

	// call jdbc connection statement
	private Statement statement = null;

	// instantiate an object "db" of db-utility
	private static DbUtility db = new DbUtility();

	// create get method for db-utility
	public static DbUtility getDbUtility() {
		return db;
	}

	// registers the driver class
	public DbUtility() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("'" + dbSchema + "'" + " Driver Loader");
			init();
		} catch (Exception e) {
			System.out.println("oops! something went wrong!! " + e.getMessage());
		}
	}

	// initialize db connection
	public void init() {
		try {
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
			System.out.println("'" + dbSchema + "'" + " Connection Established");
		} catch (Exception e) {
			System.out.println("oops! something went wrong!! " + e.getMessage());
		}
	}

	// close db connection
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("'" + dbSchema + "'" + " Connection Closed");
			}

		} catch (Exception e) {
			System.out.println("oops! something went wrong!! " + e.getMessage());
		}
	}

	// save and update operations
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			System.out.println(" < " + dbSchema + " Executing SQL " + sql + " > ");
			result = statement.executeUpdate(sql);
			System.out.println("statement was successful");
		} catch (Exception e) {
			System.out.println("oops! something went wrong!! " + e.getMessage());
		}
		return result;
	}

	// select operations
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			System.out.println(" < " + dbSchema + " Executing SQL " + sql + " > ");
			rs = statement.executeQuery(sql);
			System.out.println("statement was successful");
		} catch (Exception e) {
			System.out.println("oops! something went wrong!! " + e.getMessage());
		}
		return rs;
	}
}
