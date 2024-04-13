package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Base64;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "ctl00_MainContent_username")
    protected WebElement usernameInput;

    @FindBy(id = "ctl00_MainContent_password")
    protected WebElement passwordInput;

    @FindBy(id = "ctl00_MainContent_login_button")
    protected WebElement loginBt;

    @FindBy(id = "ctl00_MainContent_status")
    protected WebElement error;

    public void login(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        Assert.assertTrue(loginBt.isEnabled(), "Login button is not enabled");
        loginBt.click();
    }

    public void verifyLoginError(){
        Assert.assertTrue(error.isDisplayed(), "Error was not displayed");
    }

}


