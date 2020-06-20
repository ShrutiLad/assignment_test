package com.ravionics.controller;


import com.ravionics.model.Logintest;
import com.ravionics.service.ValidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ValidUserController {

    @Autowired
    private ValidUserService validUserService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("logintest/login");
        return modelAndView;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        Logintest validUser = new Logintest();
        modelAndView.addObject("logintest", validUser);
        modelAndView.setViewName("logintest/signup");

        return modelAndView;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Validated Logintest validUser, BindingResult bindingResult) {
        ModelAndView modelAndView= new ModelAndView();
        Logintest ifValid = validUserService.findValidUserByUsername(validUser.getUsername());

        if(ifValid != null) {
            bindingResult.rejectValue("username", "error.logintest", "This username already exists!");
        }
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("logintest/signup");
        } else {
            validUserService.saveValidUser(validUser);
            modelAndView.addObject("msg", "Congratulations!! New logintest has been registered successfully!");
            modelAndView.addObject("logintest", new Logintest());
            modelAndView.setViewName("logintest/signup");
        }
        return modelAndView;
    }


    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Logintest validUser = validUserService.findValidUserByUsername(authentication.getName());
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/access_denied");
        return modelAndView;
    }
}