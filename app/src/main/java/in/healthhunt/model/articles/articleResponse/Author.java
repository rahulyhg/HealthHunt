package in.healthhunt.model.articles.articleResponse;

public class Author{
	private String name;
	private int id;
	private String url;

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
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}
