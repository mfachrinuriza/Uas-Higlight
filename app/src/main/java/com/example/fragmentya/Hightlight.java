package com.example.fragmentya;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Hightlight extends AppCompatActivity {
    private String query;
    private String KEY_QUERY = "TONY STARK";
    ListView listVideo;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_ITEMS = "items";
    static final String KEY_ID = "id";
    static final String KEY_VIDEOID = "videoId";
    static final String KEY_SNIPPET = "snippet";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_THUMBNAILS = "thumbnails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight);

        Bundle getData = getIntent().getExtras();
        query = getData.getString(KEY_QUERY);


        listVideo = findViewById(R.id.listVideo);
        loader = findViewById(R.id.loader);
        listVideo.setEmptyView(loader);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }



    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        protected String doInBackground(String... args) {
            String xml = Function.excuteGet("https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyAmaQD8TB-8ZxQk2CYpH3_MKosg7lWfJ8Y&q=" + query +"&maxResults=10&type=video");
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            if (xml.length() > 10) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray(KEY_ITEMS);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        if (jsonObject.has( KEY_ID )){
                            JSONObject jsonID = jsonObject.getJSONObject(KEY_ID);
                            map.put( KEY_VIDEOID, jsonID.optString( KEY_VIDEOID ) );
                        }
                        if (jsonObject.has( KEY_SNIPPET )){
                            JSONObject jsonSnippet = jsonObject.getJSONObject(KEY_SNIPPET);
                            map.put( KEY_PUBLISHEDAT, jsonSnippet.optString( KEY_PUBLISHEDAT ) );
                            map.put( KEY_TITLE, jsonSnippet.optString( KEY_TITLE ) );
                            map.put( KEY_DESCRIPTION, jsonSnippet.optString( KEY_DESCRIPTION ) );
                            map.put(KEY_THUMBNAILS, jsonSnippet.getJSONObject(KEY_THUMBNAILS ).getJSONObject( "high" ).optString( "url" ));
                        }
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                ListVideoAdapter adapter = new ListVideoAdapter(Hightlight.this, dataList);
                listVideo.setAdapter(adapter);

                listVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent i = new Intent(Hightlight.this, HightlightActivity.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_VIDEOID));
                        startActivity(i);
                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "No video found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}