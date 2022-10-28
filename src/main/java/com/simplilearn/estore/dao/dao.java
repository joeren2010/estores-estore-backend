package com.simplilearn.estore.dao;

import java.util.List;

public interface dao<T> {
	// get all records
	List<T> getAll();
	
	// get a record
	T getOne(long id);
	
	// create a record
	void save(T obj);
	
	// update a record
	void update(T obj);
	
	// delete a record
	void delete(long id);
	
}
