package in.healthhunt.model.filter;

import java.util.List;

public class FilterData{
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"FilterData{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}