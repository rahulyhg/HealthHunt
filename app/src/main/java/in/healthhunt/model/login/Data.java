package in.healthhunt.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data<T>{

	@SerializedName(value="data")
	@Expose
	private T data;


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
 	public String toString(){
		return 
			"BookMarkData{" +
			"user = '" + data + '\'' +
			"}";
		}
}
