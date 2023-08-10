package io.qameta.junit5;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.OrderPage;

public class E2EHomework {

    private LoginPage loginPage;
    private OrderPage orderPage;

    @BeforeEach
    public void setUp() {
        Selenide.open("http://51.250.6.164:3000/signin");
    }

    @BeforeEach
    public void setUpLoginPage() {
        loginPage = new LoginPage();
        loginPage.enterUsername("userqa01");
        loginPage.enterPassword("*YQ?T9*WrE");
    }

    @BeforeEach
    public void setUpOrderPage() {
        orderPage = new OrderPage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Step("Login and create an order")
    public void loginAndCreateOrder() {
        orderPage.clickSignInButtonAndNavigateToOrder();
        orderPage.enterUsername("New order");
        orderPage.enterPhoneNumber("+375298724269");
        orderPage.enterComment("Today");
        orderPage.clickCreateOrderButton();
        orderPage.waitForOrderCreationConfirmation();
    }

    @Step("Login and search for a non-existing order")
    public void loginAndSearchNonExistingOrder() {
        orderPage.clickStatusButtonToSearchOrder();
        orderPage.enterSearchOrderNumber("12345");
        orderPage.clickSearchOrderButton();
        orderPage.verifyOrderNotFoundError();
    }

    @Test
    void testLoginAndCreateOrder() {
        loginAndCreateOrder();
    }

    @Test
    void testLoginAndSearchNonExistingOrder() {
        loginAndSearchNonExistingOrder();
    }

    private class LoginPage {
        public void enterPassword(String password) {
        }

        public void enterUsername(String username) {
        }
    }
}
