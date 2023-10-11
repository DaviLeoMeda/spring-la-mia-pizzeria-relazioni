package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pizza.Offerta;
import org.java.app.db.repo.OffertaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {

	@Autowired
	private OffertaRepo offertaRepo;
	
	public List<Offerta> findAll() {
		return offertaRepo.findAll();

	}
	
	public Offerta findById(int id) {
		return offertaRepo.findById(id).get();
	}
	
	public void save(Offerta offerta) {
		offertaRepo.save(offerta);
	}
}
