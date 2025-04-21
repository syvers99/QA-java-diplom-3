package ru.yandex;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static ru.yandex.ConfigConst.*;


// Класс главной страницы
public class HomePageBurger {

    private final WebDriver driver;
    private final String pathProfile = "/account";
    private final String pathRegister = "/register";
    private final String pathLogin = "/login";
    private final String pathForgot = "/forgot-password";
    private final String refProfile = "/account/profile";
    private final String classPassword = "password";
    private final By homePage = By.xpath(".//*[text() = 'Соберите бургер']");
    private final By accountButton = By.xpath(String.format(".//button[text()='%s']", "Войти в аккаунт"));
    private final By profileButton = By.xpath(String.format(".//a[@href='%s']", pathProfile));
    private final By loginButton = By.xpath(String.format(".//button[text()='%s']", "Войти"));
    private final By emailForm = By.xpath(String.format(".//input[@value='%s']", ""));
    private final By passwordForm = By.xpath(String.format(".//input[@type='%s']", classPassword));
    private final By nameForm = By.xpath(String.format(".//input[@value='%s']", ""));
    private final By registrationButton = By.xpath(String.format(".//a[@href='%s']", pathRegister));
    private final By logonButton = By.xpath(String.format(".//a[@href='%s']", pathLogin));
    private final By resetButton = By.xpath(String.format(".//a[@href='%s']", pathForgot));
    private final By resetPasswordEntry = By.xpath(String.format(".//button[text()='%s']", "Восстановить"));
    private final By registerButton = By.xpath(String.format(".//button[text()='%s']", "Зарегистрироваться"));
    private final By makeOrderButton = By.xpath(String.format(".//button[text()='%s']", "Оформить заказ"));
    private final By saveButton = By.xpath(String.format(".//button[text()='%s']", "Сохранить"));
    private final By badPassword = By.xpath(".//p[@class = 'input__error text_type_main-default']");
    private final By profileBlock = By.xpath(String.format(".//a[@href='%s']", refProfile));
    private final By constructor = By.xpath(".//*[text() = 'Конструктор']");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logOutButton = By.xpath(String.format(".//button[text()='%s']", "Выход"));
    private final By filling = By.xpath(".//*[text() = 'Начинки']");
    private final By details = By.xpath(".//*[text() = 'Детали ингредиента']");
    private final By fillingElm = By.xpath(".//*[text() = 'Мясо бессмертных моллюсков Protostomia']");
    private final By sauce = By.xpath(".//*[text() ='Соусы']");
    private final By sauceElm = By.xpath(".//*[text() = 'Соус Spicy-X']");
    private final By bun = By.xpath(".//*[text() ='Булки']");
    private final By bunElm = By.xpath(".//*[text() = 'Флюоресцентная булка R2-D3']");


    // конструктор класса
    public HomePageBurger(WebDriver driver) {
        this.driver = driver;

    }


    @Step("ждем загрузки домашней страницы")
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(homePage));
    }


    @Step("кликнуть  по кнопке Войти в аккаунт")
    public void chooseAccount() {
       try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }


    @Step("ждем загрузки страницы с формой для логина")
    public void waitForLoginVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

    }

    @Step("кликнуть кнопку Личный кабинет")
    public void chooseProfile() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    @Step("заполнить форму для входа в приложение и залогиниться")
    public void loginAccount(User user) {
        setCreds(user);
        driver.findElement(loginButton).click();

    }


    @Step("ввести в форму email и пароль")
    public void setCreds(User user) {
        driver.findElement(emailForm).sendKeys(user.getEmail());
        driver.findElement(passwordForm).sendKeys(user.getPassword());

    }

    @Step("кликнуть по ссылке Вы — новый пользователь? Зарегистрироваться")
    public void chooseRegistration() {
        driver.findElement(registrationButton).click();

    }

    @Step("ждем загрузки экрана с формой для регистрации")
    public void waitForRegistrationVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));

    }

    @Step("кликнуть по ссылке Войти (Уже зарегистрированы?/Вспомнили пароль)")
    public void chooseLogon() {
        driver.findElement(logonButton).click();
    }

    @Step("кликнуть ссылку Восстановить пароль")
    public void chooseResetPassword() {
        driver.findElement(resetButton).click();
    }

    @Step("ждем загрузки экрана с формой для восстановления пароля")
    public void waitForResetVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordEntry));

    }

    @Step("кликнуть  по кнопке Зарегистрироваться")
    public void registerAccount(User user) {
        setProfile(user);
        driver.findElement(registerButton).click();
    }

    @Step("заполнить форму регистрации")
    public void setProfile(User user) {
        driver.findElement(nameForm).sendKeys(user.getName());
        driver.findElement(emailForm).sendKeys(user.getEmail());
        driver.findElement(passwordForm).sendKeys(user.getPassword());
    }

    @Step("ждем загрузки экрана с ошибкой Некорректный пароль")
    public void waitForBadVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(badPassword));
        String result = driver.findElement(badPassword).getText();
        MatcherAssert.assertThat(result, containsString(FAILED_REGISTRATION));

    }

    @Step("проверить наличие кнопки Оформить заказ")
    public void waitForMakeOrderVisibilityAndCheckButton() {
        // ждем видимости элемента с нужным текстом из параметра в течение 8 секунд
        String result = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton)).getText();
        MatcherAssert.assertThat(result, containsString(MAKE_ORDER));

    }
    @Step("ждем загрузки экрана с формой для изменения  данных пользователя")
    public void waitForProfileVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        String result = driver.findElement(profileBlock).getText();
        MatcherAssert.assertThat(result, containsString(PROFILE));
    }
    @Step("кликнуть  по пункту меню Конструктор")
    public void chooseConstructor() {
        driver.findElement(constructor).click();
    }
    @Step("кликнуть  по пункту меню Логотипу")
    public void chooseLogo() {
        driver.findElement(logoButton).click();
    }
    @Step("кликнуть  по кнопке Выход")
    public void chooseLogout() {
        driver.findElement(logOutButton).click();
    }

    @Step("кликнуть  по ингредиенту в меню Начинок")
    public void waitForFillingAndClick() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingElm)).click();
    }
    @Step("кликнуть  по пункту меню Начинки")
    public void chooseFilling() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(filling)).click();

    }
    @Step("проверить карточку ингредиента меню Начинки")
    public void checkFilling() {
        String result = driver.findElement(details).getText();
        MatcherAssert.assertThat(result, containsString(DETAILS));
    }

    @Step("кликнуть  по ингредиенту в меню Соусы")
    public void waitForSauceAndClick() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceElm)).click();
    }
    @Step("кликнуть  по пункту меню Соусы")
    public void chooseSauce() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(sauce)).click();
    }
    @Step("проверить карточку ингредиента в меню Соусы")
    public void checkSauce() {
        String result = driver.findElement(details).getText();
        MatcherAssert.assertThat(result, containsString(DETAILS));
    }
    @Step("кликнуть  по пункту меню Булки")
    public void chooseBun() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(bun)).click();
    }
    @Step("проверить карточку ингредиента в меню Булки")
    public void checkBun() {
        String result = driver.findElement(details).getText();
        MatcherAssert.assertThat(result, containsString(DETAILS));
    }

    @Step("кликнуть  по ингредиенту в меню Булки")
    public void waitForBunAndClick() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(bunElm)).click();
    }

}



