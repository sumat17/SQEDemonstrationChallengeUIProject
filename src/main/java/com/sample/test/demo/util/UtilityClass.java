package com.sample.test.demo.util;

import com.sample.test.demo.enumconstants.PaymentType;
import com.sample.test.demo.enumconstants.PizzaToppings;
import com.sample.test.demo.enumconstants.PizzaTypes;

public class UtilityClass {
    private static final String NAME_FIELD_REQUIRED = "Missing name";
    private static final String NAME_AND_PHONE_FIELDS_REQUIRED = "Missing name\nMissing phone numbers";
    private static final String NO_PIZZA_SELECTED = "Please, select pizza";
    private static final String MISSING_TOPPINGS_SELECTION = "Toppings for pizza not selected";
    private static final String NO_PAYMENT_SELECTED = "Please, select desired way of payment";
    private static final String INVALID_QUANTITY = "Invalid value for quantity";
    private static final String PHONE_FIELD_REQUIRED = "Missing phone number";

    public Object[][] getOrderTestData() {
        return new Object[][]{
                {PizzaTypes.SMALL_NOTOPPINGS, PizzaToppings.SALAMI, PizzaToppings.PROVOLNE, 2, "Suma", "test@gmail.com", "+1234567", PaymentType.CARDPAYMENT},
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, PizzaToppings.MUSHROOMS, 3, "Fin", "fin@yahoo.com", "+9876778854", PaymentType.CASHPAYMENT},
                {PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.ITALIANHAM, PizzaToppings.MOZZARELLA, 2, "Olga", "olga@gmail.com", "+375789000", PaymentType.CARDPAYMENT},
                {PizzaTypes.LARGE_NOTOPPINGS, PizzaToppings.ONIONS, PizzaToppings.OLIVES, 1, "Romana", "romana@gmail.com", "+345677852727", PaymentType.CASHPAYMENT},
                {PizzaTypes.LARGE_TWOTOPPINGS, PizzaToppings.MANGOS, PizzaToppings.PARMASAN, 1, "Ivan", "Ivan@outlook.com", "+895673884727", PaymentType.CARDPAYMENT}
        };
    }

    public Object[][] getNegativeOrderTestData() {
        return new Object[][]{
                //phone field left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, "John", null, null, PaymentType.CASHPAYMENT, PHONE_FIELD_REQUIRED},
                // negative number as quantity entered
                {PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.ITALIANHAM, PizzaToppings.MUSHROOMS, -2, "Olga", "olga@gmail.com", "+375298887727", PaymentType.CARDPAYMENT, INVALID_QUANTITY},
                //no payment selected
                {PizzaTypes.LARGE_NOTOPPINGS, null, null, 1, "Romana", "romana@gmail.com", "+375298887727", null, NO_PAYMENT_SELECTED},
                //no toppings selected for pizza with toppings
                {PizzaTypes.LARGE_TWOTOPPINGS, null, null, 1, "Ivan", "", "+375298887727", PaymentType.CASHPAYMENT, MISSING_TOPPINGS_SELECTION},
                {null, null, null, 2, "Suma", "Suma@gmail.com", "+12345677", PaymentType.CARDPAYMENT, NO_PIZZA_SELECTED},
                //name and phone fields left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, null, "", null, PaymentType.CASHPAYMENT, NAME_AND_PHONE_FIELDS_REQUIRED},
                // name field left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, null, "", "+9876563", PaymentType.CARDPAYMENT, NAME_FIELD_REQUIRED}
        };
    }

}
