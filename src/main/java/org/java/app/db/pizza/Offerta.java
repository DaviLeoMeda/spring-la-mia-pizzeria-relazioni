package org.java.app.db.pizza;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Offerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 64, unique = true, nullable = false)
	@Length(min=1,
			max=64,
			message = "Lunghezza richiesta tra 1 e 64 caratteri")
	@NotBlank
	private String titolo; 
	
	@Column(nullable = false)
	private LocalDate startOccasione;
	@Column(nullable = false)
	private LocalDate endOccasione;
	@Column(nullable = false)
	@Positive(message = "Deve essere un valore superiore allo zero")
	private int sconto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	
	public Offerta() { }
	public Offerta(String titolo, LocalDate startOccasione, 
						LocalDate endOccasione, 
						int sconto, Pizza pizza) {
		
		setTitolo(titolo);
		setStartOccasione(startOccasione);
		setEndOccasione(endOccasione);
//		setSconto(100 - sconto /100);
		setSconto(sconto);
		setPizza(pizza);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public LocalDate getStartOccasione() {
		return startOccasione;
	}


	public void setStartOccasione(LocalDate startOccasione) {
		this.startOccasione = startOccasione;
	}


	public LocalDate getEndOccasione() {
		return endOccasione;
	}


	public void setEndOccasione(LocalDate endOccasione) {
		this.endOccasione = endOccasione;
	}


	public int getSconto() {
		return sconto;
	}


	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	
	
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	@Override
	public String toString() {
		return "Offerta [id= " + id + "] , "
				+ "titolo= " + titolo 
				+ " , startOccasione= " + startOccasione 
				+ " , endOccasione= " + endOccasione 
				+ " , sconto= " + sconto;
	}
	
	
	
}
