package org.java.app.db.repo;

import org.java.app.db.pizza.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaRepo extends JpaRepository<Offerta, Integer> {

}
