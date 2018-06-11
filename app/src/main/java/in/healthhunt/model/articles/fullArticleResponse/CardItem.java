package in.healthhunt.model.articles.fullArticleResponse;

import java.util.List;

public class CardItem{
	private String link;
	private String description;
	private List<Object> media;
	private String title;
	private String excerpt;

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMedia(List<Object> media){
		this.media = media;
	}

	public List<Object> getMedia(){
		return media;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setExcerpt(String excerpt){
		this.excerpt = excerpt;
	}

	public String getExcerpt(){
		return excerpt;
	}

	@Override
 	public String toString(){
		return 
			"CardItem{" + 
			"link = '" + link + '\'' + 
			",description = '" + description + '\'' + 
			",media = '" + media + '\'' + 
			",title = '" + title + '\'' + 
			",excerpt = '" + excerpt + '\'' + 
			"}";
		}
}