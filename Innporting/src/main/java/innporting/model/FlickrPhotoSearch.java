package innporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlickrPhotoSearch {

	@JsonProperty("id")
	private String id; 
	
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	
}
