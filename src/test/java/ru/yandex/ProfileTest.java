package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static ru.yandex.ConfigConst.*;

public class ProfileTest {

    private WebDriver driver;
    private HomePageBurger burgerPage;
    Profile profile;
    User user;


    @Before
    public void startUp() {
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
    @DisplayName("Переход в Личный Кабинет после успешного логина")
    @Description("Вы можете попасть в личный кабинет после успешного логина  кликнув на" +
            " кнопку Личный кабинет на главной странице")
    public void checkProfileAfterLogin() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseProfile();
        burgerPage.waitForProfileVisibility();


    }

    @Test
    @DisplayName("Переход на главную страницу через логотип")
    @Description("Вы можете перейти из Личного кабинета на главную страницу, кликнув логотип")
    public void checkLogo() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseProfile();
        burgerPage.waitForProfileVisibility();
        burgerPage.chooseLogo();
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();

    }

    @Test
    @DisplayName("Переход на главную страницу через конструктор")
    @Description("Вы можете перейти на главную страницу из Личного кабинета, кликнув на пункт Конструктор")
    public void checkConstructor() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseProfile();
        burgerPage.waitForProfileVisibility();
        burgerPage.chooseConstructor();
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();

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
