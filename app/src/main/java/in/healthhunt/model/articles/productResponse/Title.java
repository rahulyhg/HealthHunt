package in.healthhunt.model.articles.productResponse;

public class Title{
	private String rendered;
	private String raw;

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

	@Override
 	public String toString(){
		return 
			"Title{" + 
			"rendered = '" + rendered + '\'' + 
			",raw = '" + raw + '\'' + 
			"}";
		}
}
