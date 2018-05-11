package in.healthhunt.model.articles.articleResponse;

public class MediaItem{
	private boolean featured;
	private String heading;
	private String media_type;
	private String description;
	private String modified;
	private String id;
	private String url;

	public void setFeatured(boolean featured){
		this.featured = featured;
	}

	public boolean isFeatured(){
		return featured;
	}

	public void setHeading(String heading){
		this.heading = heading;
	}

	public String getHeading(){
		return heading;
	}

	public void setMedia_type(String media_type){
		this.media_type = media_type;
	}

	public String getMedia_type(){
		return media_type;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"MediaItem{" + 
			"featured = '" + featured + '\'' + 
			",heading = '" + heading + '\'' + 
			",media_type = '" + media_type + '\'' +
			",description = '" + description + '\'' + 
			",modified = '" + modified + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}
