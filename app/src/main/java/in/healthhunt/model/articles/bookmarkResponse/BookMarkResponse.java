package in.healthhunt.model.articles.bookmarkResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookMarkResponse{
	private List<Object> headers;

	@SerializedName("data")
	private BookMarkData data;
	private int status;

	public void setHeaders(List<Object> headers){
		this.headers = headers;
	}

	public List<Object> getHeaders(){
		return headers;
	}

	public void setData(BookMarkData data){
		this.data = data;
	}

	public BookMarkData getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
	public String toString(){
		return
				"BookMarkResponse{" +
						"headers = '" + headers + '\'' +
						",data = '" + data + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}