package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TC005_Delete_Employee_Record extends TestBase {

    @BeforeClass
    void deleteemployee() throws InterruptedException {

        logger.info("*********Started TC005_Delete_Employee_Record **********");

        baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = given();

        response = httpRequest.request(Method.GET, "/employees");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonpathevaluator = response.jsonPath();
        //Capture id
        String empId = jsonpathevaluator.get("[0].id");


        response = httpRequest.request(Method.DELETE, "/delete/" + empID); //Pass ID to delete record
        Thread.sleep(3000);

    }

    @Test
    void checkResposeBody() {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);

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
        logger.info("*********  Finished TC005_Delete_Employee_Record **********");
    }
}
