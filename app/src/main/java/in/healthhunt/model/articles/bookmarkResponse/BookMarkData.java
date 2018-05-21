package in.healthhunt.model.articles.bookmarkResponse;

import com.google.gson.annotations.SerializedName;

public class BookMarkData {

	@SerializedName("data")
	private BookMarkInfo data;

	private String message;
	private boolean status;

	public void setBookMarkInfo(BookMarkInfo data){
		this.data = data;
	}

	public BookMarkInfo getBookMarkInfo(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"BookMarkData{" +
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
