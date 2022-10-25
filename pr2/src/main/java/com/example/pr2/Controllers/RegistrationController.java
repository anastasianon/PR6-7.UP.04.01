package com.example.pr2.Controllers;

import com.example.pr2.Models.Role;
import com.example.pr2.Models.User;
import com.example.pr2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepos;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }
    @GetMapping("/login")
    public String log() {
        return "login";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepos.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.addAttribute("message", "Пользователь с таким логином уже есть");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepos.save(user);
        return "redirect:/login";
    }
}
