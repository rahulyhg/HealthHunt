package in.healthhunt.view.homeScreenView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.Tag;
import in.healthhunt.presenter.homeScreenPresenter.HomePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.view.tagView.TagAdapter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class HomeActivity extends AppCompatActivity implements IHomeView{

    @BindView(R.id.home_recycler_list)
    RecyclerView mHomeScreenViewer;

    private HomePresenterImp mHomePresenterImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        ButterKnife.bind(this);
        mHomePresenterImp = new HomePresenterImp(getApplicationContext(), this);
        setAdapter();
    }

    private void setAdapter() {
        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(mHomePresenterImp, getSupportFragmentManager());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mHomeScreenViewer.setLayoutManager(layoutManager);
        mHomeScreenViewer.setAdapter(homeScreenAdapter);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
