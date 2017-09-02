package com.amanmehara.programming.android.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.amanmehara.programming.android.R;
import com.amanmehara.programming.android.activities.enumeration.Activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ConnectionActivity extends BaseActivity {

    private Bundle bundle;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        setActionBar(R.id.my_toolbar,true);
        bundle = getIntent().getExtras();
        activity = (Activity) bundle.getSerializable("enumeration.Activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tryAgain(View view) {

        if (isConnected()) {
            Map<String,Serializable> extrasMap = new HashMap<>();
            switch (activity) {
                case LANGUAGE:
                    startActivity(LanguageActivity.class,extrasMap);
                    break;
                case DETAIL:
                    extrasMap.put("language",bundle.getString("language"));
                    extrasMap.put("programs",bundle.getString("programs"));
                    extrasMap.put("program",bundle.getString("program"));
                    startActivity(DetailActivity.class,extrasMap);
                    break;
                default:
            }
        } else {
            Map<String,Serializable> extrasMap = new HashMap<>();
            switch (activity) {
                case LANGUAGE:
                    extrasMap.put("enumeration.Activity", Activity.LANGUAGE);
                    startActivity(ConnectionActivity.class,extrasMap);
                    break;
                case DETAIL:
                    extrasMap.put("enumeration.Activity", Activity.DETAIL);
                    extrasMap.put("language",bundle.getString("language"));
                    extrasMap.put("programs",bundle.getString("programs"));
                    extrasMap.put("program",bundle.getString("program"));
                    startActivity(ConnectionActivity.class,extrasMap);
                    break;
                default:
            }
        }

    }
}