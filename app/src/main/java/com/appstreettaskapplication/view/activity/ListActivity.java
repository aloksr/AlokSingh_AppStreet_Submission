package com.appstreettaskapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.appstreettaskapplication.R;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.view.adapter.ListAdapter;
import com.appstreettaskapplication.view.callbacks.OnItemCLickListener;
import com.appstreettaskapplication.viewmodels.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements OnItemCLickListener {
    ArrayList<ListResponseModel> listModels = new ArrayList<>();
    ListAdapter listAdapter;
    RecyclerView rvHeadline;
    ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvHeadline = findViewById(R.id.rvList);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.init();
        listViewModel.getListRepository().observe(ListActivity.this, new Observer<List<ListResponseModel>>() {
            @Override
            public void onChanged(List<ListResponseModel> listResponseModels) {
                listModels.addAll(listResponseModels);
                listAdapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (listAdapter == null) {
            listAdapter = new ListAdapter(ListActivity.this, listModels);
            rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            listAdapter.setOnItemClickListener(this);
            rvHeadline.setAdapter(listAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
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

    private void navigateToDetaiPage(ListResponseModel itemAt) {


    }
}
