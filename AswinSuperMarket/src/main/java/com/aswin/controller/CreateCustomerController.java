package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aswin.dao.SuperMarketDAO;
import com.aswin.dao.ValidateData;
import com.aswin.model.Customer;
import com.aswin.model.EnumError;


public class CreateCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CreateCustomerController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String custName = request.getParameter("name");
		String custEmail = request.getParameter("email");
		String custPhone = request.getParameter("phone");
		
		boolean validatePhone = ValidateData.validatePhone(custPhone);
		boolean validateEmail = ValidateData.validateEmail(custEmail);
		
		if(validatePhone && validateEmail) {
			try {
				Customer c = new Customer(custName, custEmail, custPhone, 0);
				boolean created = SuperMarketDAO.getInstance().createCustomer(c);
				
				if(created) {
					out.println("<h2>Customer Created Successfully</h2>");
					out.println(ButtonAction.backButton("/AswinSuperMarket/index.jsp"));
				}
				else {
					out.println("<h2>Unable to create due to some problem!</h2>");
					out.println(ButtonAction.backButton("/AswinSuperMarket/createcustomer.jsp"));
				}
			}
			catch(SQLException e) {
				out.println(EnumError.SQLUSERALREADYEXIST.getMessage());
				out.println(ButtonAction.backButton("/AswinSuperMarket/createcustomer.jsp"));
			}
		}
		else {
			out.println(EnumError.INVALIDINPUT.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/createcustomer.jsp"));
		}
	}

}
