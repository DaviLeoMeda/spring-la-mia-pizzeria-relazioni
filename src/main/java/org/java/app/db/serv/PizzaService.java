package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pizza.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}
	
	public List<Pizza> findAll(){
		return pizzaRepo.findAll();
	}
	
	public Pizza findById(int id) {
		return pizzaRepo.findById(id).get();
	}
	
	public List<Pizza> findByNameOrDescriptionContaining(String name, String description) {
		return pizzaRepo.findByNameContainingOrDescriptionContaining(name, description);
	}
		
	public void deletePizza(Pizza pizza) {
		pizzaRepo.delete(pizza);
	}
		
}
