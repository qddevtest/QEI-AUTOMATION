package com.qdbrowserstack;

import BaseDriver.DriverSetup;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class FlakyTest extends DriverSetup {

    @Test(invocationCount = 2)
    public void flakyTest(ITestContext testContext) throws Exception {
        int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
        System.out.println("Executing count: " + currentCount);
        driver.get("https://www.bstackdemo.com");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        // Check the title
        if(currentCount == 0) {
            Assert.assertTrue(driver.getTitle().matches("StackDemo"));
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");

        }else {
            Assert.assertTrue(driver.getTitle().matches("Failure"));
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
        }
    }
}
