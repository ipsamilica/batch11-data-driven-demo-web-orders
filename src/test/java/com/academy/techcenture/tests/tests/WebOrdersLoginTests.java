package com.academy.techcenture.tests.tests;

import com.academy.techcenture.base_test.BaseTest;
import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class WebOrdersLoginTests extends BaseTest {

    @Test
    public void webOrdersLoginPositiveTest(){
        extentTest = reports.startTest("Web Orders Login Test Positive");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        extentTest.log(LogStatus.PASS, "User logged in successfully");
    }

    @Test
    public void webOrdersLoginNegativeTest(){
        extentTest = reports.startTest("Web Orders Login Test Negative");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Incorrect", "WrongPass!");
        loginPage.verifyLoginError();
        extentTest.log(LogStatus.PASS, "User verified login error successfully");
    }
}