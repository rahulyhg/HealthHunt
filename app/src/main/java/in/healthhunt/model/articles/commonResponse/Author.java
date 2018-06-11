package in.healthhunt.model.articles.commonResponse;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "Authors")
public class Author extends Model{

	@Column(name = "name")
	private String name;

	@Column(name = "author_id")
	@SerializedName("id")
	private long author_id;

	/*@Column(name = "parent_id", onDelete = Column.ForeignKeyAction.CASCADE)
	private long parent_id;

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
*/
	@Column(name = "url")
	private String url;

	public Author() {
		super();
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAuthor_id(long author_id){
		this.author_id = author_id;
	}

	public long getAuthor_id(){
		return author_id;
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
			"Author{" + 
			"name = '" + name + '\'' + 
			",author_id = '" + author_id + '\'' +
			",url = '" + url + '\'' + 
			"}";
		}
}
