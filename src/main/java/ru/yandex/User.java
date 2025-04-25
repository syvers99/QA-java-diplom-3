package ru.yandex;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;

import java.net.HttpURLConnection;
import static org.hamcrest.Matchers.*;
import static ru.yandex.ConfigConst.*;

@Getter
@Setter
public class User extends Client {
    private String name;
    private String email;
    private String password;
    private Profile profile;
    private Creds creds;

    public User(String firstName, String email, String password) {
        this.name = firstName;
        this.email = email;
        this.password = password;
        profile = new Profile(firstName, email, password);
        creds = new Creds(email, password);

    }


    @Step("Создать пользователя")
    public String createUser() {
        try {
            return
                    create(profile)
                            .extract()
                            .path("accessToken").toString();
        } catch (NullPointerException e) {
            return FAILED;
        }

    }

    @Step("Залогинить пользователя")
    public String loginUser() {
        try {
            return
                    login(creds)
                            .extract()
                            .path("accessToken").toString();
        } catch (NullPointerException e) {
            return FAILED;
        }
    }

    @Step("Удалить пользователя")
    public void deleteUser(String userToken) {

        spec()
                .header("Authorization", userToken)
                .log().all()
                .when()
                .delete(USER_PATH)
                .then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .assertThat().body("success", is(true));

    }



    public ValidatableResponse login(Creds creds) {
        return spec()
                .body(creds)
                .log().all()
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }

    public ValidatableResponse create(Profile profile) {
        return spec()
                .body(profile)
                .log().all()
                .when()
                .post(REGISTER_PATH)
                .then().log().all();
    }



}







