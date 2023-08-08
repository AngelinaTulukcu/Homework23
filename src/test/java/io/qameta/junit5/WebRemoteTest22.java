package io.qameta.junit5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class WebRemoteTest22 {

    @BeforeAll
    public static void setUpAll(){

        Configuration.remote = "http://35.238.172.233:4444/wd/hub";
        Configuration.browser = "chrome"; // System.getProperty("browserName");
        Configuration.browserSize = "1920x1080";
        Configuration.browserVersion = "114.0.5735.90";

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
    }

    @BeforeEach
    public void setUp(){
        System.out.println("Trying to open browsers");
        Selenide.open( "http://51.250.6.164:3000" );
        System.out.println("browser Opened OK");
    }

    @AfterEach
    public void tearDown(){
        closeWebDriver();
    }

    //2.1 Negative test
    @Test
    public void incorrectLoginAndCheckPopup(){

        Selenide.$(By.id("username")).setValue("Incorrect Login");
        Selenide.sleep( 5500 );
        Selenide.$(By.id("password")).setValue("Incorrect Password");

        Selenide.$(By.xpath("//button[@data-name='signIn-button']")).click();

        Selenide.$(By.xpath("//div[@data-name='authorizationError-popup']")).shouldBe(Condition.exist, Condition.visible);
    }

    //2.1 Negative test #2
    @Test
    public void incorrectLoginAndCheckErorr(){

        Selenide.$(By.id("username")).setValue("userqa01");
        Selenide.sleep( 5500 );
        Selenide.$(By.id("password")).setValue("*YQ?T9*WrE");

        Selenide.$(By.xpath("//*[@data-name='password-input']/..//span[@data-name='username-input-error']")).shouldBe(Condition.exist, Condition.visible);

        Selenide.$(By.xpath("//button[@data-name='signIn-button']")).shouldBe(Condition.disabled);
    }

    //2.1  Negative test #3
    @Test
    public void incorrectLoginwithspaceAndCheckErorr(){

        Selenide.$(By.id("username")).setValue(" ");
        Selenide.sleep( 5500 );
        Selenide.$(By.id("password")).setValue("*YQ?T9*WrE");

        Selenide.$(By.xpath("//*[@data-name='username-input']/..//span[@data-name='username-input-error']")).shouldBe(Condition.exist, Condition.visible);

        Selenide.$(By.xpath("//*[@data-name='password-input']/..//span[@data-name='username-input-error']")).shouldBe(Condition.exist, Condition.visible);

        Selenide.$(By.xpath("//button[@data-name='signIn-button']")).shouldBe(Condition.disabled);
    }
}