package in.healthhunt.model.articles.postResponse;

import java.util.List;

public class PostResponse{
	private boolean product;
	private Data data;
	private String message;
	private List<CardItem> card;
	private boolean status;

	public void setProduct(boolean product){
		this.product = product;
	}

	public boolean isProduct(){
		return product;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCard(List<CardItem> card){
		this.card = card;
	}

	public List<CardItem> getCard(){
		return card;
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
			"PostResponse{" + 
			"product = '" + product + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",card = '" + card + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}