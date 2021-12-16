package com.aswin.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.aswin.dao.JSONResponse;
import com.aswin.dao.UserStore;
import com.aswin.model.EnumError;
import com.aswin.model.EnumSuccess;
import com.aswin.model.User;

//@WebServlet("/usercontroller")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JSONResponse jsonres;
	String message;
	int errCode;
       
    public UserController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestUrl = request.getRequestURI();
		
		try {
			//Regex Validation
			String URL = User.getBaseURL();
			String pattern = URL + "|" + URL + "/[0-9]{4,}";
			boolean validURI = Pattern.matches(pattern, requestUrl);
			
			if(validURI) {
				String id_str = requestUrl.substring((URL + "/").length());
				int id = Integer.parseInt(id_str);
					
				User user = UserStore.getInstance().getUser(id);
				
				JSONObject resJSON = new JSONObject();
				jsonres = JSONResponse.getInstance();
				
				message = EnumSuccess.SUCCESSFULLYFETCHED.getMessage();
				resJSON = jsonres.successResponse(user, message);
		
				response.getOutputStream().println(resJSON.toString(4));
				
			}
			else {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRORURL.getMessage();
				errCode = EnumError.ERRORURL.getErrCode();
				
				response.getOutputStream().println(jsonres.errorResponse(errCode, message).toString(4));
			}
		}
		catch(StringIndexOutOfBoundsException e) {
			try {
				//Regex Validation
				String URL = User.getBaseURL();
				String pattern = URL;
				boolean validURI = Pattern.matches(pattern, requestUrl);
				
				int offset = 0;
				try {
					offset = Integer.parseInt(request.getParameter("offset"));
				}catch(NumberFormatException e1) {
					System.out.println("No offset");
				}
				
				if(validURI) {
					//JSON response
					Map<Integer, User> userMap;
					if(offset == 0) {
						userMap = UserStore.getInstance().getAllUser();
					}
					else {
						userMap = UserStore.getInstance().getAllUser(offset);
					}
					
					Set<Integer> keys = userMap.keySet();
					
					if(!keys.isEmpty()) {
						
						JSONObject resJSON = new JSONObject();
						jsonres = JSONResponse.getInstance();	
						
						resJSON = jsonres.successResponse(userMap, keys);
						
						response.getOutputStream().println(resJSON.toString(4));	
					}
					else {
						jsonres = JSONResponse.getInstance();
						
						//ENUM class
						int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
						String message = EnumError.SQLUSERNOTFOUND.getMessage();
						
						response.getOutputStream().println(jsonres.noUserToDisplay(errCode, message));
						response.setStatus(errCode);
					}
					
				}
				else {
					jsonres = JSONResponse.getInstance();
					message = EnumError.ERRORURL.getMessage();
					errCode = EnumError.ERRORURL.getErrCode();
					
					response.getOutputStream().println(jsonres.errorResponse(errCode, message).toString(4));
				}
			}
			catch(StringIndexOutOfBoundsException e1) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRORURL.getMessage();
				errCode = EnumError.ERRORURL.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			} 
			catch(SQLException e1) {
				jsonres = JSONResponse.getInstance();
				String message = EnumError.ERROROFFSET.getMessage();
				int errCode = EnumError.ERROROFFSET.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch (JSONException e1) {
				jsonres = JSONResponse.getInstance();
				String message = EnumError.ERRJSON.getMessage();
				int errCode = EnumError.ERRJSON.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
		} 
		catch (JSONException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.ERRJSON.getMessage();
			int errCode = EnumError.ERRJSON.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
		catch(SQLException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.SQLUSERNOTFOUND.getMessage();
			int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
		String reqBody = br.readLine();	
		String username = "";
		
		JSONObject json;
		try {
			//Regex Validation
			String requestUrl = request.getRequestURI();
			String URL = User.getBaseURL();
			String pattern = URL;
			boolean validURI = Pattern.matches(pattern, requestUrl);
			
			//Validate
			if(validURI) {
				json = new JSONObject(reqBody);
				username = json.get("name").toString();
			
				UserStore userstore = UserStore.getInstance();
				JSONObject resJSON = new JSONObject();
				
				if(username == "") {
					//ERROR Username not passed in request body
					jsonres = JSONResponse.getInstance();
					
					message = EnumError.USERNAMENOTENTERED.getMessage();
					errCode = EnumError.USERNAMENOTENTERED.getErrCode();
					
					resJSON = jsonres.errorResponse(errCode, message);
					
					response.getOutputStream().println(resJSON.toString(4));
				}
				else {
					User u = userstore.insertUser(username);
					jsonres = JSONResponse.getInstance();
					
					String message = EnumSuccess.SUCCESSFULLYADDED.getMessage();
					resJSON = jsonres.successResponse(u, message);
					
					response.getOutputStream().println(resJSON.toString(4));
				}
			}
			else {
				jsonres = JSONResponse.getInstance();
				
				String message = EnumError.ERRORURL.getMessage();
				int errCode = EnumError.ERRORURL.getErrCode();
				response.setStatus(errCode);
				
				response.getOutputStream().println(jsonres.errorResponse(errCode, message).toString(4));
			}
			
		} catch (JSONException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.ERRJSON.getMessage();
			int errCode = EnumError.ERRJSON.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
		catch(SQLIntegrityConstraintViolationException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.USERALREADYEXISTS.getMessage();
			int errCode = EnumError.USERALREADYEXISTS.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
		catch(StringIndexOutOfBoundsException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.ERRORURL.getMessage();
			int errCode = EnumError.ERRORURL.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
		catch(SQLException e) {
			jsonres = JSONResponse.getInstance();
			String message = EnumError.ERRORSQL.getMessage();
			int errCode = EnumError.ERRORSQL.getErrCode();
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestUrl = request.getRequestURI();
		
		//Regex Validation
		String URL = User.getBaseURL();
		String patternVisit = URL + "/[0-9]{5,}/visits";
		String patternUsername = URL + "/[0-9]{5,}/username";
		boolean validURIVisit = Pattern.matches(patternVisit, requestUrl);
		boolean validURIUsername = Pattern.matches(patternUsername, requestUrl);
				
		if(validURIVisit) {
			String arr[] = new String[100];
			arr = requestUrl.split("/");
			int userId = Integer.parseInt(arr[5]);
//			int id = Integer.parseInt(requestUrl.substring((URL + "/").length(), (URL + "/00000").length()));
			
			UserStore userstore = UserStore.getInstance();
			
			try {
				User user = userstore.incrementVisit(userId);
				 
				JSONObject resJSON = new JSONObject();
				jsonres = JSONResponse.getInstance();
				
				message = EnumSuccess.SUCCESSCOUNTINCREMENTED.getMessage();
				resJSON = jsonres.successResponse(user, message);
					
				response.getOutputStream().println(resJSON.toString(4));
			}
			catch(SQLException e) {
				jsonres = JSONResponse.getInstance();
				String message = EnumError.SQLUSERNOTFOUND.getMessage();
				int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch(StringIndexOutOfBoundsException e) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRORURL.getMessage();
				errCode = EnumError.ERRORURL.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, e.toString()));
			}
			catch(JSONException e) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRJSON.getMessage();
				errCode = EnumError.ERRJSON.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
		}
		else if(validURIUsername) {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String reqBody = br.lines().collect(Collectors.joining(System.lineSeparator()));
			
			try {
				JSONObject reqJSON = new JSONObject(reqBody);
				String updatedUsername = reqJSON.get("name").toString();
				
				String arr[] = new String[100];
				arr = requestUrl.split("/");
				int userId = Integer.parseInt(arr[5]);
//				int id = Integer.parseInt(requestUrl.substring((URL + "/").length(), (URL + "/00000").length()));
				
				UserStore userstore = UserStore.getInstance();
				
				//Check
				User u = userstore.getUser(userId);
				
				JSONObject resJSON = new JSONObject();
				jsonres = JSONResponse.getInstance();
				
				if(!(u.getUsername().equals(updatedUsername)))
				{
					try {
						u = userstore.updateUser(userId, updatedUsername);
						
						message = EnumSuccess.SUCCESSFULLYUPDATED.getMessage();
						resJSON = jsonres.successResponse(u, message);
				
					}
					catch(SQLIntegrityConstraintViolationException e) {
						jsonres = JSONResponse.getInstance();
						
						String message = EnumError.USERALREADYEXISTS.getMessage();
						int errCode = EnumError.USERALREADYEXISTS.getErrCode();
						
						resJSON = jsonres.errorResponse(errCode, message);
					}
				}
				else {
					String message = EnumError.USERNAMESAME.getMessage();
					int errCode = EnumError.USERNAMESAME.getErrCode();
					resJSON = jsonres.errorResponse(errCode, message);
				}
				response.getOutputStream().println(resJSON.toString(4));
			} 
			catch (JSONException e) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRJSON.getMessage();
				errCode = EnumError.ERRJSON.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch(SQLException e) {
				jsonres = JSONResponse.getInstance();
				String message = EnumError.SQLUSERNOTFOUND.getMessage();
				int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
		}
		else {
			jsonres = JSONResponse.getInstance();
			message = EnumError.ERRORURL.getMessage();
			errCode = EnumError.ERRORURL.getErrCode();
			
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
	}
	
	
	//DELETE
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String requestUrl = request.getRequestURI();
		//Regex Validation
		String URL = User.getBaseURL();
		
		String patternUser = URL + "/[0-9]{4,}";
		String patternVisit = URL + "/[0-9]{4,}/visits";
		
		boolean validURIUser = Pattern.matches(patternUser, requestUrl);
		boolean validURIVisit = Pattern.matches(patternVisit, requestUrl);
		
		if(validURIUser) {
			
			int userId = Integer.parseInt(requestUrl.substring((URL + "/").length()));
			
			JSONObject resJSON = new JSONObject();
			
			UserStore userstore = UserStore.getInstance();
			try {
				User u = userstore.getUser(userId);
				userId = userstore.deleteUser(userId);
				jsonres = JSONResponse.getInstance();
				String message = EnumSuccess.SUCCESSFULLYDELETED.getMessage();
				
				resJSON = jsonres.successResponse(u, message);
			}
			catch(SQLException e) 
			{
				jsonres = JSONResponse.getInstance();
				String message = EnumError.SQLUSERNOTFOUND.getMessage();
				int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch(JSONException e) 
			{
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRJSON.getMessage();
				errCode = EnumError.ERRJSON.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			finally {
				try {
					response.getOutputStream().println(resJSON.toString(4));
				}
				catch(JSONException e) {
					jsonres = JSONResponse.getInstance();
					message = EnumError.ERRORURL.getMessage();
					errCode = EnumError.ERRORURL.getErrCode();
					
					response.getOutputStream().println(jsonres.errorJSON(errCode, message));
				}
			}
		}
		else if(validURIVisit) {
			String arr[] = new String[100];
			arr = requestUrl.split("/");
			int userId = Integer.parseInt(arr[5]);
//			int id = Integer.parseInt(requestUrl.substring((URL + "/").length(), (URL + "/00000").length()));
			
			UserStore userstore = UserStore.getInstance();
			
			try {
				User user = userstore.decrementVisit(userId);
				
				JSONObject resJSON = new JSONObject();
				jsonres = JSONResponse.getInstance();
					
				String message = EnumSuccess.SUCCESSCOUNTDECREMENTED.getMessage();
				resJSON = jsonres.successResponse(user, message);
					
				response.getOutputStream().println(resJSON.toString(4));
			}
			catch(StringIndexOutOfBoundsException e) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRORURL.getMessage();
				errCode = EnumError.ERRORURL.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch(JSONException e) {
				jsonres = JSONResponse.getInstance();
				message = EnumError.ERRJSON.getMessage();
				errCode = EnumError.ERRJSON.getErrCode();
				
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
			catch(SQLException e) {
				jsonres = JSONResponse.getInstance();
				String message = EnumError.SQLUSERNOTFOUND.getMessage();
				int errCode = EnumError.SQLUSERNOTFOUND.getErrCode();
				response.getOutputStream().println(jsonres.errorJSON(errCode, message));
			}
		}
		else {
			jsonres = JSONResponse.getInstance();
			message = EnumError.ERRORURL.getMessage();
			errCode = EnumError.ERRORURL.getErrCode();
			
			response.getOutputStream().println(jsonres.errorJSON(errCode, message));
		}
	}

}