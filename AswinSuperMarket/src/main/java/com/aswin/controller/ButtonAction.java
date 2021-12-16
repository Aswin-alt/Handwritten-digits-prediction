package com.aswin.controller;

import java.util.Map;
import java.util.Set;

import com.aswin.model.Customer;
import com.aswin.model.Representative;
import com.aswin.model.Stock;

public class ButtonAction {
	
	public static String backButton(String target) {
		String back = "<form class=\"container w-25 text-center\" style=\"margin-top: 100px;\" action=\"" + target + "\" method=\"get\"><button type=\"submit\">Back</button></form>";
		return back;
	}
	
	public static String createCustomerButton() {
		String createCustomer = "<form class=\"container w-25 text-center\" style=\"margin-top: 100px;\" action=\"/AswinSuperMarket/createcustomer.jsp\" method=\"get\"><button type=\"submit\">Create Customer</button></form>";
		return createCustomer;
	}
	
	public static String selectCustomerButton(Customer c) {
		String createCustomer = "<form class=\"container w-25 text-center\" style=\"margin-top: 100px;\" action=\"customercontroller\" method=\"post\"><button type=\"submit\" name=\"phone\" value=\"" + c.getCustPhone() + "\">Customer Details</button></form>";
		return createCustomer;
	}

	
	public static String customerDetails(Customer c) {
		String customer = "<h2>Hi " + c.getCustName() + "!</h2>\n"
				+ "<b> Customer Id : </b>" + c.getCustId() + "<br>\n"
				+ "<b> Customer Name : </b>" + c.getCustName() + "<br>\n"
				+ "<b>	Customer Email Id : </b>" + c.getCustEmail() + "<br>\n"
				+ "<b>	Customer Phone No. : </b>" + c.getCustPhone() + "<br>\n"
				+ "<b>	Total Amount products Buyed : </b>" + c.getTotalAmount();
		return customer;
	}
	
	
	public static String dispalyFormat() {
		String tableFromat = "<h1 align=\"center\"><font><strong>Customer Details</strong></font></h1>\n"
				+ "	<table align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n"
				+ "	<tr>\n"
				+ "	</tr>\n"
				+ "	<tr bgcolor=\"#DEB887\">\n"
				+ "		<td><b>Customer ID</b></td>\n"
				+ "		<td><b>Customer Name</b></td>\n"
				+ "		<td><b>Customer Email ID</b></td>\n"
				+ "		<td><b>Customer Phone No.</b></td>\n"
				+ "		<td><b>Total Amount Buyed</b></td>\n"
				+ "	</tr>";
		return tableFromat;
	}
	
	public static String displayTableCustomer(Customer c) {
		
		String tableData = "<tr>\n"
				+ "		<td>" + c.getCustId() + "</td>\n"
				+ "		<td>" + c.getCustName() + "</td>\n"
				+ "		<td>" + c.getCustEmail() + "</td>\n"
				+ "		<td>" + c.getCustPhone() + "</td>\n"
				+ "		<td>" + c.getTotalAmount() + "</td>\n"
				+ "		<td>" + selectCustomerButton(c) + "</td>\n"
				+ "	</tr>";
		return tableData;
		
	}
	
	
	public static String billForm(Map<Integer, Representative> repMap) {
		String billForm = "<h2 style = \"text-align : center;\">BILL</h2>\n"
				+ "	<br><br>\n"
				+ "	<form name=\"bill\" method=\"post\" id=\"bill\" action=\"billcontroller2\">\n"
				+ "		\n"
				+ "		<label class=\"form-label text-left\" for=\"repid\"><b>Representative ID</b></label>\n"
//				+ "		<input type=\"number\" id=\"repid\" min=\"1\" class=\"form-control\" size=\"50\" name=\"repid\" Required/>\n"
				+ "     <select name=\"repid\" id=\"repid\">\n";
		
				Set<Integer> keys = repMap.keySet();
				for(Integer key : keys) {
					billForm = billForm + "     	<option value=\"" + repMap.get(key).getRepId() + "\">" + repMap.get(key).getRepId() + "</option>\n";
				}
				
				billForm = billForm + "     </select>"
				+ "		<br>\n"
				+ "		\n"
				+ "		<label class=\"form-label text-left\" for=\"date\"><b>Date of Purchase</b></label>\n"
				+ "		<input type=\"date\" id=\"date\" class=\"form-control\" size=\"50\" name=\"date\" Required/>\n"
				+ "		<br>\n"
				+ "		\n"
				+ "		<label class=\"form-label text-left\" for=\"discount\"><b>Discount</b></label>\n"
				+ "		<input type=\"number\" id=\"discount\" min=\"0\" max=\"100\" class=\"form-control\" size=\"50\" name=\"discount\" Required/>\n"
				+ "		<br>\n"
				+ "		<label class=\"form-label text-left\" for=\"paymode\"><b>Payment Mode</b></label>\n"
				+ "  	<select name=\"paymode\" id=\"paymode\">\n"
				+ "    		<option value=\"Cash\">Cash</option>\n"
				+ "    		<option value=\"Debit Card\">Debit Card</option>\n"
				+ "    		<option value=\"Credit Card\">Credit Card</option>\n"
				+ "    		<option value=\"UPI Mode\">UPI</option>\n"
				+ "  	</select>"
				+ "		<br>\n"
				+ "		\n"
				+ "		<p style=\"text-align:center;font-size:20px;\"><b>Stocks Available</b></p><br>";
		return billForm;
	}
	
