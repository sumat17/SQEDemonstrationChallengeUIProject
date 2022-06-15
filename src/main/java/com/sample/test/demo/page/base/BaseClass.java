package com.sample.test.demo.page.base;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    public static final int WAIT_TIMEOUT = 3;
    public Logger log = Logger.getRootLogger();
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFromDropDown(WebElement element, String option) {
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        Select select = new Select(element);
        select.selectByValue(option);
    }

    public String selectedDropDownValue(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public String getTextFromElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}