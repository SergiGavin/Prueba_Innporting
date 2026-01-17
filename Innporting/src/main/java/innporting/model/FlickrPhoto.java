package innporting.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlickrPhoto {

	@JsonProperty("id")
	private String id; //El id se trata como un String.
	
	@JsonProperty("title")
	private ContentWrapper title;
	
	@JsonProperty("description")
	private ContentWrapper description;
	
	@JsonProperty("owner")
	private Owner owner;
	
	@JsonProperty("tags")
	private TagsContainer tags;
	
	@JsonProperty("urls")
	private UrlContainer url;
	

	
	// Getters 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title != null ? title.getContent() : null;
		}
		public void setTitle(ContentWrapper title) {
			this.title = title;
		}

		public String getDescription() {
			return description != null ? description.getContent() : null;
		}
		public void setDescription(ContentWrapper description) {
			this.description = description;
		}

		public String getAuthor() {
			return owner != null ? owner.getUsername() : null;
		}

		public List<String> getTags() {
			if (tags != null && tags.getTag() != null) {
				return tags.getTag().stream()
						.map(Tag::getRaw)
						.toList();
			}
			return null;
		}
		public List<String> getUrl() {
			if (url != null && url.getUrl() != null) {
				return url.getUrl().stream()
						.map(Url::getContentUrl)
						.toList();
			}
			return null;
		}
	
	
	
	// Clases internas para mapear la estructura de Flickr
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ContentWrapper {
			@JsonProperty("_content")
			private String content;

			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
		}
		//Sacamos el username que es el author.
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Owner {
			@JsonProperty("username")
			private String username;

			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class TagsContainer {
			@JsonProperty("tag")
			private List<Tag> tag;

			public List<Tag> getTag() {
				return tag;
			}
			public void setTag(List<Tag> tag) {
				this.tag = tag;
			}
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Tag {
			@JsonProperty("raw")
			private String raw;

			@JsonProperty("_content")
			private String content;

			public String getRaw() {
				return raw;
			}
			public void setRaw(String raw) {
				this.raw = raw;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class UrlContainer {
			@JsonProperty("url")
			private List<Url> url;

			public List<Url> getUrl() {
				return url;
			}
			public void setUrl(List<Url> url) {
				this.url = url;
			}
		}
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Url {
			@JsonProperty("_content")
			private String content;

			public String getContentUrl() {
				return content;
			}
			public void setContentUrl(String content) {
				this.content = content;
			}
		}
		
	
}
