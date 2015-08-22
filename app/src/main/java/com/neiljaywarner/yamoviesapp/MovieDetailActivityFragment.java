package com.neiljaywarner.yamoviesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neiljaywarner.yamoviesapp.model.YAMovie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    TextView mTextViewTitle, mTextViewYear, mTextViewVoteAverage, mTextViewOverview;

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView imageViewThumbnail = (ImageView) root.findViewById(R.id.imageViewThumbnail);
        mTextViewTitle = (TextView) root.findViewById(R.id.textViewTitle);
        mTextViewYear = (TextView) root.findViewById(R.id.textViewYear);
        mTextViewVoteAverage = (TextView) root.findViewById(R.id.textViewVoteAverage);
        mTextViewOverview = (TextView) root.findViewById(R.id.textViewOverview);


        final YAMovie movie = (YAMovie) this.getActivity().getIntent().getExtras().get(MovieDetailActivity.EXTRA_MOVIE);

        mTextViewTitle.setText(movie.getOriginalTitle());

        //TODO: Loadiang image for screen with possible crossfade.

        Picasso.with(getContext()).load(movie.getPosterFullUrl())
                .into(imageViewThumbnail, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        loadDetailText(movie);  //if placeholder image then could load success text right away.
                        //  Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap(); // Ew!
                        //  Palette palette = PaletteTransformation.getPalette(bitmap);
                        // TODO apply palette to text views, backgrounds, etc.
                        //see  http://jakewharton.com/coercing-picasso-to-play-with-palette/

                    }

                    @Override
                    public void onError() {
                        loadDetailText(movie);
                    }
                });

        //Question - should I


        //TODO: Cool activity transitions and get background color from pallette in picture; but I want to make sure I turn it in on time

        return root;
    }

    /**
     * Loads detail info into UI.
     *
     * @param movie
     */
    private void loadDetailText(YAMovie movie) {
        mTextViewYear.setText(movie.getYear());
        mTextViewVoteAverage.setText(movie.getVoteAverage() + "/10");//TODO: Code cleanup here.
        mTextViewOverview.setText(movie.getOverview());
    }
}
