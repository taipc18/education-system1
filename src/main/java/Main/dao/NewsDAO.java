package Main.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.News;

public interface NewsDAO extends JpaRepository<News, Integer>{

	public List<News> findAllByOrderByIdDesc();
}
