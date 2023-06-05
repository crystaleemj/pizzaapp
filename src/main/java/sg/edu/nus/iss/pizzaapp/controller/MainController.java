package sg.edu.nus.iss.pizzaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.pizzaapp.model.Pizza;



@Controller
@RequestMapping
public class MainController {
    
    @GetMapping
    public String homePage(Model model){
        model.addAttribute("pizza", new Pizza());
        return "index";
    }
}
