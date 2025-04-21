package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static ru.yandex.ConfigConst.*;

public class BurgerTest {

    private WebDriver driver;
    private HomePageBurger burgerPage;
    Profile profile;
    User user;


    @Before
    public void startUp() {
            //выбор браузера
        DriverRule factory = new DriverRule();
        driver = factory.getDriver();
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        user.createUser();
        driver.get(BASE_URL);
        burgerPage = new HomePageBurger(driver);
        burgerPage.waitForLoadHomePage();
    }



    @Test
    @DisplayName("Выбрать булочку для бургера")
    @Description("Вы можете выбрать булочку для бургера в разделе Булки")
    public void checkBun() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseFilling();
        burgerPage.chooseBun();
        burgerPage.waitForBunAndClick();
        burgerPage.checkBun();

    }
    @Test
    @DisplayName("Выбрать начинку")
    @Description("Вы можете выбрать начинку для бургера в разделе Начинки")
    public void checkFilling() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseFilling();
        burgerPage.waitForFillingAndClick();
        burgerPage.checkFilling();

    }

    @Test
    @DisplayName("Выбрать соус")
    @Description("Вы можете выбрать соус для бургера в раздела Соусы")
    public void checkSauce() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseFilling();
        burgerPage.chooseSauce();
        burgerPage.waitForSauceAndClick();
        burgerPage.checkSauce();

    }

    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
        String userToken = user.loginUser();
        if (!userToken.equals(FAILED)) {
            user.deleteUser(userToken);
        }
    }

}