package sg.edu.nus.iss.pizzaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaapp.model.Delivery;
import sg.edu.nus.iss.pizzaapp.model.Order;
import sg.edu.nus.iss.pizzaapp.model.Pizza;
import sg.edu.nus.iss.pizzaapp.service.PizzaService;



@Controller
@RequestMapping
public class MainController {

    @Autowired 
    private PizzaService svc;
    
    @GetMapping
    public String homePage(Model model){
        model.addAttribute("pizza", new Pizza());
        return "index";
    }

    @PostMapping(path= "/pizza", consumes="application/x-www-form-urlencoded")
    public String deliveryPage(Model model, @Valid Pizza p, BindingResult result, HttpSession session){

        if (result.hasErrors()){
            return "index";
        }

        List<ObjectError> errors = svc.validateOrder(p);

        if(!errors.isEmpty()){
            
            for (ObjectError obj : errors) {
                result.addError(obj);
            }
            return "index";
        }

        //Httpsession helps us to bring over objects which are stored in that session
        model.addAttribute("delivery", new Delivery());
        session.setAttribute("pizza", p);

        // checked and values appear
        System.out.println(p);
        return "delivery";
    }

    @PostMapping(path="/pizza/order", consumes="application/x-www-form-urlencoded")
    public String orderPage(@Valid Delivery d, BindingResult result, Model model, HttpSession session){

        if (result.hasErrors()){
            return "delivery";
        }

        Pizza p = (Pizza) session.getAttribute("pizza");

        // checked and values appear
        System.out.println(p);
        Order o = svc.saveOrder(p, d);
        model.addAttribute("order", o);


        return "order";
    }
}
