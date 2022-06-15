package com.sample.test.demo.tests.page;

import com.sample.test.demo.enumconstants.PaymentType;
import com.sample.test.demo.enumconstants.PizzaToppings;
import com.sample.test.demo.enumconstants.PizzaTypes;
import com.sample.test.demo.model.PizzaOrder;
import com.sample.test.demo.page.SingleOrderPage;
import com.sample.test.demo.tests.base.BaseTest;
import com.sample.test.demo.util.UtilityClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SingleOrderPageTest extends BaseTest {
    @Test(dataProvider = "getOrderTestData", groups = {"smoke_test"})
    public void placeOrderTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentType paymentType) {
        String actual = "";
        double expectedCost = pizzaQuantity * pizzaType.getCost();
        orderPage = new SingleOrderPage(driver);
        orderPage.resetOrder();
        orderPage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        orderPage.setPickupInformation(name, email, phone);
        orderPage.selectPayment(paymentType);
        orderPage.placeOrder();
        actual = orderPage.getDialogMessage();
        orderPage.closeDialog();
        Assert.assertEquals(actual, String.format("Thank you for your order! TOTAL: %s %s", expectedCost, pizzaType.getDisplayName()));
    }

    @Test(dataProvider = "getOrderTestData")
    public void placeOrderResetTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentType paymentType) {
        orderPage = new SingleOrderPage(driver);
        orderPage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        orderPage.setPickupInformation(name, email, phone);
        orderPage.selectPayment(paymentType);
        orderPage.resetOrder();
        PizzaOrder resetOrder = new PizzaOrder(PizzaTypes.CHOOSE_PIZZA.getDisplayName(), pizzaTopping1.getDisplayName(), pizzaTopping2.getDisplayName(),
                0, 0.0, "", "", "", PaymentType.INVALID);
        log.info(resetOrder.toString());
        Assert.assertEquals(orderPage.getDefaultOrderInfo(), resetOrder);
    }

    @DataProvider
    public Object[][] getOrderTestData() {
        utilityClass = new UtilityClass();
        return utilityClass.getOrderTestData();
    }
}
