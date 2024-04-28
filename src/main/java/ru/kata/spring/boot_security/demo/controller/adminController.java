package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;
//Тестовые данные для входа:
//admin
//12345
@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;
    @GetMapping
    public String adminPage (Model model) {
        List<User> allUsers = userService.listAllUsers();
        model.addAttribute("allUsers", allUsers);
    return "admin";
    }
    @GetMapping(value = "/new")
    public String createUserForm(@ModelAttribute("user") User user, Model model)
    {
        List<Role> roles = roleDao.findAll();
        model.addAttribute("roles", roles);
        return "CreateUser";
    }
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, @ModelAttribute("password") String password, @RequestParam("roles") List<Role> roles){
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        Optional<User> userById = userService.findById(id);
        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
            List<Role> roles = roleDao.findAll();
            model.addAttribute("roles", roles);
            return "EditUser";
        }
        return "/admin";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("user") User user,@ModelAttribute("password") String password, @RequestParam("roles") List<Role> roles) {
        userService.saveUser(user);
        user.setRoles(roles);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }




}
