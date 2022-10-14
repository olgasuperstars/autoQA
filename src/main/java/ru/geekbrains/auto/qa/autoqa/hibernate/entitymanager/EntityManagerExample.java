package ru.geekbrains.auto.qa.autoqa.hibernate.entitymanager;

import lombok.extern.slf4j.Slf4j;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class EntityManagerExample {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("qa-auto-test");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);
        userEntity.setFirstName("Alex");
        userEntity.setSecondName("Brown");

        em.persist(userEntity);
        em.getTransaction().commit();
        em.close();

        EntityManager em2 = emf.createEntityManager();

        List<UserEntity> userEntityList = em2.createQuery("select u from UserEntity u").getResultList();

        userEntityList.forEach(u -> log.info("[Entity] id {}, name: {}, second name : {}, age: {}",
                u.getId(), u.getFirstName(), u.getSecondName(), u.getAge()));

        em2.clear();

    }
}
