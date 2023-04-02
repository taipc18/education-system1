package Main.rest.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Main.service.ImageService;
import Main.service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadRestCtrl {
	@Autowired
	UploadService up;
	@Autowired
	ImageService imageService;
	@PostMapping("/rest/upload/{folder}")
	public JsonNode upload(@PathVariable("file") MultipartFile file,
			@PathVariable("folder") String folder) {
		File savedFile = up.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", savedFile.getName());
		node.put("size", savedFile.length());
		return node;
	}
	
	@PostMapping("/rest/save/{folder}")
	public String save(@RequestParam("file") MultipartFile file,
			@PathVariable("folder") String folder) {
		String fileName = imageService.save(file, folder);
		return fileName;
	}
}
