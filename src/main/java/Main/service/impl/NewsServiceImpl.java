package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.NewsDAO;
import Main.entity.News;
import Main.service.NewsService;


@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsDAO ndao; // 

	@Override
	public List<News> findAllByOrderByIdDesc() {
		return ndao.findAllByOrderByIdDesc();
	}

	@Override
	public News findById(Integer id) {
		return ndao.findById(id).get();
	}

	@Override
	public News create(News n) {
		return ndao.save(n);
	}
    
	@Override
	public News update(News n) {
		return ndao.save(n); // no goi ham save TU NEWdAO
	}

	@Override
	public void delete(Integer id) {
		ndao.deleteById(id); // NO SE XOA THUC THE DO TRONG HAM DAO
	}

	
}
