package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.view.homeScreenView.article.myfeed.ITopProductView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TopProductPresenterImp implements ITopProductPresenter {

    private String TAG = TopProductPresenterImp.class.getSimpleName();
    private ITopProductView ITopProductView;
    private Context mContext;

    public TopProductPresenterImp(Context context, ITopProductView topProductView) {
        mContext =  context;
        ITopProductView = topProductView;
    }

    @Override
    public int getProductCount() {
        return ITopProductView.getProductCount();
    }

    @Override
    public ProductPostItem getTopProduct(int pos) {
        return ITopProductView.getProduct(pos);
    }

}
