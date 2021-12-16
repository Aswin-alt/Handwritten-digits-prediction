package com.aswin.dao;

import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aswin.model.User;

public class JSONResponse {
	
	private static JSONResponse jsonres;
	
	private JSONResponse(){
		System.out.println("JSONResponse Constructor");
	}
	
	//Singleton
	public static JSONResponse getInstance() {
		if(jsonres == null) {
			synchronized(JSONResponse.class) {
				if(jsonres == null) {
					jsonres = new JSONResponse();
				}
			}
		}
		return jsonres;
	}
	
	
	public JSONObject successResponse(User u, String message) throws JSONException {
		JSONObject resJSON = new JSONObject();
		
		resJSON.put("Success", true);
		resJSON.put("Message", message);
		
		JSONObject userJSON = new JSONObject();
		userJSON.put("UserId", u.getUserId());
		userJSON.put("Name", u.getUsername());
		userJSON.put("Visits", u.getVisit());
		
		resJSON.put("Data", userJSON);
		
		return resJSON;
	}
	
	//Get all users
	public JSONObject successResponse(Map<Integer, User> userMap, Set<Integer> keys) throws JSONException {
		
		JSONArray usersArray = new JSONArray();
		for(Integer key : keys) {
			User u = userMap.get(key);
			
			JSONObject userJSON = new JSONObject();

			userJSON.put("userId", u.getUserId());
			userJSON.put("Name", u.getUsername());
			userJSON.put("Visits", u.getVisit());
			
			
			usersArray.put(userJSON);
		}
			
			
		JSONObject resJSON = new JSONObject();
		resJSON.put("Success", true);
		resJSON.put("Data", usersArray);
		
		return resJSON;
	}
	
	
	public JSONObject errorResponse(int errCode, String message) throws JSONException {
		JSONObject resJSON = new JSONObject();
		
		resJSON.put("Success", false);
		resJSON.put("Error_Code", errCode);
		resJSON.put("Message", message);
		
		return resJSON;
	} 
	
	
	public String errorJSON(int errCode, String message) {
		return "{\n\t\"Error Code\" : " + errCode + "\n\t\"Message\" : " + message + "\n\t\"Success\" : false\n}";
	}
	
	public String noUserToDisplay(int errCode, String message) {
		System.out.println("No user");
		return "{\n\"Data\" : [],\n\"Message\" : " + message + "\n\"Status\" : " + "Success" + "\n}";
	}

}
