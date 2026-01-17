package innporting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import innporting.model.FlickrPhoto;
import innporting.model.FlickrPhotoList;
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
			
			return new ResponseEntity<>(photo, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
	}
	
	@GetMapping("/api/images/search")
	public ResponseEntity<FlickrPhotoList> getPhotoByQuery(@RequestParam String query, @RequestParam int page, @RequestParam int size){ 
		
		FlickrPhotoList photoList = flickrService.searchPhotoByQuery(query, page, size);
		
		if(photoList != null) {
			
			return new ResponseEntity<>(photoList, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
	}
	
}
