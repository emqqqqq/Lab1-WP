package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){
        chefs.add(new Chef(1L, "Petar", "Stojanovski", "Bio for Petar", new ArrayList<>()));
        chefs.add(new Chef(2L, "Elena", "Nikolova", "Bio for Elena", new ArrayList<>()));
        chefs.add(new Chef(3L, "Aleksandar", "Trajkov", "Bio for Aleksandar", new ArrayList<>()));
        chefs.add(new Chef(4L, "Marija", "Georgieva", "Bio for Marija", new ArrayList<>()));
        chefs.add(new Chef(5L, "Stefan", "Andonov", "Bio for Stefan", new ArrayList<>()));

        dishes.add(new Dish("D1", "Tavce Gravce", "Macedonian", 60));
        dishes.add(new Dish("D2", "Pasta Carbonara", "Italian", 25));
        dishes.add(new Dish("D3", "Burger", "American", 20));
        dishes.add(new Dish("D4", "Sarma", "Balkan", 90));
        dishes.add(new Dish("D5", "Cinammon rolls", "Dessert", 30));
    }
}
