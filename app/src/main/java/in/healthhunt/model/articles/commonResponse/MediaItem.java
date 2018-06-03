package in.healthhunt.model.articles.commonResponse;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "MediaItems")
public class MediaItem extends Model {

	@Column(name = "featured")
	private boolean featured;

	@Column(name = "heading")
	private String heading;

	@Column(name = "media_type")
	private String media_type;

	@Column(name = "description")
	private String description;

	@Column(name = "modified")
	private String modified;

	@Column(name = "media_id")
	@SerializedName("id")
	private String media_id;

	@Column(name = "url")
	private String url;

	public MediaItem(){
		super();
	}

	@Column(name = "parent_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private String parent_id;

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

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

	public void setMedia_id(String media_id){
		this.media_id = media_id;
	}

	public String getMedia_id(){
		return media_id;
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
			",media_id = '" + media_id + '\'' +
			",url = '" + url + '\'' + 
			"}";
		}

	public boolean getFeatured() {
					return this.featured;
	}
}
