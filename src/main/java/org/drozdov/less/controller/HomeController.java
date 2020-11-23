package org.drozdov.less.controller;


import org.drozdov.less.dao.UserDAO;
import org.drozdov.less.entity.User;
import org.drozdov.less.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


@Controller
public class HomeController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserDAO userDAO;
   // private Collection<User> users = new ArrayList<>();
    @GetMapping(value = "/")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/users")
    public String getUsers(Model model) throws SQLException {
        model.addAttribute( "users",userDAO.getAll());
        return "users";
    }
    @GetMapping(value = "/addusers")
    public String getSignUp(Model model)
    {
        model.addAttribute( "user", new User() );
        return "sign_up";
    }
    @PostMapping(value = "/addusers")
    public String getSignUp(@ModelAttribute @Valid User user, BindingResult result) throws SQLException {
        userValidator.validate(user,result);
        if (result.hasErrors()){
            return "sign_up";
        }
        userDAO.add(user);
        return "redirect:/users";
    }

}
