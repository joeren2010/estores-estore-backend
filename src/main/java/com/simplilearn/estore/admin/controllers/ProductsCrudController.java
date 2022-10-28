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
import com.simplilearn.estore.admin.dao.ProductsDao;
import com.simplilearn.estore.admin.dto.ResponseDto;
import com.simplilearn.estore.admin.model.Products;


@WebServlet("/products-crud")
public class ProductsCrudController extends HttpServlet {

	// used by jvm to identify the state of an object
	private static final long serialVersionUID = 1L;
	
	// instantiate pojo object
	Products product = new Products();
	
	// instantiate dao object
	ProductsDao productDao = new ProductsDao();
	
	// create doGet method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// create id as a request parameter
		String id = request.getParameter("id");
		
		// map products object list
		List<Products> productList = new ArrayList<Products>();

		// add and validate product-list
		if (id != null) {
			Products product = productDao.getOne(Long.parseLong(id));
			productList.add(product);
		} 
		else {
			productList = productDao.getAll();
		}
		
		// printing or displaying the response
		String jsonResponse = new Gson().toJson(productList);	//convert java object to json string
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
		//product.setProductId(Integer.parseInt(request.getParameter("productId")));
		product.setProductTitle(request.getParameter("productTitle"));
		product.setProductDescription(request.getParameter("productDescription"));
		product.setProductCode(request.getParameter("productCode"));
		product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		product.setImages(request.getParameter("images"));
		product.setThumbnailImage(Integer.parseInt(request.getParameter("thumbnailImage")));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setAddedOn(new Date());
		product.setRating(Integer.parseInt(request.getParameter("rating")));
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and save new product 
		try {
			productDao.save(product);
			dto.setMessage("Product created successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to create Product!");
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
		product.setProductId(Integer.parseInt(request.getParameter("productId")));
		product.setProductTitle(request.getParameter("productTitle"));
		product.setProductDescription(request.getParameter("productDescription"));
		product.setProductCode(request.getParameter("productCode"));
		product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		product.setImages(request.getParameter("images"));
		product.setThumbnailImage(Integer.parseInt(request.getParameter("thumbnailImage")));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setAddedOn(new Date());
		product.setRating(Integer.parseInt(request.getParameter("rating")));
		
		// instantiate response-dto object
		ResponseDto dto = new ResponseDto();
		
		//validate and update product info 
		try {
			productDao.update(product);
			dto.setMessage("Product updated successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to update Product!");
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
		
		//validate and delete product info 
		try {
			productDao.delete(Integer.parseInt(id));
			dto.setMessage("Product deleted successfully!");
		} 
		catch (Exception e) {
			dto.setMessage("Unable to delete Product!");
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
