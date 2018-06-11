package in.healthhunt.model.articles.commonResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "CurrentUsers")
public class CurrentUser extends Model {

	/*@Column(name = "parent_id", onDelete = Column.ForeignKeyAction.CASCADE)
	private long parent_id;
*/
	@Column(name = "hay")
	private int hay;

	@Column(name = "like")
	private int like;

	@Column(name = "collections", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private /*List<Collections>*/Object collections;

	@Column(name = "bookmarked")
	private boolean bookmarked;

	@Column(name = "nay")
	private int nay;

	public CurrentUser() {
		super();
	}

	public void setHay(int hay){
		this.hay = hay;
	}

	public int getHay(){
		return hay;
	}

	public void setLike(int like){
		this.like = like;
	}

	public int getLike(){
		return like;
	}

	public void setCollections(List<Collections> collections){
		this.collections = collections;
	}

	public /*List<Collections>*/Object getCollections(){
		return collections;
	}

	public void setBookmarked(boolean bookmarked){
		this.bookmarked = bookmarked;
	}

	public boolean isBookmarked(){
		return bookmarked;
	}

	public void setNay(int nay){
		this.nay = nay;
	}

	public int getNay(){
		return nay;
	}

	@Override
 	public String toString(){
		return 
			"CurrentUser{" + 
			"hay = '" + hay + '\'' + 
			",like = '" + like + '\'' + 
			",collections = '" + collections + '\'' + 
			",bookmarked = '" + bookmarked + '\'' + 
			",nay = '" + nay + '\'' + 
			"}";
		}

	public boolean getBookmarked() {
					return this.bookmarked;
	}

	/*public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}*/
}