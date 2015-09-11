package com.neiljaywarner.yamoviesapp.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neiljaywarner.yamoviesapp.R;
import com.neiljaywarner.yamoviesapp.model.RelatedVideo;
import com.squareup.picasso.Picasso;

/**
 * RelatedView that has a special knowledge of how to bind relevant data.
 * FROM https://github.com/dlew/android-custom-views-sample/blob/master/app/src/main/java/net/danlew/customviews/view/UserViewEncapsulated.java
 * which is Apache Licensed by Dan Lew (appears to be). i saw his talk in 2014 in this.
 */
public class YoutubeThumbnailView extends LinearLayout {

    private ImageView mIconView;
    private TextView mNameView;

    public YoutubeThumbnailView(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.LEFT);

        LayoutInflater.from(context).inflate(R.layout.video_thumbnail_merge, this);
        mIconView = (ImageView) findViewById(R.id.icon);
        //TODO: Use placeholder and error icons.
        mNameView = (TextView) findViewById(R.id.name);
    }

    public void bind(RelatedVideo video) {

        Picasso.with(mIconView.getContext()).load(video.getYoutubeThumbnailUrl()).into(mIconView);

        mNameView.setText(video.getName());
    }
}
