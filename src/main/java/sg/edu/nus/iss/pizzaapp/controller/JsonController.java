package sg.edu.nus.iss.pizzaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.pizzaapp.model.Order;
import sg.edu.nus.iss.pizzaapp.service.PizzaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "/order")
public class JsonController {
    @Autowired
    private PizzaService svc;

    @GetMapping(path="{orderId}")
    public ResponseEntity<String> getOrderDetails(@PathVariable String orderId) {
        Order o = svc.getOrderbyId(orderId);
        if (o == null) {
            JsonObject error = Json.createObjectBuilder()
            .add("message", "order id not found")
            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
        return ResponseEntity.ok(o.toJson().toString());
    }


    
}
