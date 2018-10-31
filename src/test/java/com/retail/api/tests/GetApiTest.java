package com.retail.api.tests;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.retail.webui.interfaces.Constants;

import io.qameta.allure.Description;

import com.retail.api.utils.RestTemplate;

public class GetApiTest {
    private String URL= Constants.CONFIG_API_URL
            .concat(Constants.USERS_ENDPOINT).concat(Constants.CONFIG_USERID);
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    
    
    @Description("<b>Test Step</b>:Verify that for GET /users/{userId} request Response Code: 200 is received<br>"
			+ "<b>Expected Result</b>:<br>" + "<ul><li>Status: 200 with user details should be received</ul>")
	    
    @Test(description="Verify that for GET /users/{userId} request Response Code: 200 is received")
    public void getUser() {
        
        RestTemplate rest = new RestTemplate();
        rest.sendGetRequest(URL);   
        rest.getValidateResponse()
        .statusCode(200)
        .body("data.id", notNullValue())
        .body("data.first_name", anything())
        .body("data.last_name", anything())
        .body("data.avatar", anything());
    }
}
