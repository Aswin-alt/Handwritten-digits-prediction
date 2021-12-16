package com.aswin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aswin.dao.SuperMarketDAO;
import com.aswin.model.Bill;
import com.aswin.model.Customer;
import com.aswin.model.EnumError;
import com.aswin.model.Stock;


public class BillController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BillController2() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//get customer
		HttpSession session= request.getSession();
		Customer c = (Customer)session.getAttribute("customer");
		
		int repId = Integer.parseInt(request.getParameter("repid"));
		String dateOfPurchase = request.getParameter("date");
		int discount = Integer.parseInt(request.getParameter("discount"));
		String paymentMode = request.getParameter("paymode");
		
		try {
			//get Payment ID
			int paymentId = SuperMarketDAO.getInstance().getPaymentMode(paymentMode);
			
			String stockId[] = request.getParameterValues("stockid");
			String stockIdQ[] = request.getParameterValues("stockidQ");
			
			double totalAmount = 0;
			//Total Calculation
			totalAmount = SuperMarketDAO.getInstance().getParticularStock(stockId, stockIdQ);
			
			double netTotalAmount = totalAmount - ((totalAmount * discount) / 100);
			
			Bill b = new Bill(dateOfPurchase, c.getCustId(), totalAmount, paymentId, repId, discount, netTotalAmount);
			//Insert Bill
			int billId = SuperMarketDAO.getInstance().createBill(b);
			b.setBillId(billId);
			
			//Insert products_Buyed
			//Bill UI
			Stock s;
			out.println(ButtonAction.billDetails1(b.getBillId(), c.getCustId(), c.getCustName(), b.getDateOfPurchase(), b.getRepId(), paymentMode));
			for(int i = 0;i < stockId.length;i++) 
			{
				s = SuperMarketDAO.getInstance().insertProduct(Integer.parseInt(stockId[i]), billId, Integer.parseInt(stockIdQ[i]));
				double total = s.getStockPrice() * Integer.parseInt(stockIdQ[i]);
				out.println(ButtonAction.billDetails2(Integer.parseInt(stockId[i]), s.getStockName(), s.getStockPrice(), Integer.parseInt(stockIdQ[i]), total));
			}
			out.println(ButtonAction.billDetails3(totalAmount, discount, netTotalAmount));
			out.println(ButtonAction.backButton("/AswinSuperMarket/index.jsp"));
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		catch(NumberFormatException e) {
			out.println(EnumError.INPUTNOTENTERED.getMessage());
			out.println(ButtonAction.backButton("/AswinSuperMarket/customer.jsp"));
		}
		
	}

}
