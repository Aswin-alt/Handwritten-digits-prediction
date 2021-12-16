package com.aswin.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.aswin.model.User;

public class UserStore {
	
	private static UserStore instance;
	private static Connection connection = null;
	private static PreparedStatement preparedstatement = null;
	private static Statement statement = null;

	//Singleton Instance
	public static UserStore getInstance() {
		if(instance == null) {
			synchronized(UserStore.class) {
				if(instance == null) {
					instance = new UserStore();
				}
			}
		}
		return instance;
	}

	// private constructor so people know to use the getInstance() function instead
	private UserStore(){
		System.out.println("Hi UserStore Constructor");
	}
	
	private static Connection getConnection() {
		try {
			Class.forName(UserStoreConstants.MYSQL_DRIVER); 
			String url = "jdbc:mysql://localhost:3306/"+UserStoreConstants.SCHEMA+"?useSSL=false";
			connection = DriverManager.getConnection(url,UserStoreConstants.MYSQL_USER_NAME,UserStoreConstants.MYSQL_PASSWORD);
		} 
		catch (SQLException | ClassNotFoundException e) {
			System.out.println("SQL Exception! " + e);
		}
		return connection;
	}
	
	//Store user method to set username
	public User insertUser(String username) throws SQLException {
		//DB
		try {
			connection = getConnection();
		
			String INSERT_DATA_USERS = "insert into users(username) values(?);";
			preparedstatement = connection.prepareStatement(INSERT_DATA_USERS);
			preparedstatement.setString(1, username);
			preparedstatement.executeUpdate();
			
			String INSERT_DATA_VISIT = "insert into visit(userId) select userId from users where username = ?;";
			preparedstatement = connection.prepareStatement(INSERT_DATA_VISIT);
			preparedstatement.setString(1, username);
			preparedstatement.executeUpdate();
		}
		finally {
			if(preparedstatement != null)
				preparedstatement.close();
			if(connection != null)
				connection.close();
		}
		User u = getUser(username);
		return u;
	}
	
	
	public User getUser(int userId) throws SQLException{
		User u;
		
		try {
			connection = getConnection();
			
			String INSERT = "select users.userId, users.username, visit.visitCount from users INNER JOIN visit on users.userId = visit.userId where users.userId = " + userId + ";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(INSERT);
			rs.next();
			String username = rs.getString(2);
			int visitCount = Integer.parseInt(rs.getString(3));
			
			u = new User(userId, username, visitCount);
		}
		finally {
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
		return u;
	}
	
	
	public User getUser(String username) throws SQLException{
		User u;
		try {
			connection = getConnection();
			String INSERT = "select users.userId, users.username, visit.visitCount from users INNER JOIN visit on users.userId = visit.userId where users.username = \"" + username + "\";";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(INSERT);
			rs.next();
			int userId = rs.getInt(1);
			int visitCount = Integer.parseInt(rs.getString(3));
			
			u = new User(userId, username, visitCount);
		}
		finally {
			if(connection != null) {
				connection.close();
			}
		}
		return u;
	}
	
	public Map<Integer, User> getAllUser() throws SQLException {
		Map<Integer, User> userMap = new HashMap<>();
		try {
			connection = getConnection();
	
			String INSERT_ALL = "select users.userId, users.username, visit.visitCount from users INNER JOIN visit on users.userId = visit.userId;";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(INSERT_ALL);
			while(rs.next()) {
				int userId = Integer.parseInt(rs.getString(1));
				String username = rs.getString(2);
				int visitCount = Integer.parseInt(rs.getString(3));
				
				User u = new User(userId, username, visitCount);
				userMap.put(userId, u);
			}
		}
		finally {
			if(statement != null) 
				statement.close();
			if(connection != null) 
				connection.close();
		}
		return userMap;
	}
	
	//Offset
	public Map<Integer, User> getAllUser(int offset) throws SQLException {
		Map<Integer, User> userMap = new HashMap<>();
		try {
			connection = getConnection();
	
			offset = (offset - 1) * 5;
			String INSERT_ALL = "select users.userId, users.username, visit.visitCount from users INNER JOIN visit on users.userId = visit.userId LIMIT " + offset + ",5";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(INSERT_ALL);
			while(rs.next()) {
				int userId = Integer.parseInt(rs.getString(1));
				String username = rs.getString(2);
				int visitCount = Integer.parseInt(rs.getString(3));
				
				User u = new User(userId, username, visitCount);
				userMap.put(userId, u);
			}
		}
		finally {
			if(statement != null) 
				statement.close();
			if(connection != null) 
				connection.close();
		}
		return userMap;
	}
	
	public User incrementVisit(int userId) throws SQLException {
		connection = getConnection();
		User u;
		//DB
		try {
			String UPDATE_VISIT = "update visit set visitCount = visitCount + 1 where userId = ?;";
			PreparedStatement preparedstatement = connection.prepareStatement(UPDATE_VISIT);
			preparedstatement.setInt(1, userId);
			preparedstatement.executeUpdate();
			u = getUser(userId);
		}
		finally {
			if(preparedstatement != null)
				preparedstatement.close();
			if(connection != null)
				connection.close();
		}
		return u;
	}
	
	public User decrementVisit(int userId) throws SQLException {
		User u;
		//DB
		try {
			connection = getConnection();
			String UPDATE_VISIT = "update visit set visitCount = visitCount - 1 where userId = ?;";
			PreparedStatement preparedstatement = connection.prepareStatement(UPDATE_VISIT);
			preparedstatement.setInt(1, userId);
			preparedstatement.executeUpdate();
			u = getUser(userId);
		}
		finally {
			if(preparedstatement != null)
				preparedstatement.close();
			if(connection != null)
				connection.close();
		}
		return u;
	}
	
	public User updateUser(int userId, String username) throws SQLException {
		User u;
		//DB
		try {
			connection = getConnection();
			String UPDATE_USER = "update users set username = ? where userId = ?;";
			PreparedStatement preparedstatement = connection.prepareStatement(UPDATE_USER);
			preparedstatement.setString(1, username);
			preparedstatement.setInt(2, userId);
			preparedstatement.executeUpdate();
			u = getUser(userId);
		}
		finally {
			if(preparedstatement != null)
				preparedstatement.close();
			if(connection != null)
				connection.close();
		}
		return u;
	}
	
	
	public int deleteUser(int userId) throws SQLException {
		//DB
		try {
			connection = getConnection();
			String DELETE_USER = "DELETE from users where userId = ?;";	
			PreparedStatement preparedstatement = connection.prepareStatement(DELETE_USER);
			preparedstatement.setInt(1, userId);
			preparedstatement.executeUpdate();
		}
		finally {
			if(preparedstatement != null)
				preparedstatement.close();
			if(connection != null)
				connection.close();
		}
		return userId;
	}

}

//public User insertUser(String username) throws SQLException {
//
//connection = getConnection();
////DB
//try {
//	for(int i = 1000;i<10000;i++) {
//	String INSERT_DATA_USERS = "insert into users(username) values(\""+ username + i +"\");";
//	PreparedStatement preparedstatement = connection.prepareStatement(INSERT_DATA_USERS);
////	preparedstatement.setString(1, username);
//	preparedstatement.executeUpdate();
//				
//	String INSERT_DATA_VISIT = "insert into visit(userId) select userId from users where username = \"" + username + i + "\";";
//	preparedstatement = connection.prepareStatement(INSERT_DATA_VISIT);
////	preparedstatement.setString(1, username);
//	preparedstatement.executeUpdate();
//	}
//}
//finally {
//	System.out.println("Finally Insert");
//	if(preparedstatement != null)
//		preparedstatement.close();
//	if(connection != null)
//		connection.close();
//}
//User u = getUser(username + "0");
//return u;
//}