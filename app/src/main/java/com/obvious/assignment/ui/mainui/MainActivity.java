package com.obvious.assignment.ui.mainui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obvious.assignment.R;
import com.obvious.assignment.model.ImageModel;
import com.obvious.assignment.ui.detailsactivity.DetailsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImagesAdapter.OnImageClick {

    private RecyclerView recyclerView;
    List<ImageModel> imageLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        readJson();
        setupRecycler();

    }

    private void setupRecycler() {
        ImagesAdapter imagesAdapter = new ImagesAdapter(imageLists, getApplicationContext(),MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(imagesAdapter);
    }

    private void readJson() {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);

                String mCopyright = menuItemObject.getString("copyright");
                String mDate = menuItemObject.getString("date");
                String mExplanation = menuItemObject.getString("explanation");
                String mHdurl = menuItemObject.getString("hdurl");
                String mMedia = menuItemObject.getString("media_type");
                String mService = menuItemObject.getString("service_version");
                String mTitle = menuItemObject.getString("title");
                String mUrl= menuItemObject.getString("url");



                ImageModel pojo = new ImageModel(mCopyright, mDate,mExplanation, mHdurl,
                        mMedia, mService,mTitle,mUrl);
                imageLists.add(pojo);
            }
        } catch (IOException | JSONException exception) {
            Log.e(MainActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        imageLists = new ArrayList<>();
    }


    private String readJsonDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.data);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }


    @Override
    public void onClick(int position) {
        String pos = String.valueOf(position);
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("list", (Serializable) imageLists);
        intent.putExtra("pos",pos);

        startActivity(intent);
    }
}
