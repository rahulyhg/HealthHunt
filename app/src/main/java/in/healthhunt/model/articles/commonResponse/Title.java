package in.healthhunt.model.articles.commonResponse;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Titles")
public class Title extends Model{

	/*@Column(name = "product_id", onDelete = Column.ForeignKeyAction.CASCADE)
	public int product_id;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
*/
	@Column(name = "rendered")
	public String rendered;

	@Column(name = "raw")
	public String raw;

	public Title(){
		super();
	}

	public void setRendered(String rendered){
		this.rendered = rendered;
	}

	public String getRendered(){
		return rendered;
	}

	public void setRaw(String raw){
		this.raw = raw;
	}

	public String getRaw(){
		return raw;
	}

	/*public List<ProductPostItem> items() {
		return getMany(ProductPostItem.class, "Title");
	}*/

	@Override
 	public String toString(){
		return 
			"Title{" + 
			"rendered = '" + rendered + '\'' + 
			",raw = '" + raw + '\'' + 
			"}";
		}
}
