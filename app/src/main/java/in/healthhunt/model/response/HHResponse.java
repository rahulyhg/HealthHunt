package in.healthhunt.model.response;

public class HHResponse<T>{
	private T data;
	private String message;
	private boolean status;

	public void setData(T data){
		this.data = data;
	}

	public T getData(){
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
			"HHResponse{" +
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
