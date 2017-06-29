package com.amanmehara.programming.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.amanmehara.programming.android.adapters.DetailAdapter;
import com.amanmehara.programming.android.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.amanmehara.programming.android.util.ActivityUtils.SET_ACTION_BAR;
import static com.amanmehara.programming.android.util.ActivityUtils.START_ACTIVITY;


public class DetailActivity extends Activity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SET_ACTION_BAR.apply(this,R.id.my_toolbar).accept(true);

        bundle = getIntent().getExtras();

        JSONObject programDetails = null;
        try {
            programDetails = new JSONObject(bundle.getString("programDetails"));
        } catch (JSONException e) {
//            e.printStackTrace();
        }

        JSONArray files = null;
        try {
            files = programDetails.getJSONArray("Files");
        } catch (JSONException e) {
//            e.printStackTrace();
        }

        RecyclerView detailRecyclerView = (RecyclerView) findViewById(R.id.files_recycler_view);
        detailRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager detailLayoutManager = new LinearLayoutManager(this);
        detailRecyclerView.setLayoutManager(detailLayoutManager);

        RecyclerView.Adapter detailAdapter = new DetailAdapter(files, bundle.getString("language"));
        detailRecyclerView.setAdapter(detailAdapter);

        TextView pgogramName = (TextView) findViewById(R.id.program_name);
        try {
            pgogramName.setText(programDetails.getString("ProgramName"));
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            Map<String,Serializable> extrasMap = new HashMap<>();
            extrasMap.put("language",bundle.getString("language"));
            START_ACTIVITY
                    .apply(this,ProgramActivity.class)
                    .accept(extrasMap);
        }

        return super.onOptionsItemSelected(item);
    }
}