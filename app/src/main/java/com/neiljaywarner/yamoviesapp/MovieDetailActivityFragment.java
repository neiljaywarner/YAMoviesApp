package com.neiljaywarner.yamoviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neiljaywarner.yamoviesapp.model.RelatedVideo;
import com.neiljaywarner.yamoviesapp.model.YAMovie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MovieDetailActivityFragment extends Fragment {

    TextView mTextViewTitle, mTextViewYear, mTextViewVoteAverage, mTextViewOverview;

    @Bind(R.id.viewGroupTrailers)
    ViewGroup viewGroupRelatedVideos;
    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, root);

        ImageView imageViewThumbnail = (ImageView) root.findViewById(R.id.imageViewThumbnail);
        mTextViewTitle = (TextView) root.findViewById(R.id.textViewTitle);
        mTextViewYear = (TextView) root.findViewById(R.id.textViewYear);
        mTextViewVoteAverage = (TextView) root.findViewById(R.id.textViewVoteAverage);
        mTextViewOverview = (TextView) root.findViewById(R.id.textViewOverview);


        final YAMovie movie = (YAMovie) this.getActivity().getIntent().getExtras().get(MovieDetailActivity.EXTRA_MOVIE);

        mTextViewTitle.setText(movie.getOriginalTitle());


        Picasso.with(getContext()).load(movie.getPosterFullUrl())
                .into(imageViewThumbnail, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        loadDetailText(movie);
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

        //TODO: Fix this when i have placeholder image, probably upon error set the image visible.  Then I woudn't need loading spinner?


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

        View viewTrailer = getTrailerView(RelatedVideo.getTestVideo()); //TODO: Custom View
        viewGroupRelatedVideos.addView(viewTrailer);
    }

    //TODO:  custom view with bound data like in dan lew tut. tried android data, didnt' work yet.
    public View getTrailerView(final RelatedVideo relatedVideo) {
        TextView tv = new TextView(this.getActivity());
        tv.setText(relatedVideo.getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInYoutube(relatedVideo);
            }
        });
        return tv;
    }

    /// TODO: Consider moving this code to RelatedVideo.java

    private void openInYoutube(RelatedVideo relatedVideo) {
        //TODO: Try/catch or something to do when youtube is not on the app to open in the browser with key
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + relatedVideo.getKey()));
        startActivity(i);
    }


}
