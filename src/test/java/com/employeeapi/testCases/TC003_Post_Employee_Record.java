package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TC003_Post_Employee_Record extends TestBase {
    String empName = RestUtils.empName();
    String empAge = RestUtils.empAge();
    String empSal = RestUtils.empSal();


    @BeforeClass
    void createEmployee() throws InterruptedException {
        logger.info("*********Started TC003_Post_Employee_Record **********");
        baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName); // Cast
        requestParams.put("salary", empSal);
        requestParams.put("age", empAge);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.POST, "/create");
        Thread.sleep(5000);
    }

    @Test
    void checkResposeBody() {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empName), true);
        Assert.assertEquals(responseBody.contains(empSal), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
    }

    @Test
    void checkStatusCode() {
        int statusCode = response.getStatusCode(); // Gettng status code
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkstatusLine() {
        String statusLine = response.getStatusLine(); // Gettng status Line
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    void checkContentType() {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }

    @Test
    void checkserverType() {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.14.1");
    }

    @Test
    void checkcontentEncoding() {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, "gzip");

    }

    @AfterClass
    void tearDown() {
        logger.info("*********  Finished TC003_Post_Employee_Record **********");
    }


}
