package com.academy.techcenture.tests.tests;

import com.academy.techcenture.base_test.BaseTest;
import com.academy.techcenture.pages.HomePage;
import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.OrderPage;
import com.academy.techcenture.utils.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;

public class WebOrderPlaceOrderTests extends BaseTest {


    @Test(dataProvider = "orders")
    public void placeOrderTest(Map<String,String> data){
        extentTest = reports.startTest("Web Orders Place Order Test");

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        loginPage.login("Tester", "test");
        extentTest.log(LogStatus.PASS, "User logged in successfully");

        homePage.navigateToOrderPage();
        extentTest.log(LogStatus.INFO, "User navigated to Order page successfully");

        orderPage.placeOrder(data);
        extentTest.log(LogStatus.PASS, "User placed ordered successfully");

        homePage.navigateToViewAllOrdersPage();
        extentTest.log(LogStatus.INFO, "User navigated to All orders page");
        homePage.logout();
        extentTest.log(LogStatus.INFO, "User successfully logged out");
    }


    @DataProvider(name = "orders")
    public Object[][] getData() {
        ExcelReader reader = new ExcelReader("src/main/resources/test_data/Test_Data.xlsx", "orders");
        return reader.getData();
    }

}
