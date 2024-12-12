package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String index(Model model) {
        List<User> users = this.userService.getAll();

        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", (List<Role>) roleRepository.findAll());

        return "user";
    }

    @PostMapping("/save")
    public RedirectView save(@ModelAttribute User user,
                             @RequestParam(value = "roles") List<Role> roles) {

        System.out.println(user);
        System.out.println(roles);
//        user.setRoles(roles);
//        userService.save(user);

        return new RedirectView("/admin");
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        User user = userService.get(id);

        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam("id") int id) {
        userService.delete(id);

        return new RedirectView("/");
    }
}
