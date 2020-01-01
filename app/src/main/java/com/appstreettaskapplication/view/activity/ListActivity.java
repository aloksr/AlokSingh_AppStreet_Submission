package com.appstreettaskapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
 import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appstreettaskapplication.R;
import com.appstreettaskapplication.constants.AppConstants;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.view.adapter.ListAdapter;
import com.appstreettaskapplication.view.callbacks.OnItemCLickListener;
import com.appstreettaskapplication.viewmodels.ListViewModel;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity implements OnItemCLickListener {
    private ListAdapter listAdapter;
    private ShimmerRecyclerView rvList;
    private ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvList = findViewById(R.id.rvList);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.init();
        listViewModel.getListRepository().observe(ListActivity.this, new Observer<List<ListResponseModel>>() {
            @Override
            public void onChanged(List<ListResponseModel> listResponseModels) {
                if(listResponseModels!= null && listResponseModels.size() > 0) {
                    listAdapter.setData(listResponseModels);
                    rvList.hideShimmerAdapter();
                }
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (listAdapter == null) {
            listAdapter = new ListAdapter(ListActivity.this);
            rvList.setLayoutManager(new LinearLayoutManager(this));
            listAdapter.setOnItemClickListener(this);
            rvList.setAdapter(listAdapter);
            rvList.showShimmerAdapter();
            rvList.setItemAnimator(new DefaultItemAnimator());
            rvList.setNestedScrollingEnabled(true);
        } else {
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View v, int pos) {
        if (v.getId() == R.id.item_container) {
            navigateToDetaiPage(listAdapter.getItemAt(pos));
        }
    }

    private void navigateToDetaiPage(ListResponseModel itemData) {
        Intent intent = new Intent(this, DetailPageActivity.class);
        // Get the transition name from the string
        intent.putExtra(AppConstants.LIST_DATA, itemData);
        String transitionName = getString(R.string.app_name);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        rvList,   // Starting view
                        transitionName    // The String
                );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
