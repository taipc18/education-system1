package Main.service;

import java.util.List;

import Main.entity.News;


public interface NewsService {

	News findById(Integer id);

	News create(News pr);

	News update(News pr); // no lien ket NEWsecviceLmpl

	void delete(Integer id);

	List<News> findAllByOrderByIdDesc();
}
