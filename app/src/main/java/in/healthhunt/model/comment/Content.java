package in.healthhunt.model.comment;

public class Content{
	private String rendered;

	public void setRendered(String rendered){
		this.rendered = rendered;
	}

	public String getRendered(){
		return rendered;
	}

	@Override
 	public String toString(){
		return 
			"Content{" + 
			"rendered = '" + rendered + '\'' + 
			"}";
		}
}
