package Main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Review;

public interface ReviewDAO extends JpaRepository<Review, Integer>{

}
