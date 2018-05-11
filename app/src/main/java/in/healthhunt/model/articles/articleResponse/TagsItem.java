package in.healthhunt.model.articles.articleResponse;

public class TagsItem{
	private Object color;
	private String name;
	private Object icon;
	private int id;
	private String slug;

	public void setColor(Object color){
		this.color = color;
	}

	public Object getColor(){
		return color;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(Object icon){
		this.icon = icon;
	}

	public Object getIcon(){
		return icon;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			",id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}
