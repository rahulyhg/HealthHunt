package in.healthhunt.model.articles.postResponse;

public class FullArticleResponse {
	private boolean product;
	private FullArticleData data;
	private String message;
	private Object/*List<CardItem>*/ card;
	private boolean status;

	public void setProduct(boolean product){
		this.product = product;
	}

	public boolean isProduct(){
		return product;
	}

	public void setData(FullArticleData data){
		this.data = data;
	}

	public FullArticleData getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCard(/*List<CardItem>*/Object card){
		this.card = card;
	}

	public /*List<CardItem>*/Object getCard(){
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
			"FullArticleResponse{" +
			"product = '" + product + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",card = '" + card + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}