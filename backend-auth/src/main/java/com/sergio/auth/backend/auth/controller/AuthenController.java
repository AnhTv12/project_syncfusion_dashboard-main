package com.sergio.auth.backend.auth.controller;
import com.sergio.auth.backend.auth.config.UserAuthenticationProvider;
import com.sergio.auth.backend.auth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class AuthenController {

    @Autowired
    private UserRepository userRepository;
    @ResponseBody
    @GetMapping( "/user/role/{username}")
    public String LoginForm(@PathVariable("username") String username){
        String name =  userRepository.findRoleByUsername(username);
        return name;
    }

    @GetMapping("/")
    public String LoginHandler(){
        return "redirect";
    }
}
