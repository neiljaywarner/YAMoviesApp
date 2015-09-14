package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.neiljaywarner.yamoviesapp.model.YAMovie;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callbacks {

    boolean mTwoPane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.

        }
    }


    /**
     * Callback method from {@link MainActivityFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(YAMovie movie) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Log.i("NJW", "is TwoPane");
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailActivityFragment.ARG_MOVIE, movie);
            MovieDetailActivityFragment fragment = new MovieDetailActivityFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.

            gotoDetailScreen(this, movie);

        }
    }

    /**
     * @param movie The movie to show details for.
     */
    private void gotoDetailScreen(Context context, YAMovie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);

        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        context.startActivity(intent);
    }
}
