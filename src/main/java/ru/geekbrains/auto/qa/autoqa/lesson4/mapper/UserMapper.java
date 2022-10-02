package ru.geekbrains.auto.qa.autoqa.lesson4.mapper;

import org.apache.catalina.User;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;

public interface UserMapper {

    UserEntity dtoToEntity(User userDto);

    User entityToDto(UserEntity userEntity);
}
