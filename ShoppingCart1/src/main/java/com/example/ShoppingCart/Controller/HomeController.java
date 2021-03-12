package com.example.ShoppingCart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/haaat")
    public String home(){
        return "home";
    }
}
