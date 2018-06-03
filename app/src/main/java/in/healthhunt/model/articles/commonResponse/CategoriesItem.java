package in.healthhunt.model.articles.commonResponse;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "CategoriesItems")
public class CategoriesItem extends Model {

	@Column(name = "color")
	private String color;

	@Column(name = "name")
	private String name;

	@Column(name = "icon")
	private String icon;

	@Column(name = "category_id")
	@SerializedName("id")
	private int category_id;


	@Column(name = "slug")
	private String slug;

	public CategoriesItem() {
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

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setCategory_id(int category_id){
		this.category_id = category_id;
	}

	public int getCategory_id(){
		return category_id;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"color = '" + color + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",category_id = '" + category_id + '\'' +
			",slug = '" + slug + '\'' + 
			"}";
		}
}
