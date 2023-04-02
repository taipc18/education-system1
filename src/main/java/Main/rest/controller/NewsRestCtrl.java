package Main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Main.entity.News;
import Main.service.NewsService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/news")
public class NewsRestCtrl {
	// khi chọn chức năng nào đó sẽ thực thi chức năng đó dẩn đến interface implements 
	@Autowired
	NewsService nSV;
	
	@GetMapping("{id}")
	public News getOne(@PathVariable("id") Integer id) {
		return nSV.findById(id);
	}

	@GetMapping()
	public List<News> getAll() {
		return nSV.findAllByOrderByIdDesc();
	}
	// THEM 
	@PostMapping
	public News create(@RequestBody News pr) {
		return nSV.create(pr); //THUC THI HAM CR TRONG NEWCERVICE
	}
	 // trong ham  co ham update   Putmapping
	@PutMapping("{id}")
	public News update(@PathVariable("id") Integer id, @RequestBody News pr) {
		return nSV.update(pr); // ham update tra ve thuc the News = ham update newserivece
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		nSV.delete(id);
	}
}
