package sg.edu.nus.iss.pizzaapp.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzaapp.model.Order;

@Repository
public class PizzaRepo {
 
    @Autowired
    private RedisTemplate<String, Object> template;

    public void save(Order order){

        //opsForValue = to .set/.get value to/fro database 
        template.opsForValue().set(order.getOrderId(), order.toJson().toString());
    }

    public Order getOrder(String orderId){
        String json = (String) template.opsForValue().get(orderId);
        return Order.create(json);
    }
}
