package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryChefRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryDishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChefServiceImpl implements ChefService {

    private InMemoryChefRepository chefRepository;
    private InMemoryDishRepository dishRepository;

    public ChefServiceImpl(InMemoryChefRepository chefRepository, InMemoryDishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        Dish dish = dishRepository.findByDishId(dishId);
        if (chef != null && dish != null) {
            chef.getDishes().add(dish);
            chefRepository.save(chef);
        }
        return chef;
    }
}
