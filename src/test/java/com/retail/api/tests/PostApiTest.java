package com.retail.api.tests;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import javax.json.Json;
import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.retail.webui.interfaces.Constants;

import io.qameta.allure.Description;

import com.retail.api.utils.RestTemplate;

public class PostApiTest {
    private String URL= Constants.CONFIG_API_URL
            .concat(Constants.USERS_ENDPOINT);
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    
    @Description("<b>Test Step</b>:Verify that for POST /users request Response Code: 201 created is received<br>"
			+ "<b>Expected Result</b>:<br>" + "<ul><li>Status: 201 Created should be received</ul>")
	    
    @Test(description="Verify that for POST /users request Response Code: 201 created is received")
    public void postUser() {
        
        JsonObject postObject = Json.createObjectBuilder()
                .add("name", "opt_sample") 
                .add("job", "leader")
                .build();     
        RestTemplate rest = new RestTemplate();
        rest.sendPostRequest(URL, postObject.toString());        
        rest.getValidateResponse()
        .statusCode(201)
        .body("name", equalToIgnoringCase("opt_sample"))
        .body("job", equalToIgnoringCase("leader"))
        .body("id", notNullValue())
        .body("createdAt", notNullValue());
    }
}
