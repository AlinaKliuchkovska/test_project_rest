package utils;

import controller.PlayerController;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import static utils.PropertiesReader.getBaseURI;

public class BaseTest {
    public PlayerController playerController;
    public SoftAssert softAssert;

    @BeforeSuite
    public void testSettings(){
        RestAssured.baseURI = getBaseURI();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }

    @BeforeClass
    public void before(){
        playerController = new PlayerController();
        softAssert = new SoftAssert();
    }
}
