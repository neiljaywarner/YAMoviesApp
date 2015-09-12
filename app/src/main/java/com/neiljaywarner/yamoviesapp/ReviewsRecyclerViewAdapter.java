package com.neiljaywarner.yamoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neiljaywarner.yamoviesapp.model.Review;
import com.neiljaywarner.yamoviesapp.model.ReviewsList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 8/20/15.
 */
public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.ReviewViewHolder> {

    private List<Review> reviews = new ArrayList<>();


    public void setData(ReviewsList reviewList) {
        if (reviews != null) {
            reviews = reviewList.getResults();
            this.notifyDataSetChanged();
        }
    }

    @Override
    public ReviewsRecyclerViewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);


        return new ReviewsRecyclerViewAdapter.ReviewViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ReviewsRecyclerViewAdapter.ReviewViewHolder holder, int position) {
        final Review review = reviews.get(position);

        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewContent.setText(review.getContent());
        //TODO: Cosmetics, empty view, trigger from somewhere besides long press!

    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewAuthor, textViewContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            //TODO: Use Butterknife
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
            textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);

        }
    }

}
