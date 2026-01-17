package innporting.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlickrPhotoList {
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("perpage")
	private int size;
	
	@JsonProperty("text")
	private String query;
	
	@JsonProperty("photo")
	private List<FlickrPhotoSearch> photos;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getText() {
		return query;
	}

	public void setText(String text) {
		this.query = text;
	}

	public List<FlickrPhotoSearch> getPhoto() {
		return photos;
	}

	public void setPhoto(List<FlickrPhotoSearch> photos) {
		this.photos = photos;
	}


	
	
	
	
	
	
	
	
	
}
