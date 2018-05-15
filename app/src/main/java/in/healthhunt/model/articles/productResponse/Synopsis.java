package in.healthhunt.model.articles.productResponse;

public class Synopsis{
	private Object rendered;
	private Object raw;

	public void setRendered(Object rendered){
		this.rendered = rendered;
	}

	public Object getRendered(){
		return rendered;
	}

	public void setRaw(Object raw){
		this.raw = raw;
	}

	public Object getRaw(){
		return raw;
	}

	@Override
 	public String toString(){
		return 
			"Synopsis{" + 
			"rendered = '" + rendered + '\'' + 
			",raw = '" + raw + '\'' + 
			"}";
		}
}
