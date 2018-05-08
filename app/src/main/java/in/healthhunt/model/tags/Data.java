package in.healthhunt.model.tags;

import java.util.List;

public class Data{
	private int offset;
	private int limit;
	private List<TagItem> tags;

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setTags(List<TagItem> tags){
		this.tags = tags;
	}

	public List<TagItem> getTags(){
		return tags;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"offset = '" + offset + '\'' + 
			",limit = '" + limit + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}