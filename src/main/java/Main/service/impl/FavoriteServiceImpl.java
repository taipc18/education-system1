package Main.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.FavoriteDAO;
import Main.entity.Favorite;
import Main.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	FavoriteDAO fdao;

	@Override
	public Favorite create(Favorite f) {
		return fdao.save(f);
	}

	@Override
	public List<Integer> findProductIdByUsername(String username) {
		return fdao.findProductIdByUsername(username);
	}

	@Override
	public List<Favorite> findByUsername(String username) {
		return fdao.findByUsername(username);
	}

	@Override
	public void delete(Integer id) {
		fdao.deleteById(id);
	}

	
}
