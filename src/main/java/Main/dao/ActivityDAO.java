package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Activity;


public interface ActivityDAO extends JpaRepository<Activity, Integer>{
	
	public List<Activity> findAllByOrderByIdDesc();
}
