package in.healthhunt.view.homeScreenView.drawerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.login.User;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.IHomeView;
import in.healthhunt.view.profileView.ProfileFragment;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 5/8/18.
 */

public class DrawerFragment extends Fragment implements CategoryAdapter.ClickListener {


    @BindView(R.id.drawer_list_viewer)
    RecyclerView mDrawerList;

    @BindView(R.id.profile_pic)
    ImageView mProfilePic;

    @BindView(R.id.profile_name)
    TextView mUserName;

    private IHomePresenter IHomePresenter;
    private Unbinder mUnbinder;
    private CategoryAdapter mCategoryAdapter;
    private IHomeView IHomeView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IHomePresenter = ((HomeActivity)getActivity()).getHomePresenter();
        IHomeView = (HomeActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUserInfo();
        setAdapter();
        return view;
    }

    private void setUserInfo() {

        User user = User.getUser();
        String name = user.getUsername();//HealthHuntPreference.getString(getContext(), user.getUsername());
        if(name != null) {
            mUserName.setText(name);
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
    }

    private void setAdapter() {
        mCategoryAdapter = new CategoryAdapter(getContext(), IHomePresenter);
        mCategoryAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mDrawerList.setLayoutManager(layoutManager);
        mDrawerList.setAdapter(mCategoryAdapter);

        Log.i("TAGSelectIed", "SelectIed");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void ItemClicked(View v, String selectedItem) {
        if(IHomePresenter.isCategoryContain(selectedItem)){
            IHomePresenter.removeCategory(selectedItem);
        }
        else {
            IHomePresenter.addCategory(selectedItem);
        }

        if(IHomePresenter.isCategoryContain(Constants.All)){
            IHomePresenter.getCategoryList().clear();
        }

        Log.i("Category", "Category " + selectedItem);
        Log.i("Category", "Category List " + IHomePresenter.getCategoryList());
    }

    public void updateAdapter(){
        if(mCategoryAdapter != null){
            mCategoryAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.edit_profile)
    void onEdit(){
        IHomeView.closeDrawer();
        IHomePresenter.loadNonFooterFragment(ProfileFragment.class.getSimpleName(), null);
    }
}
