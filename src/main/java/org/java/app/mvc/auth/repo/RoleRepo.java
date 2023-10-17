package org.java.app.mvc.auth.repo;

import org.java.app.mvc.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
