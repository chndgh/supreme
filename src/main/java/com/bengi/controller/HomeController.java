package com.bengi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by edward on 16/12/20.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"","/home"}, method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
