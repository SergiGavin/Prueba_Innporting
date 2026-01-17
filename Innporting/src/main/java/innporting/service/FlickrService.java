package innporting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import innporting.model.FlickrPhoto;
import innporting.model.FlickrPhotoList;
import innporting.model.FlickrPhotoSearch;
import innporting.model.FlickrResponse;

@Service
public class FlickrService {
	
	private final String apiKey;
	private final WebClient webclient;
	
	//Lo llamamos con constructor y recoge la key y el secreto de las properties. 
	public FlickrService(@Value("${flickr.api_key}") String apiKey) {
		this.apiKey = apiKey;
		this.webclient = WebClient.builder().baseUrl("https://api.flickr.com/services/rest").build();
	}
	
	public FlickrPhoto searchPhotosById(String id){
		try {
		
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
			
			
			  if (response == null || response.getPhoto() == null) {
		            throw new RuntimeException("Respuesta vacía de Flickr");
		        }
			
			
			FlickrPhoto photo = response.getPhoto();
			
			return photo;	
			
		}catch(Exception e){
			System.err.println("Error al buscar la foto en Flickr: " + e.getMessage());
	        throw new RuntimeException("El id de la foto que ha dado error es: " + id, e);
		}
	}
	
	//Añadimos aqui en el json la url y el autor.
	public FlickrPhotoList searchPhotoByQuery(String query, int page, int size) {
	    try {
	        // 1️⃣ Llamada al search
	        FlickrResponse response = webclient.get()
	            .uri(uriBuilder -> uriBuilder
	                .queryParam("method", "flickr.photos.search")
	                .queryParam("api_key", apiKey)
	                .queryParam("text", query)
	                .queryParam("page", page)
	                .queryParam("per_page", size)
	                .queryParam("format", "json")
	                .queryParam("nojsoncallback", "1")
	                .build())
	            .retrieve()
	            .bodyToMono(FlickrResponse.class)
	            .block();

	        if (response == null || response.getPhotos() == null) {
	            throw new RuntimeException("Respuesta vacía de Flickr search");
	        }
	        

	        FlickrPhotoList searchList = response.getPhotos();
	        List<FlickrPhotoSearch> photoList = new ArrayList<>();

	        // Añado al JSON los datos que necesitemos. 
	        for (FlickrPhotoSearch photos : searchList.getPhoto()) {
	            FlickrPhoto photo = searchPhotosById(photos.getId());

	            FlickrPhotoSearch result = new FlickrPhotoSearch();
	            result.setId(photos.getId());
	            List<String> urls = photo.getUrl(); // obtenemos la lista
	            //Agarramos el primer elemento de la lista.	            
	            result.setUrl(urls != null && !urls.isEmpty() ? urls.get(0) : null); 

	            photoList.add(result);
	        }
	             
	        searchList.setPhoto(photoList);
	        return searchList;

	    } catch (Exception e) {
	        System.err.println("Error al buscar la foto en Flickr: " + e.getMessage());
	        throw new RuntimeException("El concepto buscado que ha dado error ha sido: " + query, e);
	    }
	}

	
	
	//http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=aa80857dbe0f4136252b8177f378d669&tags=gato&format=json&per_page=5
	// flickr.photos.getInfo
	//https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=aa80857dbe0f4136252b8177f378d669&photo_id=12345&format=json&nojsoncallback=1
	
}
