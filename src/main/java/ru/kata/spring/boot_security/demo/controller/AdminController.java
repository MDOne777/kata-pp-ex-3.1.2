package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        List<User> users = this.userService.getAll();

        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/save")
    public RedirectView save(@ModelAttribute User user) {
        System.out.println(user);

        //userService.save(user);

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
