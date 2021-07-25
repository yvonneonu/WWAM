package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class SearchExplore extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_explore);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = findViewById(R.id.tool_bar);

    }

    public void backtofragm(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.exploremenu);
         super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exploremenu, menu);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final int Location = R.id.location;
                final int Search = R.id.search;
                switch (item.getItemId()){

                    case Location:
                        Fragment fragment = new LocationFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentcontainer,fragment)
                                .commit();
                        break;
                    case Search:

                        Intent intent = new Intent(SearchExplore.this, SearchExplore.class);
                        startActivity(intent);
                        Log.d("Search","Search is being clicked");
                        break;
                }
                return false;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final int Location = R.id.location;
        final int Search = R.id.search;
        switch (item.getItemId()){

            case Location:
                Fragment fragment = new LocationFragment();
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentcontainer,fragment)
                .commit();
                break;
            case Search:

                Intent intent = new Intent(SearchExplore.this, SearchExplore.class);
                startActivity(intent);
                Log.d("Search","Search is being clicked");
                break;
        }
        return super.onOptionsItemSelected(item);


    }
}