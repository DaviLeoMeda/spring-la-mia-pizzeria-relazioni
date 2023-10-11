package org.java.app.db.repo;

import java.util.List;

import org.java.app.db.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	public List<Pizza> findByName(String name);
	public List<Pizza> findByNameContainingOrDescriptionContaining(String name, String description);
}