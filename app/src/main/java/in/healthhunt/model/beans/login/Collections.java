package in.healthhunt.model.beans.login;

public class Collections{
	private Created created;

	public void setCreated(Created created){
		this.created = created;
	}

	public Created getCreated(){
		return created;
	}

	@Override
 	public String toString(){
		return 
			"Collections{" + 
			"created = '" + created + '\'' + 
			"}";
		}
}