	public static String dispalyStockFormat() {
		String tableFromat = " <table align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n"
				+ "	<tr>\n"
				+ "	</tr>\n"
				+ "	<tr bgcolor=\"#DEB887\">\n"
				+ "		<td><b>Stock ID</b></td>\n"
				+ "		<td><b>Stock Name</b></td>\n"
				+ "		<td><b>No of Stocks Available</b></td>\n"
				+ "		<td><b>Price</b></td>\n"
				+ "	</tr>";
		return tableFromat;
	}
	
	public static String displayTableStock(Stock s) {
		
		String tableData = "<tr id='" + s.getStockId() + "'>\n"
				+ "		<td>" + s.getStockId() + "</td>\n"
				+ "		<td>" + s.getStockName() + "</td>\n"
				+ "		<td>" + s.getStockAvailable() + "</td>\n"
				+ "		<td>" + s.getStockPrice() + "</td>\n"
				+ "		<td>" + "<input type=\"checkbox\" id=\"stockid\" name=\"stockid\" value='" + s.getStockId() + "' onclick=\"quantity('" + s.getStockId() + "', '" + s.getStockAvailable() + "')\">\n"
				+ "	</tr>";
		return tableData;
	}
	
	public static String stockjs() {
		
		String tableData = "<script type=text/javascript>\n"
				+ "		function quantity(id, available) {\n"
				+ "			var tr = document.getElementById(id);\n"
				+ "			var t = document.createElement(\"INPUT\");\n"
				+ "			t.setAttribute(\"type\", \"number\");\n"
				+ "			t.setAttribute(\"min\", \"1\");\n"
				+ "			t.setAttribute(\"max\", available);\n"
				+ "			t.setAttribute(\"placeholder\", \"quantity\");\n"
				+ "			t.setAttribute(\"name\", \"stockidQ\");\n"
				+ "			tr.appendChild(t);\n"
				+ "		}\n"
				+ "	</script>";
		return tableData;
	}
	
	public static String stockFormEnd() {
		String tableData = "<button type=\"submit\" style=\"margin-left:85%;\">Submit</button>\n"
				+ "	</form>";
		return tableData;
	}
	
	
	//Bill Details1
	public static String billDetails1(int billId, int custId, String custName, String date, int repId, String paymode) {
		String tableData = "<h2 align=\"center\"><font><strong>BILL DETAILS</strong></font></h2>\n"
				+ "	<table align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Bill ID </b></td>\n"
				+ "		<td>" + billId + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Customer ID </b></td>\n"
				+ "		<td>" + custId + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Customer Name </b></td>\n"
				+ "		<td>" + custName + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Date of Purchase </b></td>\n"
				+ "		<td>" + date + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Representative ID </b></td>\n"
				+ "		<td>" + repId + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Payment Mode </b></td>\n"
				+ "		<td>" + paymode + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr bgcolor=\"#DEB887\">\n"
				+ "		<td><b>Stock ID</b></td>\n"
				+ "		<td><b>Stock Name</b></td>\n"
				+ "		<td><b>Stock Price</b></td>\n"
				+ "		<td><b>Quantity</b></td>\n"
				+ "		<td><b>Total</b></td>\n"
				+ "		</tr>";
		return tableData;
	}
	
	//Bill details2
	public static String billDetails2(int stockId, String stockName, double stockPrice, int quantity, double total) {
		String tableData = "<tr>\n"
				+ "		<td>" + stockId + "</td>\n"
				+ "		<td>" + stockName + "</td>\n"
				+ "		<td>" + stockPrice + "</td>\n"
				+ "		<td>" + quantity + "</td>\n"
				+ "		<td>" + total + "</td>\n"
				+ "		</tr>";
		return tableData;
	}
	
	//Bill details3
	public static String billDetails3(double totalAmount, double discount, double netTotalAmount) {
		String tableData = "<tr >\n"
				+ "		<td><b>Grand Total </b></td>\n"
				+ "		<td>" + totalAmount + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Discount </b></td>\n"
				+ "		<td>" + discount + "</td>\n"
				+ "		</tr>\n"
				+ "		\n"
				+ "		<tr >\n"
				+ "		<td><b>Net Grand Total </b></td>\n"
				+ "		<td>" + netTotalAmount + "</td>\n"
				+ "		</tr>\n"
				+ "	</table>";
		return tableData;
	}
}
