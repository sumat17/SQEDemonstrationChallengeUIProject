package com.sample.test.demo.tests.page;

import com.sample.test.demo.enumconstants.PaymentType;
import com.sample.test.demo.enumconstants.PizzaToppings;
import com.sample.test.demo.enumconstants.PizzaTypes;
import com.sample.test.demo.page.SingleOrderPage;
import com.sample.test.demo.tests.base.BaseTest;
import com.sample.test.demo.util.UtilityClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SingleOrderPageNegativeTest extends BaseTest {
    @Test(dataProvider = "negativeOrderData", groups = {"smoke_test"})
    public void placeOrderInvalidDataTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentType paymentType, String expectedErrorMsg) {
        orderPage = new SingleOrderPage(driver);
        orderPage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        orderPage.setPickupInformation(name, email, phone);
        orderPage.selectPayment(paymentType);
        orderPage.placeOrder();
        Assert.assertNotEquals(orderPage.getDialogMessage(), expectedErrorMsg);
    }

    @DataProvider
    public Object[][] negativeOrderData() {
        utilityClass = new UtilityClass();
        return utilityClass.getNegativeOrderTestData();
    }

    @AfterMethod
    public void setDown() {
        orderPage.closeDialog();
    }
}
