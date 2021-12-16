package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aswin.dao.SuperMarketDAO;
import com.aswin.dao.ValidateData;
import com.aswin.model.Customer;
import com.aswin.model.EnumError;
import com.aswin.model.Representative;
import com.aswin.model.Stock;

public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BillController() {
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
				
				doBill(request, response, c);
			}
			catch(SQLException e) {
				System.out.println(e);
				out.println("<h2>" + EnumError.NEWCUSTOMER.getMessage() + "</h2><br>");
				out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
				out.println(ButtonAction.createCustomerButton());
			}
		}
		else {
			out.println(EnumError.INVALIDINPUT.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
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
				doBill(request, response, c);
			}
			catch(SQLException e) {
				out.println("<h2>" + EnumError.NEWCUSTOMER.getMessage() + "</h2><br>");
				out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
				out.println(ButtonAction.createCustomerButton());
			}
		}
		else {
			out.println(EnumError.INVALIDINPUT.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
		}
	}
	
	
	protected void doBill(HttpServletRequest request, HttpServletResponse response, Customer c) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session= request.getSession();
		session.setAttribute("customer", c);
		
		try {
			//Rep dropdown
			Map<Integer, Representative> repMap = new HashMap<>();
			repMap = SuperMarketDAO.getInstance().getAllRep();
			
			String billForm = ButtonAction.billForm(repMap);
			out.println(billForm);
		
			Map<Integer, Stock> stockMap = SuperMarketDAO.getInstance().getAllStock();
			
			Set<Integer> keys = stockMap.keySet();
			
			if (!keys.isEmpty()) 
			{	
				out.println(ButtonAction.stockjs());
				String stockTableFormat = ButtonAction.dispalyStockFormat();
				out.println(stockTableFormat);
	
				String stockDetail;
				
				//Print Stock Details
				for(Integer key : keys) {
					stockDetail = ButtonAction.displayTableStock(stockMap.get(key));
					out.println(stockDetail);
				}
				out.println(ButtonAction.stockFormEnd());
				out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
			}
			else {
				out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		catch (NumberFormatException e) {
			out.println(EnumError.INPUTNOTENTERED.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
		}
	}
}
