package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) Long chefId,
                                @RequestParam(required = false) String error,
                                Model model) {

        if (error != null)
            model.addAttribute("error", error);

        if (chefId != null) {
            model.addAttribute("dishes", dishService.listDishesByChef(chefId));
        } else {
            model.addAttribute("dishes", dishService.listDishes());
        }

        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("selectedChefId", chefId);

        return "listDishes";
    }


    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", null);
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {

        Dish dish = dishService.findById(id);
        if (dish == null)
            return "redirect:/dishes?error=DishNotFound";

        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {

        Dish dish = dishService.create(dishId, name, cuisine, preparationTime);
        Chef chef = chefService.findById(chefId);
        dish.setChef(chef);
        dishService.update(dish.getId(), dish.getDishId(), dish.getName(), dish.getCuisine(), dish.getPreparationTime());
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {

        Dish dish = dishService.update(id, dishId, name, cuisine, preparationTime);
        Chef chef = chefService.findById(chefId);
        dish.setChef(chef);
        dishService.update(dish.getId(), dish.getDishId(), dish.getName(), dish.getCuisine(), dish.getPreparationTime());
        return "redirect:/dishes";
    }
}