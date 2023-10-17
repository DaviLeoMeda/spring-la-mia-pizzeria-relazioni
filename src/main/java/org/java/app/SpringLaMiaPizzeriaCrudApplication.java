package org.java.app;



import java.time.LocalDate;

import org.java.app.db.pizza.Ingrediente;
import org.java.app.db.pizza.Offerta;
import org.java.app.db.pizza.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.OffertaService;
import org.java.app.db.serv.PizzaService;
import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.service.RoleService;
import org.java.app.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OffertaService offertaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingrediente mozzarella = new Ingrediente ("mozzarella");
		Ingrediente pomodoro = new Ingrediente ("pomodoro");
		Ingrediente basilico = new Ingrediente ("basilico");
		Ingrediente melanzane = new Ingrediente ("melanzane");
		Ingrediente acciughe = new Ingrediente ("acciughe");
		Ingrediente olive = new Ingrediente ("olive");
		
		ingredienteService.save(mozzarella);
		ingredienteService.save(pomodoro);
		ingredienteService.save(basilico);
		ingredienteService.save(melanzane);
		ingredienteService.save(acciughe);
		ingredienteService.save(olive);
		
		Pizza Marghe = new Pizza("Margherita", "la classica", "https://bing.com/th?id=OSK.7f42c3c8664a1feb4328bb48cccc684b", 7.99f, mozzarella, pomodoro, basilico);
		Pizza Calz = new Pizza("Calzone", "la ripiena", "https://www.inran.it/wp-content/uploads/2022/04/Calzone-napoletano.jpg", 8.99f, mozzarella, pomodoro, basilico);
		Pizza Napo = new Pizza("Napoli", "la basica", "https://images.eatsmarter.de/sites/default/files/styles/max_size/public/pizza-napoli-43827.jpg", 5.99f, mozzarella, pomodoro, basilico, acciughe, olive);
		Pizza Bufa = new Pizza("Bufalina", "la sfiziosa", "https://www.islascanarias.shopping/shop/images/com_hikashop/upload/vendor37/pizza_bufala_807015405.jpg", 9.99f, mozzarella, pomodoro, basilico);
		Pizza Verd = new Pizza("Verdure", "la sana", "https://i.pinimg.com/originals/5e/5f/66/5e5f6607b2c16f2d8c8b5d36189f7ca7.jpg", 6.99f, mozzarella, pomodoro, basilico, melanzane);
		
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
		
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		
		roleService.save(adminRole);
		roleService.save(userRole);
		
		final String pwdAdmin = new BCryptPasswordEncoder().encode("pwd");
		final String pwdUser = new BCryptPasswordEncoder().encode("pwd");
		
		User laforgeAdmin = new User("laforge", pwdAdmin, adminRole);
		User tuckerUser = new User("tucker", pwdUser, userRole);
		
		userService.save(laforgeAdmin);
		userService.save(tuckerUser);
	}

}
