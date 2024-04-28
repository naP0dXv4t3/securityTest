package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Optional;
//Тестовые данные для входа:
//admin
//12345
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    User findByUsername(String username);
    @Query("SELECT u FROM User u")
    List<User> listAllUsers();
    @Override
    <S extends User> S save(S user);

    @Override
    void deleteById(Long id);

    @Override
    Optional<User> findById(Long id);
}
