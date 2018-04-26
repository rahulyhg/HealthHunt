package in.healthhunt.model.login;

public class Created{
	private int entries;
	private boolean display;
	private String name;
	private int id;

	public void setEntries(int entries){
		this.entries = entries;
	}

	public int getEntries(){
		return entries;
	}

	public void setDisplay(boolean display){
		this.display = display;
	}

	public boolean isDisplay(){
		return display;
	}

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

	@Override
 	public String toString(){
		return 
			"Created{" + 
			"entries = '" + entries + '\'' + 
			",display = '" + display + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
