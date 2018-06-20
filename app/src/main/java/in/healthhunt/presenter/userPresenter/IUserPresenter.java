package in.healthhunt.presenter.userPresenter;

import in.healthhunt.model.login.User;
import in.healthhunt.model.user.UserRequest;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IUserPresenter {
    User getUser();
    void updateUser(UserRequest userRequest);
    void fetchCurrentUser();
}
