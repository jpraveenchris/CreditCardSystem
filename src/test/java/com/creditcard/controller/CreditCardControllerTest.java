package com.creditcard.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditcard.model.CreditCard;

@SpringBootTest
public class CreditCardControllerTest
{

    @Test
    public void testValidCardNumber() {
        String cardNumber = "4636245622664436";
        CreditCard cc = new CreditCard("Christopher", cardNumber, "100", "50");
        assertTrue(cc.validate());
    }
    
    @Test
    public void testInValidCardNumber() {
        String wrongCardNumber = "4215643494165518";
        CreditCard cc2 = new CreditCard("Jason", wrongCardNumber, "100", "50");
        assertFalse(cc2.validate());
    }
}
