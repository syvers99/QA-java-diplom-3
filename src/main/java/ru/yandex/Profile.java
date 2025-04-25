package ru.yandex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class Profile {
    private String name;
    private String email;
    private String password;

    public Profile() {
        password = UUID.randomUUID().toString();
        email = UUID.randomUUID() + "@yandex.ru";
        name = UUID.randomUUID().toString();
    }

}