package in.healthhunt.model.comment;

public class Author{
	private String name;
	private int id;
	private Object url;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrl(Object url){
		this.url = url;
	}

	public Object getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}
