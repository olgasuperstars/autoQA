package ru.geekbrains.auto.qa.autoqa.lesson4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByFirstNameAndSecondName(String firstName, String secondName);

    Optional<UserEntity> findByAddressEntityCity(String city);


}
