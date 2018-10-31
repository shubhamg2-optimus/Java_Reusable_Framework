package com.retail.api.tests;

import javax.json.Json;
import javax.json.JsonObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.retail.webui.interfaces.Constants;

import io.qameta.allure.Description;

import com.retail.api.utils.RestTemplate;

public class DeleteApiTest {
    private String URL= Constants.CONFIG_API_URL
            .concat(Constants.USERS_ENDPOINT);
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    int userId ;
    
    @BeforeClass
    public void postUser() {
        
        JsonObject postObject = Json.createObjectBuilder()
                .add("name", "opt_sample2") 
                .add("job", "leader")
                .build();
        
        RestTemplate rest = new RestTemplate();
        rest.sendPostRequest(URL, postObject.toString());
        
        rest.getValidateResponse()
        .statusCode(201);        
        userId = rest.getResponseAsJsonObject().getInt("id");
        System.out.println(userId);
    }
    
    @Description("<b>Test Step</b>:Verify that for DELETE /users/{userId} request user get deleted and Response Code: 200 is received<br>"
			+ "<b>Expected Result</b>:<br>" + "<ul><li>User gets deleted and Status: 200 should be received</ul>")
	    
    @Test(description="Verify that for DELETE /users/{userId} request delets the user and Response Code: 200 is received")
    public void deleteUser() {
        
        RestTemplate rest = new RestTemplate();
        rest.sendDeleteRequest(URL, userId);       
        rest.getValidateResponse()
        .statusCode(204);
    }
}
