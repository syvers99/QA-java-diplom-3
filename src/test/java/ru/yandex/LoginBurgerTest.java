package ru.yandex;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import static ru.yandex.ConfigConst.*;

public class LoginBurgerTest {

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
    @DisplayName("Залогиниться через кнопку Войти в аккаунт")
    @Description("Вы можете перейти в форму для логина кликнув на кнопку" +
            "Войти в аккаунт на Главной странице и залогиниться")
    public void checkFromAccount()
    {
        burgerPage.chooseAccount();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
    }

    @Test
    @DisplayName("Залогиниться через кнопку Личный кабинет")
    @Description("Вы можете перейти в форму для логина кликнув на кнопку Личный Кабинет" +
            " на Главной странице и залогиниться")
    public void checkFromProfile() {
        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
    }

    @Test
    @DisplayName("Залогиниться  через кнопку  Войти (Регистрация)")
    @Description("Вы можете перейти в форму для  логина кликнув на кнопку Войти на странице регистрации")
    public void checkFromRegistration() {
        burgerPage.chooseAccount();
        burgerPage.waitForLoginVisibility();
        burgerPage.chooseRegistration();
        burgerPage.waitForRegistrationVisibility();
        burgerPage.chooseLogon();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
    }

    @Test
    @DisplayName("Залогиниться через кнопку Войти (Восстановление пароля)")
    @Description("Вы можете перейти в форму для логина кликнув на кнопку Войти на странице восстановления пароля")
    public void checkFromReset() {
        burgerPage.chooseAccount();
        burgerPage.waitForLoginVisibility();
        burgerPage.chooseResetPassword();
        burgerPage.waitForResetVisibility();
        burgerPage.chooseLogon();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
    }

    @Test
    @DisplayName("Разлогиниться через кнопку Выход ")
    @Description("Вы можете разлогиниться в Личном кабинете через кнопку Выход")
    public void checkLogout() {

        burgerPage.chooseProfile();
        burgerPage.waitForLoginVisibility();
        burgerPage.loginAccount(user);
        burgerPage.waitForMakeOrderVisibilityAndCheckButton();
        burgerPage.chooseProfile();
        burgerPage.waitForProfileVisibility();
        burgerPage.chooseLogout();
        burgerPage.waitForLoginVisibility();


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
