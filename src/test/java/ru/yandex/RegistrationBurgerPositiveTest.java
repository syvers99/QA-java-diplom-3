package ru.yandex;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import static ru.yandex.ConfigConst.*;

public class RegistrationBurgerPositiveTest {

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
        driver.get(BASE_URL);
        burgerPage = new HomePageBurger(driver);
        burgerPage.waitForLoadHomePage();
    }





    @Test
    @DisplayName("Зарегистрироваться через форму на странице регистрации")
    @Description("Вы можете зарегистрироваться через форму на странице регистрации" +
            " и потом успешно залогиниться")
    public void registerUserTest() {
        burgerPage.chooseAccount();
        burgerPage.waitForLoginVisibility();
        burgerPage.chooseRegistration();
        burgerPage.waitForRegistrationVisibility();
        burgerPage.registerAccount(user);
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
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

