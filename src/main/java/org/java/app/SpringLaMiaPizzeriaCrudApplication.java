package org.java.app;



import java.time.LocalDate;

import org.java.app.db.pizza.Offerta;
import org.java.app.db.pizza.Pizza;
import org.java.app.db.serv.OffertaService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OffertaService offertaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Pizza Marghe = new Pizza("Margherita", "la classica", "Foto", 7.99f);
		Pizza Calz = new Pizza("Calzone", "la ripiena", "Foto", 8.99f);
		Pizza Napo = new Pizza("Napoli", "la basica", "Foto", 5.99f);
		Pizza Bufa = new Pizza("Bufalina", "la sfiziosa", "Foto", 9.99f);
		Pizza Verd = new Pizza("Verdure", "la sana", "Foto", 6.99f);
		
		pizzaService.save(Marghe);
		pizzaService.save(Calz);
		pizzaService.save(Napo);
		pizzaService.save(Bufa);
		pizzaService.save(Verd);
		
		System.out.println("Insertion done!");
		
		
		Offerta pizzaDelMese = new Offerta("PizzaMese", LocalDate.parse("2023-05-05"), 
				LocalDate.parse("2023-06-06"), 25, Marghe);
		Offerta pizzaDellAnno = new Offerta("PizzaAnno", LocalDate.parse("2023-01-01"), 
				LocalDate.parse("2023-12-01"), 30, Verd);
		Offerta pizzaDellaSettimana = new Offerta("PizzaSettimana", LocalDate.parse("2023-08-01"), 
				LocalDate.parse("2023-08-07"), 15, Bufa);
		
		offertaService.save(pizzaDellaSettimana);
		offertaService.save(pizzaDelMese);
		offertaService.save(pizzaDellAnno);
		
	}

}
