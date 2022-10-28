package com.simplilearn.estore.admin.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simplilearn.estore.admin.dao.UsersDao;
import com.simplilearn.estore.admin.dto.ResponseDto;
import com.simplilearn.estore.admin.model.Users;

@WebServlet("/users-signup")
public class UsersSignUpController extends HttpServlet {
	// used by jvm to identify the state of an object
	private static final long serialVersionUID = 1L;
	
	// instantiate pojo object
	Users user = new Users();
	
	// instantiate dao object
	UsersDao userDao = new UsersDao();
	
	// create doPost method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// add set-methods for mapping: use dao file as ref
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
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
		
		//validate and save new user sign-up 
		try {
			userDao.save(user);
			dto.setMessage("Enduser created successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to create Enduser!");
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
