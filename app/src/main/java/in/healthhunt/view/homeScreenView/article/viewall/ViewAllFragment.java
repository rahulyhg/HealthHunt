package in.healthhunt.view.homeScreenView.article.viewall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.ViewAllPresenterImp;

/**
 * Created by abhishekkumar on 5/3/18.
 */

public class ViewAllFragment extends Fragment implements IViewAll{


    @BindView(R.id.view_all_recycler_list)
    RecyclerView mViewAllViewer;

    private ViewAllPresenterImp mViewAllPresenterImp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewAllPresenterImp = new ViewAllPresenterImp(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        ButterKnife.bind(this, view);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        ViewAllAdapter viewAllAdapter = new ViewAllAdapter(getContext(), mViewAllPresenterImp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mViewAllViewer.setLayoutManager(layoutManager);
        mViewAllViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext())));
        mViewAllViewer.setAdapter(viewAllAdapter);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View view) {
        return new ViewAllHolder(view);
    }
}
