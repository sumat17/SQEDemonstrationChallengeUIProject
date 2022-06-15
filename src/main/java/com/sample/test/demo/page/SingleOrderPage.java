package com.sample.test.demo.page;

import com.sample.test.demo.enumconstants.PaymentType;
import com.sample.test.demo.enumconstants.PizzaToppings;
import com.sample.test.demo.enumconstants.PizzaTypes;
import com.sample.test.demo.model.PizzaOrder;
import com.sample.test.demo.page.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleOrderPage extends BaseClass {
    public SingleOrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "pizza1Pizza")
    private WebElement pizza;

    @FindBy(xpath = "//select[@class='toppings1']")
    private WebElement pizzaToppingsMenu1;

    @FindBy(xpath = "//select[@class='toppings2']")
    private WebElement pizzaToppingsMenu2;

    @FindBy(id = "pizza1Qty")
    private WebElement pizzaQuantity;

    @FindBy(id = "pizza1Cost")
    private WebElement pizzaCost;

    @FindBy(id = "ccpayment")
    private WebElement cardPayment;

    @FindBy(id = "cashpayment")
    private WebElement cashPayment;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "placeOrder")
    private WebElement placeOrderButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    @FindBy(xpath = "//div/p")
    private WebElement dialog;

    @FindBy(xpath = "//div[@id='dialog']/p")
    private WebElement dialogText;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    private WebElement dialogCloseButton;

    public void choosePizza(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int quantity) {
        if (pizzaType != null) {
            selectFromDropDown(pizza, pizzaType.getDisplayName());
        }
        if (pizzaTopping1 != null) {
            selectFromDropDown(pizzaToppingsMenu1, pizzaTopping1.getDisplayName());
        }
        if (pizzaTopping2 != null) {
            selectFromDropDown(pizzaToppingsMenu2, pizzaTopping2.getDisplayName());
        }
        pizzaQuantity.clear();
        pizzaQuantity.sendKeys(String.valueOf(quantity));
    }

    public String getPizzaQuantity() {
        return pizzaQuantity.getAttribute("value");
    }

    public String getPizzaCost() {
        return pizzaCost.getAttribute("value");
    }

    public String getName() {
        return nameInput.getAttribute("value");
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    public void setPickupInformation(String name, String email, String phone) {
        if (name != null && phone != null) {
            nameInput.click();
            nameInput.sendKeys(name);

            emailInput.click();
            emailInput.sendKeys(email);

            phoneInput.click();
            phoneInput.sendKeys(phone);
        }
    }

    public void selectPayment(PaymentType paymentType) {
        if (paymentType == null) return;
        switch (paymentType) {
            case CARDPAYMENT:
                cardPayment.click();
                break;
            case CASHPAYMENT:
                cashPayment.click();
                break;
            default:
                log.info("Invalid payment method");
        }
    }

    public PaymentType getPaymentMethod() {
        if (String.valueOf(js.executeScript("return arguments[0].checked;", cardPayment)).equals("true")) {
            return PaymentType.CARDPAYMENT;
        } else if (String.valueOf(js.executeScript("return arguments[0].checked;", cashPayment)).equals("true")) {
            return PaymentType.CASHPAYMENT;
        } else {
            return PaymentType.INVALID;
        }
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

    public void resetOrder() {
        resetButton.click();
    }

    public String getDialogMessage() {
        return dialog.getText();
    }

    public void closeDialog() {
        dialogCloseButton.click();
    }

    public PizzaOrder getDefaultOrderInfo() {
        return new PizzaOrder(selectedDropDownValue(pizza), selectedDropDownValue(pizzaToppingsMenu1), selectedDropDownValue(pizzaToppingsMenu2),
                Integer.parseInt(getPizzaQuantity()), Double.parseDouble(getPizzaCost()), getName(), getEmail(), getPhone(), getPaymentMethod());
    }
}
