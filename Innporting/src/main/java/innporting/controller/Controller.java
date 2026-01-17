package innporting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import innporting.model.FlickrPhoto;
import innporting.service.FlickrService;

@RestController
public class Controller {

	
	private final FlickrService flickrService;
	
	public Controller(FlickrService flickrService) {
		this.flickrService = flickrService;
	}
	
	
	@GetMapping("/api/images/{id}")
	public ResponseEntity<FlickrPhoto> getPhotoById(@PathVariable String id){
		
		FlickrPhoto photo = flickrService.searchPhotosById(id);
		
		if(photo != null) {
			
			System.out.println("Funciona");
			return new ResponseEntity<>(photo, HttpStatus.OK);
			
		}else {
			System.out.println("No Funciona");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
	}
	
}
