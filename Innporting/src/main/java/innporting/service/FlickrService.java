package innporting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import innporting.model.FlickrPhoto;
import innporting.model.FlickrResponse;

@Service
public class FlickrService {
	
	private final String apiKey;
	private final String apiSecret;
	private final WebClient webclient;
	
	//Lo llamamos con constructor y recoge la key y el secreto de las properties. 
	public FlickrService(@Value("${flickr.api_key}") String apiKey, @Value("${flickr.api_secret}") String apiSecret) {
		this.apiKey = apiKey;
		this.apiSecret= apiSecret;
		this.webclient = WebClient.builder().baseUrl("https://api.flickr.com/services/rest").build();
	}
	
	public FlickrPhoto searchPhotosById(String id){
			
		
			FlickrResponse response = webclient.get()
					.uri(uriBuilder -> uriBuilder
							.queryParam("method", "flickr.photos.getInfo")
							.queryParam("api_key",apiKey)
							.queryParam("photo_id",id)
							.queryParam("format","json")
							.queryParam("nojsoncallback", "1")
							.build())
					.retrieve()
					.bodyToMono(FlickrResponse.class)
					.block();
			
			FlickrPhoto photo = response.getPhoto();
			
			
			System.out.println("response: " + photo.getId());
			System.out.println("ID: " + photo.getId());
			System.out.println("Title: " + photo.getTitle());
			System.out.println("Author: " + photo.getAuthor());
			System.out.println("Image URL: " + photo.getUrl());
			System.out.println("CLAVE: " + apiKey);
			
			return photo;	
	}
	
	
	//http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=aa80857dbe0f4136252b8177f378d669&tags=gato&format=json&per_page=5
	// flickr.photos.getInfo
	
	//https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=aa80857dbe0f4136252b8177f378d669&photo_id=12345&format=json&nojsoncallback=1
	
}
