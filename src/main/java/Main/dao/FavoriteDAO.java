package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Main.entity.Favorite;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer>{

	@Query("SELECT f FROM Favorite f WHERE f.account.username=?1")
	List<Favorite> findByUsername(String username);
	
	@Query("SELECT f.product.id FROM Favorite f WHERE f.account.username=?1")
	List<Integer> findProductIdByUsername(String username);
	
	@Query("SELECT f.have FROM Favorite f WHERE f.account.username=?1 and f.product.id=?2")
	String coko(String username, Integer id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Favorite f WHERE f.account.username=?1 and f.product.id=?2")
	void delete(String username, Integer id);
}
