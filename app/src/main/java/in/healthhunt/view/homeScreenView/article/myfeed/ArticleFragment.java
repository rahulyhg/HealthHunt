package in.healthhunt.view.homeScreenView.article.myfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ArticleFragment extends Fragment {

    private Unbinder mUnBinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_item_view, container, false);
        mUnBinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        //int index = bundle.getInt(Constants.VIEWPAGER_FRAGMENT_NO_KEY);
//        if(index == ArticleFragment.ARTICLE_COUNT - 1) {
//            //mOnBoardText.setText(getText(R.string.swipe_right_back));
//            //mGetStarted.setVisibility(View.VISIBLE);
//        }
//        else {
//           // mOnBoardText.setText(getText(R.string.explore_new_things));
//            //mGetStarted.setVisibility(View.INVISIBLE);
//        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
