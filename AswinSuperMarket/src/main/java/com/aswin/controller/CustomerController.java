package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aswin.dao.SuperMarketDAO;
import com.aswin.dao.ValidateData;
import com.aswin.model.Customer;
import com.aswin.model.EnumError;


public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CustomerController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		boolean validate = ValidateData.validateEmail(email);
		
		if(validate) {
			try {
				Customer c = SuperMarketDAO.getInstance().getCustomer(email);
				out.println(ButtonAction.customerDetails(c));
				out.println(ButtonAction.backButton("customerdetailcontroller"));
			}
			catch(SQLException e) {
				System.out.println(e);
				out.println("<h2>" + EnumError.NEWCUSTOMER.getMessage() + "</h2><br>");
				out.println(ButtonAction.backButton("customerdetailcontroller"));
				out.println(ButtonAction.createCustomerButton());
			}
		}
		else {
			out.println(EnumError.INVALIDINPUT.getMessage());
			out.println(ButtonAction.backButton("customerdetailcontroller"));
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String phoneString = request.getParameter("phone");
		boolean validate = ValidateData.validatePhone(phoneString);
		long phone = Long.parseLong(phoneString);
		
		if(validate) {
			try {
				Customer c = SuperMarketDAO.getInstance().getCustomer(phone);
				out.println(ButtonAction.customerDetails(c));
				out.println(ButtonAction.backButton("customerdetailcontroller"));
			}
			catch(SQLException e) {
				out.println("<h2>" + EnumError.NEWCUSTOMER.getMessage() + "</h2><br>");
				out.println(ButtonAction.backButton("customerdetailcontroller"));
				out.println(ButtonAction.createCustomerButton());
			}
		}
		else {
			out.println(EnumError.INVALIDINPUT.getMessage());
			out.println(ButtonAction.backButton("customerdetailcontroller"));
		}
	}

}
