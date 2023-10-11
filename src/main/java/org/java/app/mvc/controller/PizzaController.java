package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pizza.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class PizzaController {

	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/pizze")
	public String getIndex(Model model,
							@RequestParam(required = false) String research) {
		
//		System.out.println("Research:" + research);
		
		List<Pizza> pizze = research == null
					? pizzaService.findAll()
					: pizzaService.findByNameOrDescriptionContaining(research, research);	
		model.addAttribute("pizze", pizze);
		
		
		return "pizza-index";
	}
	
	@GetMapping("/pizze/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
	
	@GetMapping("/pizze/create")
	public String getCreate(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-create";
	}
	
	@PostMapping("/pizze/create")
	public String storePizza(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().stream()
			.map(e -> e.getDefaultMessage())
			.forEach(System.out::println);
			
			return "pizza-create";
		} else {
			System.out.println("Data confirmed");
		}
		
		System.out.println(formPizza);
		
		try {pizzaService.save(formPizza);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		 
		return "redirect:/pizze";
	}
	
	@GetMapping("/pizze/update/{id}")
	public String getUpdate(
			@PathVariable int id,
			Model model
		) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-create";
	}
		
		
	
	@PostMapping("/pizze/update/{id}")
	public String updatePizza(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().stream()
			.map(e -> e.getDefaultMessage())
			.forEach(System.out::println);
			
			return "pizza-create";
		} else {
			System.out.println("Data confirmed");
		}
		
		System.out.println(formPizza);
		
		try {pizzaService.save(formPizza);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/pizze";
	}
	
	@PostMapping("/pizze/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		pizzaService.deletePizza(pizza);
		
		return "redirect:/pizze";
	}
	
}
