package Main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
