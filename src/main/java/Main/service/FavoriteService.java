package Main.service;

import java.util.List;

import Main.entity.Favorite;

public interface FavoriteService {


	Favorite create(Favorite f);

	List<Integer> findProductIdByUsername(String username);

	List<Favorite> findByUsername(String username);

	void delete(Integer id);

}
