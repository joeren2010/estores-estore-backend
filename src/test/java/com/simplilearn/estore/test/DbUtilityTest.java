package com.simplilearn.estore.test;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.simplilearn.estore.utility.DbUtility;

public class DbUtilityTest {

	// initialize db-utility: method name = utility class name
	DbUtility db = null;
	
	// to be performed before each test
	@BeforeEach
	public void setUp() {
		// call your db connection
		db = DbUtility.getDbUtility();
	}
	
	// to be performed after each test
	@AfterEach
	public void cleanUp() {
		// destroy your db connection
		db.destroy();
		db = null;
	}

	// test connection method
	@Test
	public void testConnection() {
		// initialize your db connection
		db.init();
	}
}
