package com.obvious.assignment.ui.detailsactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.obvious.assignment.R;
import com.obvious.assignment.model.ImageModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

     RecyclerView recyclerView;
     Intent intent;
     List<ImageModel> list;
     String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent = getIntent();
        position = getIntent().getStringExtra("pos");
        list = (List<ImageModel>) getIntent().getSerializableExtra("list");
        initViews();
        setupRecycler(position);

    }

    private void setupRecycler(String position) {

        DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));
        recyclerView.setAdapter(detailsAdapter);
        recyclerView.getLayoutManager().scrollToPosition(Integer.parseInt(position));

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewDetails);


    }
}