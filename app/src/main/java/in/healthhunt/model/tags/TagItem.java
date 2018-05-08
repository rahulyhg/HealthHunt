package in.healthhunt.model.tags;

public class TagItem {
	private String color;
	private String name;
	private String icon;
	private int count;
	private String description;
	private int id;
	private String slug;
	private boolean isPressed;

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean pressed) {
		isPressed = pressed;
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

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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
			"TagItem{" +
			"color = '" + color + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",count = '" + count + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}
