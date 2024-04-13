package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(linkText = "View all orders")
    protected WebElement viewAllOrdersLink;

    @FindBy(linkText = "View all products")
    protected WebElement viewAllProductsLink;

    @FindBy(linkText = "Order")
    protected WebElement orderLink;

    @FindBy(tagName = "h1")
    protected WebElement logo;

    @FindBy(linkText = "Logout")
    protected WebElement logoutLink;

    public void logout(){
        Assert.assertTrue(logoutLink.isDisplayed(), "logout link is not displayed");
        logoutLink.click();
    }

    public void navigateToViewAllOrdersPage(){
        viewAllOrdersLink.click();
    }

    public void navigateToViewAllProductsPage(){
        viewAllProductsLink.click();
    }

    public void navigateToOrderPage(){
        orderLink.click();
    }


}
