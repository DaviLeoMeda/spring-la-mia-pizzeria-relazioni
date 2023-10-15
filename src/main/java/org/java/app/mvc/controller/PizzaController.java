package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pizza.Ingrediente;
import org.java.app.db.pizza.Offerta;
import org.java.app.db.pizza.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.OffertaService;
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
	
	@Autowired
	private OffertaService offertaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
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
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredienti);
		
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
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti", ingredienti);
		
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
		List<Offerta> offerte = pizza.getOfferte();
		
		offertaService.deleteOfferta(offerte);
		pizzaService.deletePizza(pizza);
		
		
		return "redirect:/pizze";
	}
	
	@GetMapping("/pizze/offerta/{pizza_id}")
	public String occasione(
			@PathVariable("pizza_id") int id,
			Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		Offerta offerta = new Offerta();
		
		model.addAttribute("offerta", offerta);
		model.addAttribute("pizza", pizza);
		
		return "offerta-form";
	}
	
	@PostMapping("/pizze/offerta/{pizza_id}")
	public String storeOccasione(
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult, @PathVariable("pizza_id") int id,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			
			return "offerta-form"; 
		}
		
		Pizza pizza = pizzaService.findById(id);
		offerta.setPizza(pizza);
		
		offertaService.save(offerta);
		
		return"redirect:/pizze";
	}
	
	@GetMapping("/pizze/offerta/edit/{off_id}")
	public String editOccasione(
			@PathVariable("off_id") int id,
			Model model) {
		
		Offerta notOfferta = offertaService.findById(id);
		Pizza pizza = notOfferta.getPizza();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerta", notOfferta);
		
		return "offerta-form";
	}
	
	@PostMapping("/pizze/offerta/edit/{off_id}")
	public String updateOccasione(
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult, 
			@PathVariable("off_id") int id,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			
			return "offerta-form"; 
		}
		
		Offerta exOfferta = offertaService.findById(id);
		Pizza pizza = exOfferta.getPizza();
		offerta.setPizza(pizza);
		
		offertaService.save(offerta);
		
		return "redirect:/pizze/" + pizza.getId();
	}
	
}
