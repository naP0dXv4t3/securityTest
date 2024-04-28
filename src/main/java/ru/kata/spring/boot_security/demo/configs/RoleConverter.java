package ru.kata.spring.boot_security.demo.configs;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

//Тестовые данные для входа:
//admin
//12345
@Component
public class RoleConverter implements Converter<String, Role> {
    private final RoleDao roleDao;
    private final List<Role> roles = new ArrayList<>();

    public RoleConverter(RoleDao roleDao) {
        this.roleDao = roleDao;
        roleDao.findAll().forEach(roles::add);
    }

    @Override
    public Role convert(String RoleID) {
        return roleDao.findByUsername(RoleID);
    }
}
