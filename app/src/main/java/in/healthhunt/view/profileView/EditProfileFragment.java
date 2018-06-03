package in.healthhunt.view.profileView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.login.User;
import in.healthhunt.presenter.tagPresenter.ITagPresenter;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.IHomeView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;

/**
 * Created by abhishekkumar on 6/3/18.
 */

public class EditProfileFragment extends Fragment{

    @BindView(R.id.user_pic)
    ImageView mProfilePic;

    @BindView(R.id.change_photo)
    TextView mChangePhoto;

    @BindView(R.id.profile_details)
    LinearLayout mUserInfo;

    @BindView(R.id.username)
    EditText mUserName;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.edit_profile_text)
    TextView mCancel;

    @BindView(R.id.logout)
    TextView mDone;

    private ITagPresenter ITagPresenter;
    private IHomeView IHomeView;
    private final int PICK_IMAGE_REQUEST = 1001;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IHomeView = (HomeActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_edit_profile, container, false);
        ButterKnife.bind(this, view);
        mChangePhoto.setVisibility(View.VISIBLE);
        mUserInfo.setVisibility(View.GONE);
        mCancel.setText(R.string.cancel);
        mDone.setText(R.string.done);
        IHomeView.updateTitle(getString(R.string.edit_profile));
        IHomeView.hideBottomNavigationSelection();
        setUserInfo();
        return view;
    }

    private void setUserInfo() {

        User user = User.getUser();
        String name = user.getName();//HealthHuntPrefere

        // nce.getString(getContext(), user.getUsername());
        if(name != null) {
            mUserName.setText(name);
        }

        String email = user.getEmail();//HealthHuntPrefere

        // nce.getString(getContext(), user.getUsername());
        if(email != null) {
            mEmail.setText(email);
        }

        String url = user.getUserImage();//HealthHuntPreference.getString(getContext(), user.getUserId());

        if(url != null) {
            url = url.replace("\n", "");
            Glide.with(getContext())
                    .load(url)
                    .bitmapTransform(new CropCircleTransformation(getContext())).placeholder(R.mipmap.avatar)
                    .into(mProfilePic);
        }
        else {
            mProfilePic.setImageResource(R.mipmap.avatar);
        }

        //String designation = user.get
    }

    @OnClick(R.id.edit_profile_text) //Cancel Button
    void onCancel(){
        finishFragment();
    }

    private void finishFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.logout) //Done Button
    void onDone(){
        User user = User.getUser();
        user.setName(mUserName.getText().toString());
        user.save();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.user_pic)
    void onChange(){
        changeImage();
    }

    private void changeImage(){
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Glide.with(this).loadFromMediaStore(uri).bitmapTransform(new CropCircleTransformation(getContext())).
                    placeholder(R.mipmap.avatar).into(mProfilePic);
            User user = User.getUser();
            user.setUserImage(uri.toString());
            user.save();



            /*try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
