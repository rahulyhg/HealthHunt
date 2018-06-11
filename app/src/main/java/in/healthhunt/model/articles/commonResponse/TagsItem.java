package in.healthhunt.model.articles.commonResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "TagsItems")
public class TagsItem extends Model{

	@Column(name = "color")
	private String color;

	@Column(name = "name")
	private String name;

	@Column(name = "icon")
	private String icon;

	@Column(name = "tag_id")
	@SerializedName("id")
	private long tag_id;

	@Column(name = "slug")
	private String slug;

	public TagsItem(){
		super();
	}

	@Column(name = "parent_id",unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
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

	public void setTag_id(int tag_id){
		this.tag_id = tag_id;
	}

	public long getTag_id(){
		return tag_id;
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
			"TagsItem{" + 
			"color = '" + color + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",tag_id = '" + tag_id + '\'' +
			",slug = '" + slug + '\'' + 
			"}";
		}

	public void setId(long id) {
					this.tag_id = id;
	}
}
