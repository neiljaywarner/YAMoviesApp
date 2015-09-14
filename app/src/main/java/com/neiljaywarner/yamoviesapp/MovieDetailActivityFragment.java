package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neiljaywarner.yamoviesapp.model.RelatedVideo;
import com.neiljaywarner.yamoviesapp.model.VideosList;
import com.neiljaywarner.yamoviesapp.model.YAMovie;
import com.neiljaywarner.yamoviesapp.view.YoutubeThumbnailView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MovieDetailActivityFragment extends Fragment {

    public static final String ARG_MOVIE = "arg_movie";
    private static final String TAG = MovieDetailActivityFragment.class.getSimpleName();
    YAMApplication mApp;
    @Bind(R.id.viewGroupTrailers)
    ViewGroup viewGroupRelatedVideos;
    @Bind(R.id.buttonReviews)
    Button mButtonReviews;
    @Bind(R.id.imageViewFavorite)
    ImageView mImageViewFavorite;
    private YAMovie mMovie;
    private TextView mTextViewTitle, mTextViewYear, mTextViewOverview;
    private VideosList mVideosList;
    private CompositeSubscription mCompositeSubscription;

    private boolean mTwoPane;

    public MovieDetailActivityFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (YAMApplication) getActivity().getApplication();

        if (getArguments() != null && getArguments().containsKey(ARG_MOVIE)) {
            mMovie = getArguments().getParcelable(ARG_MOVIE);
            this.getActivity().setTitle(mMovie.getOriginalTitle());
            mTwoPane = true;
        } else {
            MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
            mMovie = movieDetailActivity.getMovie();
            mTwoPane = false;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, root);

        ImageView imageViewThumbnail = (ImageView) root.findViewById(R.id.imageViewThumbnail);
        mTextViewTitle = (TextView) root.findViewById(R.id.textViewTitle);
        mTextViewYear = (TextView) root.findViewById(R.id.textViewYear);
        mTextViewOverview = (TextView) root.findViewById(R.id.textViewOverview);


        mTextViewTitle.setText(mMovie.getOriginalTitle());

        mCompositeSubscription = new CompositeSubscription();


        Picasso.with(getContext()).load(mMovie.getPosterFullUrl())
                .into(imageViewThumbnail, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        loadDetails(mMovie);
                        //  Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap(); // Ew!
                        //  Palette palette = PaletteTransformation.getPalette(bitmap);
                        // TODO apply palette to text views, backgrounds, etc.
                        //see  http://jakewharton.com/coercing-picasso-to-play-with-palette/

                    }

                    @Override
                    public void onError() {
                        loadDetails(mMovie);
                        updateRelatedVideosList(mMovie.getId());
                    }
                });

        //TODO: Fix this when i have placeholder image, probably upon error set the image visible.  Then I woudn't need loading spinner?


        //TODO: Cool activity transitions and get background color from pallette in picture; but I want to make sure I turn it in on time

        mButtonReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewsDialog(v.getContext(), mMovie);
            }
        });

        return root;
    }

    /**
     * Show reviews Activity (dialog) for this movie.
     *
     * @param context
     * @param movie
     */
    private void showReviewsDialog(Context context, YAMovie movie) {
        //  Snackbar.
        context.startActivity(ReviewsActivity.newIntent(context, movie));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mCompositeSubscription.unsubscribe();
    }

    /**
     * Loads detail info into UI.
     *
     * @param movie
     */
    private void loadDetails(YAMovie movie) {
        mTextViewYear.setText(movie.getYear());
        mButtonReviews.setText(movie.getVoteAverage() + "/10");//TODO: Code cleanup here.
        mTextViewOverview.setText(movie.getOverview());

        updateRelatedVideosList(movie.getId());

        if (mApp.isFavorite(movie)) {
            mImageViewFavorite.setImageResource(android.R.drawable.star_big_on);
        } else {
            mImageViewFavorite.setImageResource(android.R.drawable.star_big_off);
        }

        mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite();
            }
        });



    }

    public View getCustomTrailerView(final RelatedVideo relatedVideo) {
        YoutubeThumbnailView relatedVideoView = new YoutubeThumbnailView(this.getActivity());
        relatedVideoView.bind(relatedVideo);
        relatedVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInYoutube(relatedVideo);
            }
        });
        return relatedVideoView;
    }

    private void updateRelatedVideosViews(VideosList videosList) {
        RelatedVideo video;
        for (int i = 0; i < videosList.getVideos().size(); i++) {
            video = videosList.getVideos().get(i);
            View viewTrailer = getCustomTrailerView(video);
            viewGroupRelatedVideos.addView(viewTrailer);
        }

    }


    /// TODO: Consider moving this code to RelatedVideo.java

    private void openInYoutube(RelatedVideo relatedVideo) {
        //TODO: Try/catch or something to do when youtube is not on the app to open in the browser with key
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + relatedVideo.getKey()));
        startActivity(i);
    }


    private void updateRelatedVideosList(int movieId) {
        final MovieService movieService = MovieService.getInstance();


        final Observable<VideosList> videosListObservable;

        videosListObservable = movieService.getVideosList(movieId);

        if (videosListObservable == null) {
            Log.i(TAG, "retrofit observable=null");
            return;
        }
        mCompositeSubscription.add(videosListObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<VideosList>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "observable completed.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "observable error;" + e.getMessage());
                                Toast.makeText(getActivity().getApplicationContext(), "..Please check internet connection and refresh.", Toast.LENGTH_LONG).show();
                                //TODO: Strings.xml strings.
                            }

                            @Override
                            public void onNext(VideosList videosList) {
                                mVideosList = videosList;
                                updateRelatedVideosViews(mVideosList);
                            }
                        })
        );

    }

    private void toggleFavorite() {
        if (mApp.isFavorite(mMovie)) {
            mApp.removeFavorite(mMovie);
            mImageViewFavorite.setImageResource(android.R.drawable.star_big_off);
            Toast.makeText(this.getActivity(), "Removed '" + mMovie.getOriginalTitle() + "' as favorite", Toast.LENGTH_LONG).show();
        } else {
            mApp.saveFavorite(mMovie);
            mImageViewFavorite.setImageResource(android.R.drawable.star_big_on);
            Toast.makeText(this.getActivity(), "Saved '" + mMovie.getOriginalTitle() + "' as favorite", Toast.LENGTH_LONG).show();
        }
    }


    //TODO: Try RxPreferences, it's new rx spiffiness, then RxCupboard for content provider.
}
