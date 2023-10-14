package org.java.app.db.pizza;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(length = 50, unique = true , nullable = false)
	@Length(min=1,
			max=50,
			message = "Lunghezza minima di 1 carattere, massima di 50")
	@NotBlank
	private String name;
	@Length(min=1,
			max=50,
			message = "Lunghezza minima di 1 carattere, massima di 50")
	@NotBlank
	private String description;
	
	@Column(length = 128 , nullable = false)
	@Length(min=1,
			max=128,
			message = "Lunghezza minima di 1 carattere, massima di 128")
	@NotBlank
	private String pic;
	
	@Column(nullable = false)
	@Positive(message = "Deve essere un valore superiore alla linea poveraccio")
	private float price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;
	
	@ManyToMany
	private List<Ingrediente> Ingredienti;
	
	public Pizza() { }
	public Pizza(String name, String description, String pic, float price, Ingrediente... ingredienti) {
		setName(name);
		setDescription(description);
		setPic(pic);
		setPrice(price);
		setIngredienti(Arrays.asList(ingredienti));
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public List<Offerta> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	
	
	
	public List<Ingrediente> getIngredienti() {
		return Ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		Ingredienti = ingredienti;
	}
	@Override
	public String toString() {
		return "Pizza [id= " + id + ", "
					+ "name= " + name + ", "
					+ "description= " + description 
					+ ", pic= " + pic 
					+ ", price= " + price + "]";
	}
	
	
}
