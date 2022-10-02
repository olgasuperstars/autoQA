package ru.geekbrains.auto.qa.autoqa.lesson4.controller;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private int id;
    private String firstName;
    private String secondName;
    private int age;
}
