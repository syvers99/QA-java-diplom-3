package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static ru.yandex.ConfigConst.*;

public class RegistrationBurgerNegativeTest {

    private WebDriver driver;
    private HomePageBurger burgerPage;
    Profile profile;
    User user;


    @Before
    public void startUp() {
        //выбор браузера
        DriverRule factory = new DriverRule();
        driver = factory.getDriver();

        driver.get(BASE_URL);

        burgerPage = new HomePageBurger(driver);

        burgerPage.waitForLoadHomePage();
    }



    @Test
    @DisplayName("Нельзя зарегистрироваться с невалидным паролем")
    @Description("Если вы регистрируетесь с паролем , который не соответствует " +
            "требованиям появится ошибка Некорректный пароль")
    public void registerUserNegativeTest() {
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(),PASSWORD_BAD);
        burgerPage.chooseAccount();
        burgerPage.waitForLoginVisibility();
        burgerPage.chooseRegistration();
        burgerPage.waitForRegistrationVisibility();
        burgerPage.registerAccount(user);
        burgerPage.waitForBadVisibility();
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
