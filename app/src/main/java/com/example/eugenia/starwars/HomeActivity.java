package com.example.eugenia.starwars;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.eugenia.starwars.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private int currentStatus = 2;

    private HeroAdapter mHeroAdapter;
    private RecyclerScrollListener recyclerScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.hero_recycler_view);

        mHeroAdapter = new HeroAdapter();
        mRecyclerView.setAdapter(mHeroAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mHeroAdapter.setListener(new HeroAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), HeroDetailActivity.class);
                intent.putExtra(HeroDetailActivity.HERO_DETAIL_ID, position);
                startActivity(intent);
            }
        });

        recyclerScrollListener = new RecyclerScrollListener();
        mRecyclerView.addOnScrollListener(recyclerScrollListener);

        loadData();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_popup_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_button_change_status_loading:
                showLoading();
            case R.id.action_button_change_status_empty:
                break;
            case R.id.action_button_change_status_content:
                showContent();
            case R.id.action_button_change_status_error:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadData() {
        showLoading();
        ((App) getApplication()).getmRestApiHeroService().getAllHeroes(1).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                recyclerScrollListener.setLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mHeroAdapter.addHeroes(response.body().getmResult());
                        showContent();
                        Log.d("TAG", "Success");
                    } else {
                        Log.d("TAG", "null");
                    }
                } else {
                    Log.d("TAG", "not success");
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void showContent() {
        getCurrentView(currentStatus).setVisibility(View.INVISIBLE);
        findViewById(R.id.hero_recycler_view).setVisibility(View.VISIBLE);
        currentStatus = 2;
    }

    private void showLoading() {
        getCurrentView(currentStatus).setVisibility(View.INVISIBLE);
        findViewById(R.id.status_home_loading).setVisibility(View.VISIBLE);
        currentStatus = 0;
    }

    private View getCurrentView(int currentId) {
        switch (currentId) {
            case 0:
                return findViewById(R.id.status_home_loading);
            case 2:
                return findViewById(R.id.hero_recycler_view);
            default:
                return findViewById(R.id.hero_recycler_view);
        }
    }

    private void fetchData(Integer page) {
        ((App) getApplication()).getmRestApiHeroService().getAllHeroes(page).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                recyclerScrollListener.setLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mHeroAdapter.addHeroes(response.body().getmResult());
                        Log.d("TAG", "Success");
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                recyclerScrollListener.setLoading(false);
            }
        });
    }

    private class RecyclerScrollListener extends RecyclerView.OnScrollListener {

        private Integer mTotalCount;
        private Integer mLastItem;
        private Integer mThresHold = 5;
        private boolean mIsLoading = false;

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (mRecyclerView.getLayoutManager() == null) {
                return;
            }

            mTotalCount = mRecyclerView.getLayoutManager().getItemCount();
            mLastItem = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findLastVisibleItemPosition();

            if (!mIsLoading && mTotalCount < (mLastItem + mThresHold)){
                fetchData((mTotalCount / 10) + 1);
                setLoading(true);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        private void setLoading(Boolean loading) {
            mIsLoading = loading;
        }


    }
}
