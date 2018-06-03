package in.healthhunt.model.articles.commonResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Likes")
public class Likes extends Model{

	/*@Column(name = "parent_id", onDelete = Column.ForeignKeyAction.CASCADE)
	private long parent_id;
*/
	@Column(name = "hays")
	private String hays;

	@Column(name = "nays")
	private String nays;

	@Column(name = "likes")
	private String likes;

	public Likes() {
		super();
	}

	public void setHays(String hays){
		this.hays = hays;
	}

	public String getHays(){
		return hays;
	}

	public void setNays(String nays){
		this.nays = nays;
	}

	public String getNays(){
		return nays;
	}

	public void setLikes(String likes){
		this.likes = likes;
	}

	public String getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"Likes{" + 
			"hays = '" + hays + '\'' + 
			",nays = '" + nays + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}

	/*public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
*/}
