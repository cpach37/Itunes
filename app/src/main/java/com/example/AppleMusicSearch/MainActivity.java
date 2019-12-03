package com.example.AppleMusicSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.AppleMusicSearch.Itunes.Itunes;
import com.example.AppleMusicSearch.Itunes.Result;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Map<String, String> queryMap;

    private ListViewAdapter adapter;
    private ListView mListView;
    private Menu menu;

    private void populateListView (List<Result> resultList){
        mListView = findViewById(R.id.listMain);
        adapter = new ListViewAdapter(this, resultList);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       }

    public void searchMusic(String type, String key){
        ItunesApi itunesApi = RetrofitInstance.getRetrofitInstance().create(ItunesApi.class);
        queryMap = new HashMap<>();
        queryMap.put("term", key);
        Call<Itunes> call = itunesApi.getArtist(queryMap);

        call.enqueue(new Callback<Itunes>() {
            @Override
            public void onResponse(Call<Itunes> call, Response<Itunes> response) {
                List<Result> listadocanciones = response.body().getResults();
                populateListView(listadocanciones);
            }

            @Override
            public void onFailure(Call<Itunes> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //
                searchMusic("users", query);
                //
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //
                searchMusic("users", newText);
                //
                return false;
            }
        });
        return true;
    }

}