package in.healthhunt.view.fullView;

import android.os.Bundle;
import android.support.annotation.Nullable;

import in.healthhunt.R;
import in.healthhunt.view.BaseActivity;

/**
 * Created by abhishekkumar on 5/10/18.
 */

public class FullViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_article_view_item);
    }
}
