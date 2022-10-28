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
import com.simplilearn.estore.admin.dao.UsersDao;
import com.simplilearn.estore.admin.dto.ResponseDto;
import com.simplilearn.estore.admin.model.Users;


@WebServlet("/users-crud")
public class UsersCrudController extends HttpServlet {

	// used by jvm to identify the state of an object
	private static final long serialVersionUID = 1L;
	
	// instantiate pojo object
	Users user = new Users();
	
	// instantiate dao object
	UsersDao userDao = new UsersDao();
	
	// create doGet method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// create id as a request parameter
		String id = request.getParameter("id");
		
		// map users object list
		List<Users> userList = new ArrayList<Users>();

		// add and validate user-list
		if (id != null) {
			Users user = userDao.getOne(Long.parseLong(id));
			userList.add(user);
		} 
		else {
			userList = userDao.getAll();
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(userList);	//convert java object to json string
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
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		// user.setLoginType(2);
		user.setFullName(request.getParameter("fullName"));
		user.setStreet(request.getParameter("street"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setCountry(request.getParameter("country"));
		user.setPincode(Integer.parseInt(request.getParameter("pincode")));
		user.setImage(request.getParameter("image"));
		user.setContact(Long.parseLong(request.getParameter("contact")));
		user.setAddedOn(new Date());
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and update user info 
		try {
			userDao.update(user);
			dto.setMessage("Enduser updated successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to update Enduser!");
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
		
		//validate and delete admin info 
		try {
			userDao.delete(Integer.parseInt(id));
			dto.setMessage("Enduser deleted successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to delete Enduser!");
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
