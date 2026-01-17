package innporting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class FlickrResponse {
	@JsonProperty("photo")
	private FlickrPhoto photo;
	private String stat;
	@JsonProperty("photos")
	private FlickrPhotoList photos;
	
	
	
	public FlickrPhoto getPhoto() {
		return photo;
	}
	public void setPhoto(FlickrPhoto photo) {
		this.photo = photo;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public FlickrPhotoList getPhotos() {
		return photos;
	}
	public void setPhotoList(FlickrPhotoList photos) {
		this.photos = photos;
	}
	
	
	
	
}
