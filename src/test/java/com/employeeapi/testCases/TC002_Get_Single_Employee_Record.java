package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class TC002_Get_Single_Employee_Record extends TestBase {
    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*********Started TC002_Get_Single_Employee_Record **********");

        baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = given();
        response = httpRequest.request(Method.GET, "/employee/" + empID);

        Thread.sleep(7000);
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(empID));
    }

    @Test
    void checkStatusCode() {
        int statusCode = response.getStatusCode(); // Gettng status code
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkResponseTime() {
        long responseTime = response.getTime(); // Getting status Line
        Assert.assertTrue(responseTime < 6000);

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
    void checkContentLenght() {
        String contentLength = response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
    }

    @AfterClass
    void tearDown() {
        logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
    }


}
