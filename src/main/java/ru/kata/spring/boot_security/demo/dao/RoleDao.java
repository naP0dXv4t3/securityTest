package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
//Тестовые данные для входа:
//admin
//12345
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    @Override
    List<Role> findAll();
    @Query("Select r from Role r left join fetch r.users where r.username=:username")
    Role findByUsername(String username);
}
