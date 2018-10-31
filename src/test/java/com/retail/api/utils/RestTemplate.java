package com.retail.api.utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Reporter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static io.restassured.RestAssured.given;


/**
 * RESTAssured library is used for HHTP POST
 */
public class RestTemplate {
    protected Response response;  
    private String url = "";
    private String username;
    private String password;
    private boolean authenticationRequired = false;
    private ContentType contentType = ContentType.JSON;
    public static Logger log;

    public RestTemplate(String myUrl, String myUsername, String myPassword) {
        this.url = myUrl;
        Authentication(myUsername, myPassword);
    }

    public RestTemplate() {
    	
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        log = Logger.getLogger(RestTemplate.class);
        
    }

    public void authenticationRequired(boolean authRequired) {
    	
        this.authenticationRequired = authRequired;
    }

    public void Authentication(String myUserName, String myPassword) {
    	
        this.username = myUserName;
        this.password = myPassword;
    }

    /**
     * This is for Retrieves application trace Id for detail log
     *
     * @return
     */
    @SuppressWarnings("unused")
    private void setDetailLogTraceId() {
    	
        String traceId = response.getHeader("x-trace");
        if (Reporter.getCurrentTestResult() != null && traceId != null) {
            Reporter.getCurrentTestResult().setAttribute("applicationTraceId", traceId);
        }
    }
    
    
    public void resetURL(String myUrl) {
    	
        this.url = myUrl;
    }

    public void setContentType(ContentType contentType) {
    	
        this.contentType = contentType;
    }

    @SuppressWarnings("unused")
    private RequestSpecification authentication() {
    	
        if (authenticationRequired) {
            return given().auth().preemptive().basic(this.username, this.password).log().all();
        } else {
            return given().auth().none().log().all();
        }
    }

    @SuppressWarnings("unused")
    private void oauth2Token() {
    	
        if (authenticationRequired) {
        } else {
            given().auth().none().log().all();
        }

    }
    
    /**
     * Using Rest Assured to send GET request.
     *
     * @param endPoint
     * @return
     */
    @Step("Send GET request")
    public Response sendGetRequest(String endPoint) {

        response = authentication().get(this.url + endPoint);
        log.debug("********************* print out response info from RestAssured ********************* ");
        response.then().log().all();
        setDetailLogTraceId();
        return response;
    }

    /**
     * replace request template before sending request
     *
     * @param endPoint
     * @param parameter
     * @return
     */
    public Response sendGetRequest(String endPoint, String parameter) {
    	
        return sendGetRequest(endPoint + "/" + parameter);
    }

    /**
     * Using Rest Assured to send GET request with input url without authentication.
     *
     * @param myUrl
     * @return
     */
    public Response sendGetRequestWithUrl(String myUrl) {
    	
        response = authentication().log().all().get(myUrl);
        log.debug("********************* print out response info from RestAssured ********************* ");
        response.then().log().all();
        return response;
    }
    
    /**
     * Using Rest Assured to send GET request with input URL with authentication.
     *
     * @param myUrl
     * @param token
     * @return
     */
    public Response sendGetRequestWithUrl(String myUrl, String token) {
    	
        response = given().header("Authorization", token).log().all().get(myUrl);
        log.debug("********************* print out response info from RestAssured ********************* ");
        response.then().log().all();
        return response;
    }

    /**
     * Specify a header name and value before sending a GET request
     *
     * @param endPoint
     * @param headerName
     * @param headerValue
     * @return
     */
    public Response sendGetWithHeader(String endPoint, String headerName, String headerValue) {
    	
        response = authentication().header(headerName, headerValue).log().all().get(this.url + endPoint);
        log.debug("********************* RestAssured: GET request with header ********************* ");
        response.then().log().all();
        setDetailLogTraceId();
        return response;
    }

    /**
     * Send a request with headers and a parameter.
     *
     * @param endPoint
     * @param parameter
     * @param headerName
     * @param headerValue
     * @return
     */
    public Response sendGetWithHeaderAndParameter(String endPoint, String parameter, String headerName,
            String headerValue) {
    	
        return sendGetWithHeader(endPoint + "/" + parameter, headerName, headerValue);
    }
    
    /**
     * Send Post request
     *
     * @param endPoint
     * @param body
     * @return
     */
    @Step("Send POST request")
    public Response sendPostRequest(String endPoint, String body) {

        response = authentication().body(body).contentType(contentType).log().all().post(this.url + endPoint);
        log.debug("********************* log from RestAssured. ");
        setDetailLogTraceId();
        return response;
    }

    /**
     * Specify a header name and value with the post request
     *
     * @param endPoint
     * @param body
     * @param headerName
     * @param headerValue
     * @return
     */
    public Response sendPostWithHeader(String endPoint, String body, String headerName, String headerValue) {
    	
        response = authentication().body(body).contentType(contentType).header(headerName, headerValue).log().all()
                .post(this.url + endPoint);
        log.debug("********************* log from RestAssured. ");
        response.then().log().all();
        setDetailLogTraceId();
        return response;
    }

        
    /**
     * Send Post request
     *
     * @param endPoint
     * @param body
     * @param token
     * @return
     */
    public Response sendPostRequest(String endPoint, String body, String token) {

        response = given().header("Authorization", token).body(body).contentType(contentType).log().all().post(this.url + endPoint);
        log.debug("Logs using REST Assured");
        response.then().log().all();
        return response;
    }

    /**
     * Send PUT request
     * @param endPoint
     * @param body
     * @return
     */
    @Step("Send PUT request")
    public Response sendPutRequest(String endPoint, String body) {
    	
        response = authentication().body(body).contentType(contentType).log().all().put(this.url + endPoint);
        log.debug("********************* log from RestAssured. ");
        response.then().log().all();
        setDetailLogTraceId();
        return response;
    }
    
    /**
     * replace request template before sending request
     *
     * @param endPoint
     * @param parameter
     * @param body
     * @return
     */
    public Response sendPutRequest(String endPoint, String parameter, String body) {
    	
        return sendPutRequest((endPoint + "/" + parameter), body);
    }

    
    /**
     * Send Delete request.
     *
     * @param endPoint
     * @return
     */
    public Response sendDeleteRequest(String endPoint) {
    	
        response = authentication().log().all().delete(this.url + endPoint);
        log.debug("********************* print out response info from RestAssured ********************* ");
        response.then().log().all();
        setDetailLogTraceId();
        return response;
    }

    /**
     * Send Delete request.
     *
     * @param endPoint
     * @return
     */
    @Step("Send DELETE request")
    public Response sendDeleteRequest(String endPoint, int parameter) {
    	
        return sendDeleteRequest((endPoint + parameter));
    }
    
    @Step("Get response")
    public ValidatableResponse getValidateResponse() {
    	
        return this.response.then();
    }

    @SuppressWarnings("rawtypes")
    public ResponseBody getResponseBody() {
    	
        return this.response.body();
    }
    
    @Step("Get response as JSON object")
    public JSONObject getResponseAsJsonObject() {
    	
        return new JSONObject(this.response.getBody().asString());
    }

    public int getResponseCode() {
    	
        return this.response.statusCode();
    }
    
    public void resetAuthentication(String myUserName, String myPassword) {
    	
        this.username = myUserName;
        this.password = myPassword;
    }
}
