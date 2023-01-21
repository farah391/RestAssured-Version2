package com.noorteck.qa.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.noorteck.qa.utils.Constants;
import com.noorteck.qa.utils.DBUtils;

import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;


public class JobApi {
	public static void jobFieldLevelValidation(DataTable dataTable) {
		switch (Constants.apiRequestConfigTestDataMap.get("apiName")) {
		
		
		case "PostNewJob":
			EmployeeAPI.verifyResponseField(dataTable);

			break;
		}
	}
	

public static void jobDBLevelValidation() {
	switch (Constants.apiRequestConfigTestDataMap.get("apiName")) {
	case "PostNewJob":
		
		JsonPath jsonPath = Constants.response.jsonPath();
		Constants.requestBodyMap.put("jobId",jsonPath.getInt("jobId"));		
		verifyAgainstDB();
		break;
		
	}
		
	}




public static void verifyAgainstDB() {
	List<LinkedHashMap<String, String>> expResultMaps = new ArrayList<>();

	String query = "SELECT * FROM hr_scrum.jobss WHERE job_id = " + Constants.requestBodyMap.get("jobId") + ";";			

	
	// connect to database
	expResultMaps = DBUtils.makeDBRequest(query);
	for (LinkedHashMap<String, String> expResultMap : expResultMaps) {

		JobApi.compareTwoValues(Constants.requestBodyMap.get("jobId"), expResultMap.get("job_id"));
		JobApi.compareTwoValues(Constants.requestBodyMap.get("jobTitle"), expResultMap.get("job_title"));
		JobApi.compareTwoValues(Constants.requestBodyMap.get("minSalary"), expResultMap.get("min_salary"));
		JobApi.compareTwoValues(Constants.requestBodyMap.get("maxSalary"), expResultMap.get("max_salary"));
	}    
}


private static void compareTwoValues(Object object, String string) {
	// TODO Auto-generated method stub
	
}
}
