package com.neiljaywarner.yamoviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neiljaywarner.yamoviesapp.model.ReviewsList;
import com.neiljaywarner.yamoviesapp.model.YAMovie;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Dialog themed activity to go on top when the user presses 8/10 or similar.
 */
public class ReviewsActivity extends Activity {

    public static final String EXTRA_MOVIE = "movie_extra";

    private static final String TAG = ReviewsActivity.class.getSimpleName();
    private ReviewsList mReviewsList;

    private CompositeSubscription mCompositeSubscription;
    private ReviewsRecyclerViewAdapter mAdapter;
    private View mEmptyView;

    /**
     * Convenience method so you can start this activity properly.
     *
     * @param movie
     * @return
     */
    public static Intent newIntent(Context context, YAMovie movie) {
        Intent intent = new Intent(context, ReviewsActivity.class);
        intent.putExtra(ReviewsActivity.EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        mCompositeSubscription = new CompositeSubscription();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mAdapter = new ReviewsRecyclerViewAdapter();

        mCompositeSubscription = new CompositeSubscription();

        recyclerView.setAdapter(mAdapter);
        final YAMovie movie = (YAMovie) getIntent().getExtras().get(EXTRA_MOVIE);

        this.setTitle(movie.getOriginalTitle());
        Log.i(TAG, "in ReviewsActivity onCreate about to load/update reviews for '" + movie.getOriginalTitle());
        updateReviewsList(movie.getId());

        mEmptyView = findViewById(android.R.id.empty);
        (findViewById(R.id.closeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewsActivity.this.finish();
            }
        });
        // TODO: 3 lines...https://github.com/twotoasters/JazzyListView/blob/master/sample/src/main/java/com/twotoasters/jazzylistview/sample/RecyclerViewActivity.java
    }


    //TODO: code cleanup when i have more time, too much duplication!  Uncle Bob says the only way to write good code is to write bad code and refactor..

    private void updateReviewsList(int movieId) {
        final MovieService movieService = MovieService.getInstance();

        final Observable<ReviewsList> reviewsListObservable;

        reviewsListObservable = movieService.getReviewsList(movieId);

        if (reviewsListObservable == null) {
            Log.i(TAG, "retrofit observable=null");
            return;
        }
        mCompositeSubscription.add(reviewsListObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ReviewsList>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "reviews observable completed.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "reviews observable error;" + e.getMessage());
                                Toast.makeText(getApplicationContext(), "..Please check internet connection and refresh.", Toast.LENGTH_LONG).show();
                                //TODO: Strings.xml strings.
                            }

                            @Override
                            public void onNext(ReviewsList videosList) {
                                mReviewsList = videosList;
                                mAdapter.setData(mReviewsList);

                                if (mReviewsList.getResults().size() == 0) {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                } else {
                                    mEmptyView.setVisibility(View.GONE);
                                }
                            }
                        })
        );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }
}
