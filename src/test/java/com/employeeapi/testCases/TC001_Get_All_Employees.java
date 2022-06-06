package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TC001_Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        logger.info("------------Started TC001_Get_All_Employees-----------");
        baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = given();
        response = httpRequest.request(Method.GET, "/employees");
        Thread.sleep(3000);
    }


    @Test
    void checkResponseBody() {
        logger.info("----validating response body------");
        String responseBody = response.getBody().asString();
        logger.info("Response Body=====>" + responseBody);
        Assert.assertTrue(responseBody != null);

    }


    @Test
    void checkStatusCode() {
        logger.info("----validating status code------");
        int statusCode = response.getStatusCode();
        logger.info("Status Code===>" + statusCode);
        Assert.assertEquals(statusCode, "200");
    }

    @Test
    void checkResponseTime() {
        logger.info("---------validating Response Time--------");
        long responseTime = response.getTime();
        logger.info("Response time is==" + responseTime);
        if (responseTime > 10000)
            logger.warn("Response Time is greater than 2000");
        Assert.assertTrue(responseTime < 10000);
    }


    @Test
    void checkStatusLine() {
        logger.info("------validating status line------");
        String statusLine = response.getStatusLine();
        logger.info("Status Line is ==>" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    void checkContentType() {
        logger.info("---------validating Content Type--------");
        String contentType = response.header("Content-Type");
        logger.info("Content-Type is==" + contentType);
        Assert.assertEquals(contentType, "application/json");


    }

    @Test
    void checkserverType() {
        logger.info("***********  Checking Server Type **********");

        String serverType = response.header("Server");
        logger.info("Server Type is =>" + serverType);
        Assert.assertEquals(serverType, "nginx");

    }

    @Test
    void checkcontentEncoding() {
        logger.info("***********  Checking Content Encoding**********");

        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding is==>" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");


    }

    @Test
    void checkContentLength() {
        logger.info("******validating content Length");
        String contentLength = response.header("Content-Length");
        logger.info("content Length is " + contentLength);
        if (Integer.parseInt(contentLength) < 100)
            logger.warn("Content Length is less than 100");

        Assert.assertTrue(Integer.parseInt(contentLength) > 100);
    }

    @AfterClass
    void tearDown() {
        logger.info("*********  Finished TC001_Get_All_Employees **********");
    }
}
