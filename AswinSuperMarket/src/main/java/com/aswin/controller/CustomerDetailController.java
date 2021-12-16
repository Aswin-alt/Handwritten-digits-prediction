package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aswin.dao.SuperMarketDAO;
import com.aswin.model.Customer;
import com.aswin.model.EnumError;


public class CustomerDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CustomerDetailController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			Map<Integer, Customer> customerMap;
			
			customerMap = SuperMarketDAO.getInstance().getAllCustomer();
			
			Set<Integer> keys = customerMap.keySet();
			
			if (!keys.isEmpty()) 
			{	
				out.println(ButtonAction.dispalyFormat());
	
				String customerDetail;
				
				//Print Student Details
				for(Integer key : keys) {
					customerDetail = ButtonAction.displayTableCustomer(customerMap.get(key));
					out.println(customerDetail);
				}
//				out.println(ButtonAction.stockFormEnd());	
				
				out.println(ButtonAction.backButton("/AswinSuperMarket/index.jsp"));
				out.println(ButtonAction.createCustomerButton());
			} 
			else {
				out.println(ButtonAction.backButton("/AswinSuperMarket/index.jsp"));
				out.println(ButtonAction.createCustomerButton());
			}
		}
		catch(SQLException e){
			out.println(EnumError.NOCUSTOMERAVAILABLE.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/index.jsp"));
		}

	}

}
