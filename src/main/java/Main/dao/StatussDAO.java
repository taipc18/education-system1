package Main.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import Main.entity.Statuss;


public interface StatussDAO extends JpaRepository<Statuss, String>{
	
}
