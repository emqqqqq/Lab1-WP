package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class InMemoryDishRepository {
    public List<Dish> findAll(){
        return DataHolder.dishes;
    };
    public Dish findByDishId(String dishId){
        return DataHolder.dishes.stream().filter(dish -> dish.getDishId().equals(dishId)).findFirst().orElse(null);
    };
}
