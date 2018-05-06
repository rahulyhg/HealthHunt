package in.healthhunt.model.login;

/**
 * Created by abhishekkumar on 5/2/18.
 */

public class LoginResponse {

    private Data<User> data;
    private String message;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }

    @Override
    public String toString(){
        return
                "LoginResponse{" +
                        "user = '" + data + '\'' +
                        "}";
    }
}
