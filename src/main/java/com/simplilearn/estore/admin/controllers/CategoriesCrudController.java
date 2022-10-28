package com.simplilearn.estore.admin.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.estore.admin.dao.CategoriesDao;
import com.simplilearn.estore.admin.dto.ResponseDto;
import com.simplilearn.estore.admin.model.Categories;


@WebServlet("/categories-crud")
public class CategoriesCrudController extends HttpServlet {

	// used by jvm to identify the state of an object
	private static final long serialVersionUID = 1L;
	
	// instantiate pojo object
	Categories category = new Categories();
	
	// instantiate dao object
	CategoriesDao categoryDao = new CategoriesDao();
	
	// create doGet method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// create id as a request parameter
		String id = request.getParameter("id");
		
		// map categories object list
		List<Categories> categoryList = new ArrayList<Categories>();

		// add and validate category-list
		if (id != null) {
			Categories category = categoryDao.getOne(Long.parseLong(id));
			categoryList.add(category);
		} 
		else {
			categoryList = categoryDao.getAll();
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(categoryList);	//convert java object to json string
		PrintWriter out = response.getWriter();  		//instantiate print-writer object called "out"
		response.setContentType("application/json");  	//sets the content type of the response sent to client
		response.setCharacterEncoding("UTF-8");  		//sets the character encoding of the response sent to client
		out.print(jsonResponse);  						//displays json string as text
		out.flush();  									//clears all elements from the print-writer stream 
	}
	
	
	// create doPost method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// add set-methods for mapping: use dao file as ref
		//category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		category.setCategoryName(request.getParameter("categoryName"));
		category.setCategoryDescription(request.getParameter("categoryDescription"));
		category.setCategoryImageUrl(request.getParameter("categoryImageUrl"));
		category.setActive(Integer.parseInt(request.getParameter("active")));
		category.setAddedOn(new Date());
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and save new category 
		try {
			categoryDao.save(category);
			dto.setMessage("Category created successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to create Category!");
			dto.setError(e.toString());
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(dto);	//convert java object to json string
		PrintWriter out = response.getWriter();  		//instantiate print-writer object called "out"
		response.setContentType("application/json");  	//sets the content type of the response sent to client
		response.setCharacterEncoding("UTF-8");  		//sets the character encoding of the response sent to client
		out.print(jsonResponse);  						//displays json string as text
		out.flush();  									//clears all elements from the print-writer stream 
	}	
	
	
	// create doPut method
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// add set-methods for mapping: use dao file as ref
		category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		category.setCategoryName(request.getParameter("categoryName"));
		category.setCategoryDescription(request.getParameter("categoryDescription"));
		category.setCategoryImageUrl(request.getParameter("categoryImageUrl"));
		category.setActive(Integer.parseInt(request.getParameter("active")));
		category.setAddedOn(new Date());
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and update category info 
		try {
			categoryDao.update(category);
			dto.setMessage("Category updated successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to update Category!");
			dto.setError(e.toString());
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(dto);	//convert java object to json string
		PrintWriter out = response.getWriter();  		//instantiate print-writer object called "out"
		response.setContentType("application/json");  	//sets the content type of the response sent to client
		response.setCharacterEncoding("UTF-8");  		//sets the character encoding of the response sent to client
		out.print(jsonResponse);  						//displays json string as text
		out.flush();  									//clears all elements from the print-writer stream 
	}
	
	
	// create doDelete method
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// create id as a request parameter
		String id = request.getParameter("id");
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and delete category info 
		try {
			categoryDao.delete(Integer.parseInt(id));
			dto.setMessage("Category deleted successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to delete Category!");
			dto.setError(e.toString());
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(dto);	//convert java object to json string
		PrintWriter out = response.getWriter();  		//instantiate print-writer object called "out"
		response.setContentType("application/json");  	//sets the content type of the response sent to client
		response.setCharacterEncoding("UTF-8");  		//sets the character encoding of the response sent to client
		out.print(jsonResponse);  						//displays json string as text
		out.flush();  									//clears all elements from the print-writer stream 
	}
}
