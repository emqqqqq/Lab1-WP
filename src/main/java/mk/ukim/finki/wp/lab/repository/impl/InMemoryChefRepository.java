package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public class InMemoryChefRepository {
    public List<Chef> findAll(){
        return DataHolder.chefs;
    };

    public Optional<Chef> findById(Long id){
        return DataHolder.chefs.stream().filter(r->r.getId().equals(id)).findFirst();
    };

    public Chef save(Chef chef){
        if(chef==null || chef.getId()==0){
            return null;
        }
        DataHolder.chefs.removeIf(c->c.getId().equals(chef.getId()));
        DataHolder.chefs.add(chef);
        return chef;
    };
}
