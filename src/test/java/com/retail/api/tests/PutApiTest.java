package com.retail.api.tests;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import javax.json.Json;
import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.retail.webui.interfaces.Constants;

import io.qameta.allure.Description;

import com.retail.api.utils.RestTemplate;

public class PutApiTest {
    private String URL= Constants.CONFIG_API_URL
            .concat(Constants.USERS_ENDPOINT).concat(Constants.CONFIG_USERID);
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    
        
    @Description("<b>Test Step</b>:Verify that for PUT /users/{userId} request updates user's details and Response Code: 200 is received<br>"
			+ "<b>Expected Result</b>:<br>" + "<ul><li>User details are updated and Status: 200 should be received</ul>")
	    
    @Test(description="Verify that for PUT /users/{userId} request updates user's details and Response Code: 200 is received")
    public void putUser() {
        
        JsonObject putObject = Json.createObjectBuilder()
                .add("name", "opt_sample_updated") 
                .add("job", "leader")
                .build();       
        RestTemplate rest = new RestTemplate();
        rest.sendPutRequest(URL, putObject.toString());        
        rest.getValidateResponse()
        .statusCode(200)
        .body("name", equalToIgnoringCase("opt_sample_updated"))
        .body("job", equalToIgnoringCase("leader"))
        .body("updatedAt", notNullValue());
    }
}
