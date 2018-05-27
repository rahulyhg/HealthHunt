package in.healthhunt.model.articles.postProductResponse;

public class FullProductResponse {
	private boolean product;
	private FullProductData data;
	private String message;
	private Object card;
	private boolean status;

	public void setProduct(boolean product){
		this.product = product;
	}

	public boolean isProduct(){
		return product;
	}

	public void setData(FullProductData data){
		this.data = data;
	}

	public FullProductData getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCard(Object card){
		this.card = card;
	}

	public Object isCard(){
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
			"FullProductResponse{" +
			"product = '" + product + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",card = '" + card + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
