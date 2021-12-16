package com.aswin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.aswin.model.Bill;
import com.aswin.model.Customer;
import com.aswin.model.RepIncrement;
import com.aswin.model.Representative;
import com.aswin.model.Stock;

public class SuperMarketDAO {

	private static SuperMarketDAO instance;
	private static Connection connection = null;
	private static PreparedStatement preparedstatement = null;
	private static Statement statement = null;

	// Singleton Instance
	public static SuperMarketDAO getInstance() {
		if (instance == null) {
			synchronized (SuperMarketDAO.class) {
				if (instance == null) {
					instance = new SuperMarketDAO();
				}
			}
		}
		return instance;
	}

	// private constructor so people know to use the getInstance() function instead
	private SuperMarketDAO() {
		System.out.println("Hi SuperMarket Constructor");
	}

	private static Connection getConnection() {
		try {
			Class.forName(DBConstants.MYSQL_DRIVER);
			String url = "jdbc:mysql://localhost:3306/" + DBConstants.SCHEMA + "?useSSL=false";
			connection = DriverManager.getConnection(url, DBConstants.MYSQL_USER_NAME, DBConstants.MYSQL_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("SQL Exception! " + e);
		}
		return connection;
	}

	// Create Customer
	public Boolean createCustomer(Customer c) throws SQLException {
		// DB
		try {
			connection = getConnection();

			String INSERT_CUSTOMER = "Insert into Customer(Cust_name, Email, Phone_No) values(?, ?, ?);";
			preparedstatement = connection.prepareStatement(INSERT_CUSTOMER);
			preparedstatement.setString(1, c.getCustName());
			preparedstatement.setString(2, c.getCustEmail());
			preparedstatement.setString(3, c.getCustPhone());
			preparedstatement.executeUpdate();
		} finally {
			if (preparedstatement != null)
				preparedstatement.close();
			if (connection != null)
				connection.close();
		}
		return true;
	}

	// Insert products to Products_Buyed table
	public Stock insertProduct(int stockId, int billId, int quantity) throws SQLException {
		Stock s;
		try {
			connection = getConnection();

			String INSERT_PRODUCT = "Insert into Products_Buyed(Stock_Id, Bill_Id, No_Of_Stock, Total) values(?, ?, ?, (Select (Stock_Price * ?) from Stocks where Stock_Id = ?));";
			preparedstatement = connection.prepareStatement(INSERT_PRODUCT);
			preparedstatement.setInt(1, stockId);
			preparedstatement.setInt(2, billId);
			preparedstatement.setInt(3, quantity);
			preparedstatement.setInt(4, quantity);
			preparedstatement.setInt(5, stockId);
			preparedstatement.executeUpdate();

			String GET_PRICE = "Select Stock_Name, Stock_Price from Stocks where Stock_Id = " + stockId + ";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_PRICE);
			rs.next();
			String stockName = rs.getString(1);
			double stockPrice = rs.getDouble(2);
			s = new Stock(stockId, stockName, stockPrice);
		} finally {
			if (statement != null)
				statement.close();
			if (preparedstatement != null)
				preparedstatement.close();
			if (connection != null)
				connection.close();
		}
		return s;
	}

	// Create Bill
	public int createBill(Bill b) throws SQLException {
		int billId;
		try {
			connection = getConnection();

			String INSERT_BILL = "Insert into Bill(Date_Of_Purchase, Cust_Id, Total_Amount, Rep_Id, Payment_Mode_Id, Discount, Total_Net_Amount) values(?, ?, ?, ?, ?, ?, ?);";
			preparedstatement = connection.prepareStatement(INSERT_BILL);
			preparedstatement.setString(1, b.getDateOfPurchase());
			preparedstatement.setInt(2, b.getCustId());
			preparedstatement.setDouble(3, b.getTotalAmount());
			preparedstatement.setInt(4, b.getRepId());
			preparedstatement.setInt(5, b.getPaymentModeId());
			preparedstatement.setDouble(6, b.getDiscount());
			preparedstatement.setDouble(7, b.getTotalNetAmount());
			preparedstatement.executeUpdate();

			String GET_BILL_ID = "Select Bill_Id from Bill order by Bill_Id desc;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_BILL_ID);
			rs.next();
			billId = rs.getInt(1);
		} finally {
			if (statement != null)
				statement.close();
			if (preparedstatement != null)
				preparedstatement.close();
			if (connection != null)
				connection.close();
		}
		return billId;
	}

	// Payment get
	public int getPaymentMode(String paymentMode) throws SQLException {
		int paymentId;

		try {
			connection = getConnection();

			String GET_PAYMENT_MODE = "select Payment_Mode_Id from Payment_Mode where Payment_Mode = \"" + paymentMode
					+ "\";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_PAYMENT_MODE);
			rs.next();
			paymentId = rs.getInt(1);
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return paymentId;
	}

	// Get Stocks of a bill
	public Map<Integer, Stock> getStocks(int billId) throws SQLException {
		Map<Integer, Stock> stockMap = new LinkedHashMap<>();
		try {
			connection = getConnection();

			String GET_STOCK = "Select p.Stock_Id, s.Stock_Name, p.No_Of_Stock, p.Total from Products_Buyed p INNER JOIN Stocks s ON p.Stock_Id = s.Stock_Id where p.Bill_Id = "
					+ billId + ";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_STOCK);
			while (rs.next()) {
				int stockId = rs.getInt(1);
				String stockName = rs.getString(2);
				int noOfStock = rs.getInt(3);
				double total = rs.getDouble(4);
				Stock s = new Stock(stockId, stockName, noOfStock, total);
				stockMap.put(stockId, s);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return stockMap;
	}

	// phone
	public Customer getCustomer(long phone) throws SQLException {
		Customer c;

		try {
			connection = getConnection();

			String GET_CUSTOMER = "select * from Customer where Phone_No = \"" + phone + "\";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_CUSTOMER);
			rs.next();
			int custId = rs.getInt(1);
			String custName = rs.getString(2);
			String custEmail = rs.getString(3);
			String custPhone = rs.getString(4);

			c = new Customer(custId, custName, custEmail, custPhone);
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return c;
	}

	// email
	public Customer getCustomer(String email) throws SQLException {
		Customer c;

		try {
			connection = getConnection();

			System.out.println(email);
			String GET_CUSTOMER = "select * from Customer where Email = \"" + email + "\";";
			System.out.println(GET_CUSTOMER);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(GET_CUSTOMER);
			rs.next();
			int custId = rs.getInt(1);
			String custName = rs.getString(2);
			String custEmail = rs.getString(3);
			String custPhone = rs.getString(4);

			c = new Customer(custId, custName, custEmail, custPhone);
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return c;
	}

	// Customer
	public Map<Integer, Customer> getAllCustomer() throws SQLException {
		Map<Integer, Customer> customerMap = new LinkedHashMap<>();
		try {
			connection = getConnection();

			String SELECT_ALL = "select c.Cust_Id, c.Cust_Name, c.Email, c.Phone_No, SUM(b.Total_Net_Amount) as Total_Buyed_Amount from Customer c INNER JOIN Bill b ON c.Cust_Id = b.Cust_Id group by c.Cust_Id;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);

			while (rs.next()) {
				int custId = rs.getInt(1);
				String custName = rs.getString(2);
				String custEmail = rs.getString(3);
				String custPhone = rs.getString(4);
				double totalAmount = rs.getDouble(5);

				Customer c = new Customer(custId, custName, custEmail, custPhone, totalAmount);
				customerMap.put(custId, c);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return customerMap;
	}

	// Get All Bills
	public Map<Integer, Bill> getAllBill(LocalDate from, LocalDate to, int offset) throws SQLException {
		Map<Integer, Bill> billMap = new LinkedHashMap<Integer, Bill>();
		try {
			connection = getConnection();

			String SELECT_ALL = "Select * from Bill where Date_Of_Purchase >= '" + from + "' and Date_Of_Purchase <= '" + to + "' ORDER BY Date_Of_Purchase DESC LIMIT " + offset + ",10;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);

			while (rs.next()) {
				int billId = rs.getInt(1);
				String date = rs.getString(2);
				int custId = rs.getInt(3);
				double total = rs.getDouble(4);
				int repId = rs.getInt(5);
				int payId = rs.getInt(6);
				double discount = rs.getDouble(7);
				double netTotal = rs.getDouble(8);

				Bill b = new Bill(billId, date, custId, total, repId, payId, discount, netTotal);
				billMap.put(billId, b);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return billMap;
	}

	// Get increment details
	public Map<Integer, RepIncrement> getIncrementDetails(int year, int offset) throws SQLException {
		Map<Integer, RepIncrement> incMap = new LinkedHashMap<>();
		try {
			connection = getConnection();

			String SELECT_ALL = "Select r.Rep_Id, r.Rep_Name, r.Desig_Id, r.DOJ, r.Age, COUNT(b.Bill_Id) as Total_Sales, SUM(b.Total_Net_Amount) as Total_Amount, r.Salary from Bill b INNER JOIN S_Representative r ON r.rep_Id = b.rep_Id where YEAR(Date_Of_Purchase) = "
					+ year + " GROUP BY b.Rep_Id LIMIT " + offset + ",10;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);

			while (rs.next()) {
				int repId = rs.getInt(1);
				String repName = rs.getString(2);
				int DesigId = rs.getInt(3);
				String doj = rs.getString(4);
				int age = rs.getInt(5);
				int totalNoOfSales = rs.getInt(6);
				double totalSalesAmount = rs.getDouble(7);
				double salary = rs.getDouble(8);

				RepIncrement r = new RepIncrement(repId, repName, DesigId, doj, age, totalNoOfSales, totalSalesAmount,
						salary);
				incMap.put(repId, r);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return incMap;
	}

	// Particular stock details
	public int getParticularStock(String stockId[], String quantity[]) throws SQLException {
		int totalAmount = 0;

		try {
			connection = getConnection();

			for (int i = 0; i < stockId.length; i++) {
				int id = Integer.parseInt(stockId[i]);
				String SELECT_PARTICULAR = "Select * from Stocks where Stock_Id = " + id;
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(SELECT_PARTICULAR);

				rs.next();
				int stockId1 = rs.getInt(1);
				String stockName = rs.getString(2);
				int stockAvailable = rs.getInt(3);
				double stockPrice = rs.getDouble(4);

				totalAmount += (stockPrice * Integer.parseInt(quantity[i]));

				// update stock
				String UPDATE_STOCK = "Update Stocks set Stock_Available = Stock_Available - ? where Stock_Id = ?;";
				preparedstatement = connection.prepareStatement(UPDATE_STOCK);
				preparedstatement.setInt(1, Integer.parseInt(quantity[i]));
				preparedstatement.setInt(2, stockId1);
				preparedstatement.executeUpdate();
			}
		} finally {
			if (statement != null)
				statement.close();
			if (preparedstatement != null)
				preparedstatement.close();
			if (connection != null)
				connection.close();
		}
		return totalAmount;
	}

	// Update Rep Salary
	public boolean updateRepSalary(Map<Integer, RepIncrement> incMap) throws SQLException {

		try {
			connection = getConnection();

			// update Rep Salary
			Set<Integer> keys = incMap.keySet();
			for (Integer key : keys) {
				RepIncrement r = incMap.get(key);
				String UPDATE_STOCK = "Update S_Representative set Salary = ? where Rep_Id = ?;";
				preparedstatement = connection.prepareStatement(UPDATE_STOCK);
				preparedstatement.setDouble(1, r.getIncrementedSalary());
				preparedstatement.setInt(2, r.getRepId());
				preparedstatement.executeUpdate();
			}

		} finally {
			if (statement != null)
				statement.close();
			if (preparedstatement != null)
				preparedstatement.close();
			if (connection != null)
				connection.close();
		}
		return true;
	}

	// All Stock
	public Map<Integer, Stock> getAllStock() throws SQLException {
		Map<Integer, Stock> stockMap = new LinkedHashMap<>();
		try {
			connection = getConnection();

			String SELECT_ALL = "select * from Stocks;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);

			while (rs.next()) {
				int stockId = rs.getInt(1);
				String stockName = rs.getString(2);
				int stockAvailable = rs.getInt(3);
				double stockPrice = rs.getDouble(4);

				Stock s = new Stock(stockId, stockName, stockAvailable, stockPrice);
				stockMap.put(stockId, s);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return stockMap;
	}

	// All Representative
	public Map<Integer, Representative> getAllRep() throws SQLException {
		Map<Integer, Representative> repMap = new LinkedHashMap<>();
		try {
			connection = getConnection();

			String SELECT_ALL = "Select r.Rep_Id, r.Rep_Name, r.DOJ, r.Age, r.Salary, r.Desig_Id, d.Desig_Name from S_Representative r INNER JOIN Designation d ON r.Desig_Id = d.Desig_Id;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);

			while (rs.next()) {
				int repId = rs.getInt(1);
				String repName = rs.getString(2);
				String doj = rs.getString(3);
				int age = rs.getInt(4);
				double salary = rs.getDouble(5);
				int desigId = rs.getInt(6);
				String desigName = rs.getString(7);

				Representative r = new Representative(repId, repName, doj, age, salary, desigId, desigName);
				repMap.put(repId, r);
			}
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return repMap;
	}

	// Operations
	// Total Sales Made
	public int getTotalSales(LocalDate from, LocalDate to) throws SQLException {
		int totalSales;
		try {
			connection = getConnection();

			String SELECT = "Select COUNT(Bill_Id) from Bill where Date_Of_Purchase >= '" + from + "' AND Date_Of_Purchase <= '" + to + "';";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);

			rs.next();
			totalSales = rs.getInt(1);
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return totalSales;
	}

	// Total Sales Made Customer
	public Customer getTopCustomer(LocalDate from, LocalDate to, String choice) throws SQLException {
		Customer c;
		try {
			connection = getConnection();
			String SELECT;

			if (choice.equals("amount")) {
				SELECT = "Select c.Cust_Id, c.Cust_Name, c.Phone_No, SUM(b.Total_Net_Amount) as Total from Bill b INNER JOIN Customer c ON c.Cust_Id = b.Cust_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY b.Cust_Id ORDER BY Total DESC;";
			} else {
				SELECT = "Select c.Cust_Id, c.Cust_Name, c.Phone_No, COUNT(b.Bill_Id) as Total from Bill b INNER JOIN Customer c ON c.Cust_Id = b.Cust_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY b.Cust_Id ORDER BY Total DESC;";
			}
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);

			rs.next();
			int custId = rs.getInt(1);
			String custName = rs.getString(2);
			String custPhone = rs.getString(3);
			double totalAmount = rs.getDouble(4);

			c = new Customer();
			c.setCustId(custId);
			c.setCustName(custName);
			c.setCustPhone(custPhone);
			c.setTotalAmount(totalAmount);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return c;
	}

	// Total Sales Made Rep
	public Representative getTopRep(LocalDate from, LocalDate to, String choice) throws SQLException {
		Representative r;
		try {
			connection = getConnection();
			String SELECT;

			if (choice.equals("amount")) {
				SELECT = "Select r.Rep_Id, r.Rep_Name, SUM(b.Total_Net_Amount) as Total from Bill b INNER JOIN S_Representative r ON r.rep_Id = b.rep_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY b.Rep_Id ORDER BY Total DESC;";
			} else {
				SELECT = "Select r.Rep_Id, r.Rep_Name, COUNT(b.Bill_Id) as Total from Bill b INNER JOIN S_Representative r ON r.rep_Id = b.rep_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY b.Rep_Id ORDER BY Total DESC;";
			}
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);

			rs.next();
			int repId = rs.getInt(1);
			String repName = rs.getString(2);
			double totalAmount = rs.getDouble(3);

			r = new Representative();
			r.setRepId(repId);
			r.setRepName(repName);
			r.setTotalAmount(totalAmount);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return r;
	}

	// Sales Made Rep
	public Map<Integer, Representative> getTopRep(LocalDate from, LocalDate to, int offset) throws SQLException {
		Map<Integer, Representative> repMap = new LinkedHashMap<>();
		try {
			connection = getConnection();
			String SELECT = "Select r.Rep_Id, r.Rep_Name, COUNT(b.Bill_Id) as Total from Bill b INNER JOIN S_Representative r ON r.rep_Id = b.rep_Id where b.Date_Of_Purchase >= '"
					+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY b.Rep_Id ORDER BY Total DESC LIMIT "
					+ offset + ",5;";
//			String SELECT = "Select Date_Of_Purchase, Rep_Id, COUNT(Bill_Id) as Total from Bill where Date_Of_Purchase >= '" + from + "'  AND Date_Of_Purchase <= '" + to + "' GROUP BY Date_Of_Purchase, Rep_Id ORDER BY Total,Date_Of_Purchase DESC LIMIT 0,5;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);

			while (rs.next()) {
				int repId = rs.getInt(1);
				String repName = rs.getString(2);
				double totalAmount = rs.getDouble(3);

				Representative r = new Representative();
				r.setRepId(repId);
				r.setRepName(repName);
				r.setTotalAmount(totalAmount);

				repMap.put(repId, r);
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return repMap;
	}

	// Total Sales Made Stock
	public Stock getTopStock(LocalDate from, LocalDate to, String choice) throws SQLException {
		Stock s;
		try {
			connection = getConnection();
			String SELECT;

			if (choice.equals("amount")) {
				SELECT = "Select s.Stock_Id, s.Stock_Name, SUM(p.Total) as Total from Stocks s INNER JOIN Products_Buyed p ON s.Stock_Id = p.Stock_Id INNER JOIN Bill b ON b.Bill_Id = p.Bill_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY p.Stock_Id ORDER BY Total DESC;";
			} else {
				SELECT = "Select s.Stock_Id, s.Stock_Name, COUNT(p.Products_Buyed_Id) as Total from Stocks s INNER JOIN Products_Buyed p ON s.Stock_Id = p.Stock_Id INNER JOIN Bill b ON b.Bill_Id = p.Bill_Id where b.Date_Of_Purchase >= '"
						+ from + "' AND b.Date_Of_Purchase <= '" + to + "' GROUP BY p.Stock_Id ORDER BY Total DESC;";
			}
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT);

			rs.next();
			int stockId = rs.getInt(1);
			String stockName = rs.getString(2);
			double totalAmount = rs.getDouble(3);

			s = new Stock();
			s.setStockId(stockId);
			s.setStockName(stockName);
			s.setTotalAmount(totalAmount);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return s;
	}

}
