package com.academy.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class OrderPage extends BasePage{

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    protected WebElement productDropDown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    protected WebElement quantityInput;

    @FindBy(xpath = "//input[@value='Calculate']")
    protected WebElement calculateBtn;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    protected WebElement customerNameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    protected WebElement streetAddressInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    protected WebElement cityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    protected WebElement stateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    protected WebElement zipInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    protected WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    protected WebElement exprDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    protected WebElement processBtn;

    @FindBy(tagName = "strong")
    protected  WebElement orderCreatedSuccessMsg;


    public void placeOrder(Map<String, String> data) {

        if (!data.get("run").equals("yes")) {
            return;
        }

        Select select = new Select(productDropDown);
        select.selectByVisibleText(data.get("product_name"));

        quantityInput.sendKeys(data.get("quantity"));
        calculateBtn.click();

        customerNameInput.sendKeys(data.get("customer_name"));
        streetAddressInput.sendKeys(data.get("street"));
        cityInput.sendKeys(data.get("city"));
        stateInput.sendKeys(data.get("state"));
        zipInput.sendKeys(data.get("zip"));

        WebElement card = driver.findElement(By.xpath("//input[@value='"+data.get("card")+"']"));
        card.click();

        cardNumberInput.sendKeys(data.get("card_number"));
        String exprDate = data.get("expr_date");
        String convertedDate = convertDate(exprDate);
        exprDateInput.sendKeys(convertedDate);

        processBtn.click();

        Assert.assertTrue(orderCreatedSuccessMsg.isDisplayed());
    }

    private String convertDate(String date){
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/yy");
        try {
            LocalDate inputDate = LocalDate.parse(date, inputFormat);
            String outputDateString = inputDate.format(outputFormat);
            return outputDateString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
